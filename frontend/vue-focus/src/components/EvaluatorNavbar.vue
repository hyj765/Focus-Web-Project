<template>
  <div class="flex flex-none min-h-screen">
    <div class="flex flex-col w-56 overflow-hidden bg-white/50">
      <ul class="flex flex-col py-4 divide-y space-y-60">
        <div>
          <!-- <div v-show="false">
            <EvaluatorTime
              v-for="(item, index) in scheduledList"
              :content="item.value"
              :key="index"
              :item="item"
            ></EvaluatorTime>
          </div> -->
          <li @click="moveToEvaluatorHome()">
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
          <!-- @click="moveToEvaluatorTime()" -->
          <li>
            <a
              v-for="(schedule, index) in scheduledList"
              :content="schedule.value"
              :key="index"
              href="#"
              class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
            >
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                ><i class="bx bx-time-five"></i
              ></span>
              <span class="text-sm font-medium">
                <router-link
                  :to="{ name: 'EvaluatorTime', params: { id: schedule.id } }"
                >
                  {{ schedule.startTime.slice(11, 16) }} ~
                  {{ schedule.endTime.slice(11, 16) }}
                </router-link>
              </span>
            </a>
          </li>
        </div>
        <div>
          <li @click="moveToEvaluatorRecord()">
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
          <li @click="moveToEvaluatorFAQ()">
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
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

import EvaluatorTime from '@/views/Evaluator/EvaluatorTime.vue';

const router = useRouter();
const scheduledList = ref(null);

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const moveToEvaluatorHome = () => {
  router.push({ name: 'Evaluator' });
};

const moveToEvaluatorTime = () => {
  router.push({ name: 'EvaluatorTime' });
};

const moveToEvaluatorRecord = () => {
  router.push({ name: 'EvaluatorRecord' });
};
const moveToEvaluatorFAQ = () => {
  router.push({ name: 'EvaluatorFAQ' });
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

onMounted(() => {
  getScheduleList();
});
</script>

<style lang="scss" scoped></style>
