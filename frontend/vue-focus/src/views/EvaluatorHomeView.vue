<template>
  <div>
    <!--     
    # HEADER
  -->
    <nav class="bg-white/50 border-gray-200 px-2 sm:px-4 py-2.5 rounded">
      <div
        class="container flex flex-wrap items-center justify-between mx-auto"
      >
        <div class="flex items-center space-x-2">
          <img class="h-7" src="../assets/blue_opacity.png" alt="logo" />
          <span class="self-center text-xl font-semibold whitespace-nowrap"
            >Focus</span
          >
        </div>
        <div class="hidden w-full md:block md:w-auto" id="navbar-default">
          <ul
            class="flex flex-col items-center p-4 mt-4 border border-gray-100 rounded-lg md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0"
          >
            <!-- # FAQ -->
            <li class="rounded-lg bg-white/60">
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
                ><i class="bx bx-question-mark"></i
              ></span>
            </li>

            <!-- # 메일함 -->
            <li class="rounded-lg bg-white/60">
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
                ><i class="bx bxs-inbox"></i
              ></span>
            </li>

            <!-- # 프로필 -->
            <div class="flex items-center space-x-2">
              <img
                class="h-8 rounded-full"
                src="~@/assets/오상식.jpg"
                alt="James Bhatta"
              />
              <button @click="changeCurrentComp(EvaluatorProfile)">
                <h4
                  class="font-semibold tracking-wide text-gray-700 capitalize font-poppins"
                >
                  {{ userId }} {{ name }}
                </h4>
              </button>
              <!-- 로그아웃 -->
              <li class="rounded-lg bg-white/60" @click="logout()">
                <span
                  class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
                  ><i class="bx bx-log-out"></i
                ></span>
              </li>
            </div>
          </ul>
        </div>
      </div>
    </nav>
    <div class="flex">
      <!-- 
      # NAVBAR
      -->
      <div class="flex flex-none min-h-screen">
        <div class="flex flex-col w-56 overflow-hidden bg-white/50">
          <ul class="flex flex-col py-4 divide-y space-y-60">
            <div>
              <li @click="changeCurrentComp(EvaluatorDashboard)">
                <a
                  href="#"
                  class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
                >
                  <span
                    class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                    ><i class="bx bxs-dashboard"></i
                  ></span>
                  <span class="text-sm font-medium">홈 / 대시보드</span>
                </a>
              </li>
              <li @click="changeCurrentComp(EvaluatorTime)">
                <a
                  v-for="(item, index) in scheduledList"
                  :content="item.value"
                  :key="index"
                  href="#"
                  class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
                >
                  <span
                    class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                    ><i class="bx bx-time-five"></i
                  ></span>
                  <span class="text-sm font-medium">
                    {{ item.startTime.slice(11, 16) }} ~
                    {{ item.endTime.slice(11, 16) }}
                  </span>
                </a>
              </li>
            </div>
            <div>
              <li @click="changeCurrentComp(EvaluatorRecord)">
                <a
                  href="#"
                  class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
                >
                  <span
                    class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                    ><i class="bx bx-id-card"></i
                  ></span>
                  <span class="text-sm font-medium">평가내역</span>
                </a>
              </li>
              <li @click="changeCurrentComp(EvaluatorFAQ)">
                <a
                  href="#"
                  class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
                >
                  <span
                    class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                    ><i class="bx bx-question-mark"></i
                  ></span>
                  <span class="text-sm font-medium">FAQ</span>
                </a>
              </li>
            </div>
          </ul>
        </div>
      </div>
      <!-- 
      # DynamicComponentContents
      -->
      <!-- <div>
        <h2>chocolate yummy</h2>
      </div> -->
      <component class="flex-auto" :is="currentComp" />
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, shallowRef, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';

import EvaluatorDashboard from './EvaluatorDashboard.vue';
import EvaluatorRecord from './EvaluatorRecord.vue';
import EvaluatorSetting from './EvaluatorSetting.vue';
import EvaluatorTime from './EvaluatorTime.vue';
import EvaluatorFAQ from './EvaluatorFAQ.vue';
import EvaluatorProfile from './EvaluatorProfile.vue';

const currentComp = shallowRef(EvaluatorDashboard);
const changeCurrentComp = comp => (currentComp.value = comp);

const router = useRouter();
const store = useStore();

const scheduledList = ref(null);
const myinfo = ref(null);
const name = ref('');
const email = ref('');
const tel = ref('');
const userId = ref('');

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const logout = () => {
  store.dispatch('logout').then(() => {
    router.push({ name: 'Login' });
  });
};

const getScheduleList = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/evaluators/list`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('res.data: ', res.data);
      scheduledList.value = res.data['2023-02-17'];
      console.log(scheduledList.value);
    });
};

const getMyInfo = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/evaluators/me`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('myInfo : ', res.data);
      myinfo.value = res;
      name.value = myinfo.value.data.name;
      email.value = myinfo.value.data.email;
      tel.value = myinfo.value.data.tel;
      userId.value = myinfo.value.data.userId;
    });
};

onMounted(() => {
  getScheduleList();
  getMyInfo();
});
</script>

<style lang="scss" scoped></style>
