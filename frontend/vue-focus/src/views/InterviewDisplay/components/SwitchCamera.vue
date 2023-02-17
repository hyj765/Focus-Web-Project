<template>
  <div>
    <div class="videoInput">
      <video class="myVideo" autoplay :poster="posterImgSrc"></video>
    </div>
    <div class="text-center mt-3">
      <button class="btn btn-secondary" @click="onClickCameraBtn">
        <font-awesome-icon :icon="['fas', cameraIcon]" />
      </button>

      <button class="btn btn-secondary" @click="onClickMuteBtn">
        <font-awesome-icon :icon="['fas', mutedIcon]" />
      </button>

      <button class="btn btn-secondary" @click="selectsOn = !selectsOn">
        <img src="~@/assets/settings.png" alt="" class="setting" />
        <!-- <font-awesome-icon :icon="['fas', 'cog']" /> -->
      </button>

      <div v-if="selectsOn" class="select-container my-2">
        <select
          id="cameraSelect"
          v-model="deviceSetting.currentVideoId"
          name="cameras"
          class="form-select form-select-sm text-truncate"
          @change="onChangeCamera"
        >
          <option value="0" disabled>카메라 선택</option>
          <option
            v-for="camera in cameraDevices"
            :key="camera.deviceId"
            :value="camera.deviceId"
          >
            {{ camera.label }}
          </option>
        </select>
        <select
          id="micSelect"
          v-model="deviceSetting.currentAudioId"
          name="mics"
          class="form-select form-select-sm text-truncate"
          @change="onChangeCamera"
        >
          <option value="0" disabled>마이크 선택</option>
          <option
            v-for="mic in micDevices"
            :key="mic.deviceId"
            :value="mic.deviceId"
          >
            {{ mic.label }}
          </option>
        </select>
      </div>
    </div>
  </div>
</template>
<script>
import notifications from '@/composables/notifications';

const { notifyDanger } = notifications();

export default {
  name: 'StudyDetailCamera',
  components: {},
  data() {
    return {
      myStream: null,
      myVideo: null,
      cameraDevices: [],
      initCamera: 0,
      micDevices: [],
      initMic: 0,
      selectsOn: false,
      deviceSetting: {
        currentVideoId: '',
        currentAudioId: '',
        isCameraOn: true,
        isMuted: true,
      },
      posterImgSrc: '',
    };
  },
  computed: {
    mutedIcon() {
      return this.deviceSetting.isMuted ? 'microphone-slash' : 'microphone';
    },
    cameraIcon() {
      return this.deviceSetting.isCameraOn ? 'video' : 'video-slash';
    },
  },
  watch: {
    deviceSetting: {
      deep: true,
      handler() {
        this.$emit(
          // eslint-disable-next-line
          'set-device',
          {
            videoId: this.deviceSetting.currentVideoId,
            audioId: this.deviceSetting.currentAudioId,
            videoOn: this.deviceSetting.isCameraOn,
            audioOn: !this.deviceSetting.isMuted,
          },
        );
      },
    },
  },
  mounted() {
    window.addEventListener('beforeunload', this.unloadEvent);
    this.myVideo = document.querySelector('.myVideo');
    this.getMedia();
    this.getDevices();
  },
  unmounted() {
    this.unloadEvent();
    window.removeEventListener('beforeunload', this.unloadEvent);
  },
  methods: {
    unloadEvent() {
      this.closeMedia();
      this.myStream = null;
      this.myVideo = null;
    },
    // 장치 가져오기
    getDevices: async function () {
      try {
        const devices = await navigator.mediaDevices.enumerateDevices();
        const cameras = devices.filter(device => device.kind === 'videoinput');
        const mics = devices.filter(device => device.kind === 'audioinput');
        // this.currentCamera = this.myStream ? await this.myStream.getVideoTracks()[0].id : null
        // this.currentMic = this.myStream ? await this.myStream.getAudioTracks()[0].id: null
        this.cameraDevices = cameras;
        this.micDevices = mics;
        this.deviceSetting.currentVideoId = this.myStream
          ? await this.myStream.getVideoTracks()[0].getSettings().deviceId
          : null;
        this.deviceSetting.currentAudioId = this.myStream
          ? await this.myStream.getAudioTracks()[0].getSettings().deviceId
          : null;
      } catch (err) {
        console.log(err);
      }
    },
    // 장치 연결
    getMedia: async function (cameraDeviceId, micDeviceId) {
      // 초기 세팅
      const initialConstraints = {
        audio: true,
        video: true,
      };
      // 사용자 입력 세팅
      const exactConstraints = {
        audio: { deviceId: micDeviceId },
        video: { deviceId: cameraDeviceId },
      };
      try {
        // 스트림
        this.myStream = await navigator.mediaDevices.getUserMedia(
          cameraDeviceId ? exactConstraints : initialConstraints,
        );
        this.myVideo.srcObject = this.myStream;
        if (this.deviceSetting.isMuted) {
          this.onClickMuteBtn();
          this.deviceSetting.isMuted = true;
        }
        if (!this.deviceSetting.isCameraOn) {
          this.onClickCameraBtn();
          this.deviceSetting.isCameraOn = false;
        }
        if (!cameraDeviceId) {
          await this.getDevices();
        }
      } catch (err) {
        console.log(err);
        if (err.message === 'Permission denied') {
          notifyDanger('카메라/오디오 접근 권한이 필요합니다.😳');
          this.posterImgSrc = require('@/assets/profile.png');
        }
        console.log(err.message);
        this.closeMedia();
      }
    },
    onClickMuteBtn: function () {
      this.deviceSetting.isMuted = !this.deviceSetting.isMuted;
      this.myStream.getAudioTracks().forEach(track => {
        track.enabled = !track.enabled;
      });
    },
    onClickCameraBtn: async function () {
      this.deviceSetting.isCameraOn = !this.deviceSetting.isCameraOn;
      const videoTrack = this.myStream.getVideoTracks()[0];
      videoTrack.enabled = !videoTrack.enabled;
      if (!videoTrack.enabled) {
        videoTrack.stop();
      } else {
        await this.getMedia(
          this.deviceSetting.currentVideoId,
          this.deviceSetting.currentAudioId,
        );
      }
    },
    onChangeCamera: async function (e) {
      // 현재 카메라 끄고, 타겟 카메라와 현재 마이크 불러옴.
      console.log(e.target.value);
      this.myStream.getVideoTracks().forEach(track => {
        track.stop();
      });
      await this.getMedia(
        this.deviceSetting.currentVideoId,
        this.deviceSetting.currentAudioId,
      );
    },
    onChangeMic: async function () {
      // 현재 마이크 끄고, 타겟 마이크와 현재 카메라
      this.myStream.getAudioTracks().forEach(track => {
        track.stop();
      });
      await this.getMedia(
        this.deviceSetting.currentVideoId,
        this.deviceSetting.currentAudioId,
      );
    },
    // 미디어 장치 종료
    closeMedia: function () {
      try {
        this.myStream.getTracks().forEach(track => {
          track.stop();
        });
        this.myStream.srcObject = null;
      } catch (err) {
        console.log(err);
      }
    },
  },
};
</script>
<style scoped>
video {
  margin-top: 10rem;
  border-radius: 1rem;
  justify-content: center;
  width: 100%;
  aspect-ratio: 30 / 19;
  overflow: hidden;
  object-fit: cover;
  border-radius: 1rem;
}

videoInput {
  width: auto;
  height: auto;
}

button {
  border-radius: 0%;
  height: 5rem;
  width: 5rem;
  margin: 0 0.5rem 0px;
  color: black;
}

.setting {
  width: 14px;
  height: 14px;
  object-fit: cover;
}
</style>
