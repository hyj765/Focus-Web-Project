<template>
  <div>
    <div class="videoInput" style="display: flex; margin-left: 13%">
      <video class="myVideo" autoplay :poster="posterImgSrc"></video>
      <!-- <img src="~@/assets/guide_image.jpg" alt="Guide_Image" /> -->
    </div>
    <div class="flex flex-row justify-center">
      <div>
        <div
          class="flex justify-center w-16 space-x-2 rounded"
          :class="cameraBackground"
        >
          <button @click="onClickCameraBtn" type="button">
            <i :class="cameraIcon"></i>
          </button>
          <button @click="selectsOnCamera = !selectsOnCamera">
            <i class="bx bx-chevron-down"></i>
          </button>
        </div>
      </div>
      <div>
        <div
          class="flex justify-center w-16 space-x-2 rounded"
          :class="muteBackground"
        >
          <button @click="onClickMuteBtn" type="button">
            <i :class="mutedIcon"></i>
          </button>
          <button @click="selectsOn = !selectsOn">
            <i class="bx bx-chevron-down"></i>
          </button>
        </div>
      </div>

      <div class="flex flex-col">
        <button class="btn btn-secondary" @click="selectsOn = !selectsOn">
          <img src="~@/assets/settings.png" alt="setting" class="w-6 setting" />
          <!-- <font-awesome-icon :icon="['fas', 'cog']" /> -->
        </button>
        <div v-if="selectsOnCamera" class="fixed my-2 select-container w-72">
          <Listbox
            v-model="deviceSetting.currentVideoId"
            id="cameraSelect"
            name="cameras"
            @change="onChangeCamera"
          >
            <div class="relative mt-1">
              <ListboxButton
                class="relative w-full py-2 pl-3 pr-10 text-left bg-white rounded-lg shadow-md cursor-default focus:outline-none focus-visible:border-indigo-500 focus-visible:ring-2 focus-visible:ring-white focus-visible:ring-opacity-75 focus-visible:ring-offset-2 focus-visible:ring-offset-orange-300 sm:text-sm"
              >
                <span class="block truncate">카메라 선택</span>
                <span
                  class="absolute inset-y-0 right-0 flex items-center pr-2 pointer-events-none"
                >
                  <ChevronUpDownIcon
                    class="w-5 h-5 text-gray-400"
                    aria-hidden="true"
                  />
                </span>
              </ListboxButton>

              <transition
                leave-active-class="transition duration-100 ease-in"
                leave-from-class="opacity-100"
                leave-to-class="opacity-0"
              >
                <ListboxOptions
                  class="absolute w-full py-1 mt-1 overflow-auto text-base bg-white rounded-md shadow-lg max-h-60 ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm"
                >
                  <ListboxOption
                    v-slot="{ active, selected }"
                    v-for="camera in cameraDevices"
                    :key="camera.deviceId"
                    :value="camera.deviceId"
                    as="template"
                  >
                    <li
                      :class="[
                        active
                          ? 'bg-amber-100 text-amber-900'
                          : 'text-gray-900',
                        'relative cursor-default select-none py-2 pl-10 pr-4',
                      ]"
                    >
                      <span
                        :class="[
                          selected ? 'font-medium' : 'font-normal',
                          'block truncate',
                        ]"
                        >{{ camera.label }}</span
                      >
                      <span
                        v-if="selected"
                        class="absolute inset-y-0 left-0 flex items-center pl-3 text-amber-600"
                      >
                        <CheckIcon class="w-5 h-5" aria-hidden="true" />
                      </span>
                    </li>
                  </ListboxOption>
                </ListboxOptions>
              </transition>
            </div>
          </Listbox>
        </div>
        <div v-if="selectsOn" class="fixed my-2 select-container w-72">
          <Listbox
            v-model="deviceSetting.currentAudioId"
            id="micSelect"
            name="mics"
            @change="onChangeCamera"
          >
            <div class="relative mt-1">
              <ListboxButton
                class="relative w-full py-2 pl-3 pr-10 text-left bg-white rounded-lg shadow-md cursor-default focus:outline-none focus-visible:border-indigo-500 focus-visible:ring-2 focus-visible:ring-white focus-visible:ring-opacity-75 focus-visible:ring-offset-2 focus-visible:ring-offset-orange-300 sm:text-sm"
              >
                <span class="block truncate">마이크 선택</span>
                <span
                  class="absolute inset-y-0 right-0 flex items-center pr-2 pointer-events-none"
                >
                  <ChevronUpDownIcon
                    class="w-5 h-5 text-gray-400"
                    aria-hidden="true"
                  />
                </span>
              </ListboxButton>

              <transition
                leave-active-class="transition duration-100 ease-in"
                leave-from-class="opacity-100"
                leave-to-class="opacity-0"
              >
                <ListboxOptions
                  class="absolute w-full py-1 mt-1 overflow-auto text-base bg-white rounded-md shadow-lg max-h-60 ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm"
                >
                  <ListboxOption
                    v-slot="{ active, selected }"
                    v-for="mic in micDevices"
                    :key="mic.deviceId"
                    :value="mic.deviceId"
                    as="template"
                  >
                    <li
                      :class="[
                        active
                          ? 'bg-amber-100 text-amber-900'
                          : 'text-gray-900',
                        'relative cursor-default select-none py-2 pl-10 pr-4',
                      ]"
                    >
                      <span
                        :class="[
                          selected ? 'font-medium' : 'font-normal',
                          'block truncate',
                        ]"
                        >{{ mic.label }}</span
                      >
                      <span
                        v-if="selected"
                        class="absolute inset-y-0 left-0 flex items-center pl-3 text-amber-600"
                      >
                        <CheckIcon class="w-5 h-5" aria-hidden="true" />
                      </span>
                    </li>
                  </ListboxOption>
                </ListboxOptions>
              </transition>
            </div>
          </Listbox>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref } from 'vue';
import {
  Listbox,
  ListboxLabel,
  ListboxButton,
  ListboxOptions,
  ListboxOption,
} from '@headlessui/vue';
import { CheckIcon, ChevronUpDownIcon } from '@heroicons/vue/20/solid';
</script>
<script>
import notifications from '@/composables/notifications';
const { notifyDanger } = notifications();

export default {
  name: 'StudyDetailCamera',
  data() {
    return {
      myStream: null,
      myVideo: null,
      cameraDevices: [],
      initCamera: 0,
      micDevices: [],
      initMic: 0,
      selectsOn: false,
      selectsOnCamera: false,
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
      return this.deviceSetting.isMuted
        ? 'bx bx-volume-mute'
        : 'bx bx-volume-full';
    },
    muteBackground() {
      return this.deviceSetting.isMuted
        ? 'inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-red-600 rounded-full shadow-md hover:bg-red-700 hover:shadow-lg focus:bg-red-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-red-800 active:shadow-lg w-9 h-9'
        : 'inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-indigo-600 rounded-full shadow-md hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg w-9 h-9';
    },
    cameraIcon() {
      return this.deviceSetting.isCameraOn ? 'bx bx-video' : 'bx bx-video-off';
    },
    cameraBackground() {
      return this.deviceSetting.isCameraOn
        ? 'inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-indigo-600 rounded-full shadow-md hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg w-9 h-9'
        : 'inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-red-600 rounded-full shadow-md hover:bg-red-700 hover:shadow-lg focus:bg-red-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-red-800 active:shadow-lg w-9 h-9';
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
  margin-top: 0;
  border-radius: 1rem;
  justify-content: center;
  width: 70%;
  aspect-ratio: 30 / 19;
  overflow: hidden;
  object-fit: cover;
  border-radius: 1rem;
  transform: rotateY(180deg);
}

videoInput {
  width: auto;
  height: auto;
}
</style>
