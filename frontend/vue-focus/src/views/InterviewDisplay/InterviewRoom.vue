<template>
  <!-- -------------------------------------------------------------- 채팅방 들어가기 전 나오는 화면 ----------------------------------------------------------- -->
  <div id="main-container" class="container">
    <div v-if="!session" id="join">
      <div>
        <!-- <PracticeRoom /> -->
        <SwitchCamera @set-device="setDevice($event)" />
        <div class="form-group">
          <p>
            <label>NAME</label>
            <input
              v-model="myUserName"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p>
            <label>ROOM CODE</label>
            <input
              v-model="mySessionId"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p class="text-center">
            <button class="btn btn-lg btn-success" @click="joinSession()">
              참여하기
            </button>
          </p>
        </div>
      </div>
    </div>

    <!-- ----------------------------------------------------------- 채팅방 들어가면 나오는 화면 ------------------------------------------------------------ -->

    <div v-if="session" id="session">
      <!------------------- 화면 출력 Start -------------------->

      <div id="video-container" class="">
        <div v-if="publisher">
          <user-video
            :stream-manager="publisher"
            :is-speak="
              isSpeakList.includes(publisher.stream.connection.connectionId)
            "
            @click.enter="updateMainVideoStreamManager(publisher)"
          />
        </div>

        <div
          v-for="sub in subscribers"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
        >
          <div class="">
            <user-video
              :stream-manager="sub"
              :is-speak="
                isSpeakList.includes(sub.stream.connection.connectionId)
              "
              @click.enter="updateMainVideoStreamManager(sub)"
            />
          </div>
        </div>
      </div>

      <div id="main-video">
        <MainScreen :stream-manager="mainStreamManager" />
        <div id="tool-bar">
          <!------------------- Camera/Voice On/Off Start ------------------->
          <div id="tools">
            <img
              v-if="!mute"
              id="mute_img"
              src="~@/assets/mute.png"
              @click="unmuteMySound"
            />
            <img
              v-else
              id="unmute_img"
              src="~@/assets/unmute.png"
              @click="muteMySound"
            />

            <!------------------- 화면 공유 Start ---------------------->
            <img
              v-if="!screenOV"
              id="startscreenshare_img"
              src="~@/assets/startscreenshare.png"
              @click="startScreenShare"
            />

            <img
              v-else
              id="stopscreenshare_img"
              src="~@/assets/stopscreenshare.png"
              @click="stopScreenShare"
            />
            <!------------------- 화면 공유 End ------------------------>

            <img
              id="leaveSession_img"
              src="~@/assets/stop_call.png"
              @click="leaveSession"
            />

            <img
              v-if="closecamera"
              id="closecamera_img"
              src="~@/assets/opencamera.png"
              @click="openCamera"
            />

            <img
              v-else
              id="opencamera_img"
              src="~@/assets/closecamera.png"
              @click="closeCamera"
            />

            <img
              id="openchat_img"
              src="~@/assets/openchat.png"
              @click="startChat"
            />
          </div>
          <!------------------- Camera/Voice On/Off End ---------------------->
        </div>
      </div>
      <!------------------- 화면 출력 End ----------------------->

      <!------------------- 채팅 기능 Start ---------------------->
      <div v-if="chatmodal == true" class="black-bg">
        <div class="side_chat">
          <UserChat
            id="user-chat"
            :chat-log="chatLog"
            value="보내기"
            @send-message="sendMessage"
          />
          <button
            class="btn btn-primary"
            style="position: absolute; right: 0px; top: 0px"
            @click="closeChat"
          >
            닫기
          </button>
        </div>
      </div>
      <!------------------- 채팅 기능 End ------------------------>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';

// <------------------- 화면 출력 Start -------------------->
import UserVideo from './components/UserVideo.vue';
// <------------------- 화면 출력 End ---------------------->

// <------------------- 채팅 기능 Start -------------------->
import UserChat from './components/UserChat';
// <------------------- 채팅 기능 End ---------------------->

// import PracticeRoom from "./components/PracticeRoom.vue";

import MainScreen from './components/MainScreen.vue';

import SwitchCamera from './components/SwitchCamera.vue';

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = 'https://' + 'i8a106.p.ssafy.io';
const OPENVIDU_SERVER_SECRET = 'cKlIVFkVgNinXpF';

export default {
  name: 'InterviewRoom',

  components: {
    // <------------------- 화면 출력 Start -------------------->
    UserVideo,
    // <------------------- 화면 출력 End ---------------------->

    // <------------------- 채팅 기능 Start -------------------->
    UserChat,
    // <------------------- 채팅 기능 End ---------------------->

    MainScreen,

    SwitchCamera,
  },

  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      mySessionId: 'Room' + Math.floor(Math.random() * 100),
      myUserName: '지원자' + Math.floor(Math.random() * 100),

      // <------------------- 비디오/보이스 On/Off Start ---------->
      mute: false,
      closecamera: false,
      // <------------------- 비디오/보이스 On/Off End-- ---------->

      // <------------------- 화면 공유 Start -------------------->
      screenOV: undefined,
      screenSession: undefined,
      screenShareName: undefined,
      // <------------------- 화면 공유 End ---------------------->

      // <------------------- 채팅 기능 Start -------------------->
      chatLog: [],
      chatmodal: false,
      // <------------------- 채팅 기능 End ---------------------->
      active: false,

      // Speech Detection
      isSpeakList: [],
      isSpeak: false,
    };
  },

  methods: {
    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();
      this.OV.setAdvancedConfiguration({
        publisherSpeakingEventOptions: {
          interval: 100,
          threshold: -50,
        },
      });

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on('streamCreated', ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      this.session.on('streamDestroyed', ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on('exception', ({ exception }) => {
        console.warn(exception);
      });

      // <------------------- 채팅 기능 Start -------------------->
      this.session.on('signal', event => {
        this.chatLog.push([event.data, JSON.parse(event.from.data)]);
      });
      // <------------------- 채팅 기능 End ---------------------->

      // Speech Start Detection
      this.session.on('publisherStartSpeaking', event => {
        this.isSpeakList.push(event.connection.connectionId);
        this.isSpeak = !this.isSpeak;
      });

      // Speech Stop Detection
      this.session.on('publisherStopSpeaking', event => {
        let temp = this.isSpeakList;
        let index = temp.indexOf(event.connection.connectionId, 0);
        if (index >= 0) {
          temp.splice(index, 1);
          this.isSpeakList = temp;
        }
        this.isSpeak = !this.isSpeak;
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then(token => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: false, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: '640x720', // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisher;
            this.publisher = publisher;

            // --- Publish your stream ---

            this.session.publish(this.publisher);
          })
          .catch(error => {
            console.log(
              'There was an error connecting to the session:',
              error.code,
              error.message,
            );
          });
      });

      window.addEventListener('beforeunload', this.leaveSession);
    },

    leaveSession() {
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;
      (this.chatLog = []),
        window.removeEventListener('beforeunload', this.leaveSession);
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    getToken(mySessionId) {
      return this.createSession(mySessionId).then(sessionId =>
        this.createToken(sessionId),
      );
    },

    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: 'OPENVIDUAPP',
                password: OPENVIDU_SERVER_SECRET,
              },
            },
          )
          .then(response => response.data)
          .then(data => resolve(data.id))
          .catch(error => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`,
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`,
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: 'OPENVIDUAPP',
                password: OPENVIDU_SERVER_SECRET,
              },
            },
          )
          .then(response => response.data)
          .then(data => resolve(data.token))
          .catch(error => reject(error.response));
      });
    },

    // <------------------- 채팅 기능 Start -------------------->
    sendMessage(msg) {
      this.session
        .signal({ data: msg, to: [] })
        .then(() => {
          console.log('Message successfully sent');
        })
        .catch(error => {
          console.error(error);
        });
    },

    // <------------------- 채팅 기능 End ---------------------->

    // <------------------- 화면 공유 Start -------------------->
    startScreenShare() {
      this.screenOV = new OpenVidu();
      this.screenSession = this.screenOV.initSession();
      (this.screenShareName = this.myUserName + "'s Screen"),
        this.getToken(this.mySessionId)
          .then(token => {
            this.screenSession
              .connect(token, { clientData: this.screenShareName })
              .then(() => {
                let publisher = this.screenOV.initPublisher('html-element-id', {
                  videoSource: 'screen',
                  publishAudio: false,
                });

                try {
                  publisher.once('accessAllowed', () => {
                    let test = publisher.stream
                      .getMediaStream()
                      .getVideoTracks(); //eslint-disable-line no-unused-vars
                    publisher.stream
                      .getMediaStream()
                      .getVideoTracks()[0]
                      .addEventListener('ended', () => {
                        this.stopScreenShare();
                      });
                    this.screenSession.publish(publisher);
                  });

                  publisher.once('accessDenied', () => {
                    this.stopScreenShare();
                  });
                } catch (error) {
                  console.log(error);
                }
              });
          })
          .catch(() => {
            this.screenOV = undefined;
            this.screenSession = undefined;
          });
    },

    setDevice(deviceSettings) {
      this.videoId = deviceSettings.videoId;
      this.audioId = deviceSettings.audioId;
      this.videoOn = deviceSettings.videoOn;
      this.audioOn = deviceSettings.audioOn;
    },

    stopScreenShare() {
      this.screenSession.disconnect();
      this.screenOV = undefined;
      this.screenSession = undefined;
    },
    // <------------------- 화면 공유 End ---------------------->

    // <------------------- 비디오/보이스 On/Off Start ------------------->
    hoverToolbar() {
      this.active = true;
    },

    leaveToolbar() {
      this.active = false;
    },

    muteMySound() {
      this.publisher.publishAudio(false);
      this.mute = !this.mute;
    },

    unmuteMySound() {
      this.publisher.publishAudio(true);
      this.mute = !this.mute;
    },

    openCamera() {
      this.publisher.publishVideo(true);
      this.closecamera = !this.closecamera;
    },

    closeCamera() {
      this.publisher.publishVideo(false);
      this.closecamera = !this.closecamera;
    },
    startChat() {
      this.chatmodal = true;
    },
    closeChat() {
      this.chatmodal = false;
    },
    // <------------------- 비디오/보이스 On/Off End ------------------->
  },
};
</script>

<style scoped>
body {
  margin: 0;
}

div {
  box-sizing: border-box;
}

.black-bg {
  width: auto;
  height: auto;
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  padding: 20px;
}

#video-container video {
  display: inline-block;
  position: relative;
  width: 50%;
  cursor: pointer;
}

#video-container video + div {
  float: left;
  width: 50%;
  margin-left: -50%;
}

#video-container p {
  display: inline-block;
  background: #f8f8f8;
  padding-left: 0px;
  padding-right: 5px;
  color: #777777;
  font-weight: bold;
  border-bottom-right-radius: 4px;
}

#main-container {
  padding-left: 0;
  padding-right: 0;
  margin-right: 0;
  margin-left: 0;
}

#main-video p {
  position: absolute;
  display: inline-block;
  background: #f8f8f8;
  padding: 0;
  color: #777777;
  font-weight: bold;
  border-bottom-right-radius: 4px;
}

.main-function {
  display: flex;
  justify-content: center;
}

#tool-bar {
  display: flex;
  justify-content: center;
  position: absolute;
  bottom: 0;
  left: 0;
  height: 10%;
  width: 100%;
}

#tools {
  display: flex;
  justify-content: center;
  height: 50%;
}

#user-chat {
  margin-left: 1rem;
}
</style>
