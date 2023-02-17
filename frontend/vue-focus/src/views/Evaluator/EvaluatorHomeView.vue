<template>
  <div>
    <EvaluatorHeader></EvaluatorHeader>
    <div class="flex">
      <EvaluatorNavbar></EvaluatorNavbar>
      <div class="w-screen">
        <div class="flex flex-col space-y-10">
          <!-- 
    # 네이버님 안녕하세요
    -->
          <nav class="flex flex-wrap justify-between px-8 py-4 text-gray-800">
            <h1 class="font-bold">{{ evaluatorsName }} 님, 안녕하세요</h1>
            <h3 class="font-bold text-gray-500">DASHBOARD</h3>
          </nav>
        </div>
        <div class="p-10">
          <p class="p-2 text-lg font-bold text-gray-500">
            <i class="pr-1 text-indigo-500 bx bxs-check-circle"></i>
            잠시후에 시작할 면접입니다
          </p>
          <div class="inline-block">
            <div
              class="flex flex-row pb-3 pl-2 pr-4 bg-white rounded-lg shadow-lg"
            >
              <div
                class="flex flex-col items-center w-auto h-auto text-gray-900"
              >
                <p
                  class="flex items-center justify-center p-4 text-2xl font-bold"
                >
                  {{ name }}
                </p>
                <p
                  class="flex items-center justify-center px-4 py-1 text-white bg-indigo-900 rounded-xl"
                >
                  {{ startTime.slice(11, 16) }} ~
                  {{ endTime.slice(11, 16) }}
                </p>
              </div>
              <div class="flex items-center justify-center">
                <p>
                  {{ startTime.slice(0, 10) }} ~
                  {{ endTime.slice(0, 10) }}
                </p>
                <button
                  @click="goSettingRoom"
                  type="button"
                  class="place-content-center inline-block rounded bg-indigo-500 mx-3 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                >
                  입장
                </button>
              </div>
              <div></div>
            </div>
          </div>
        </div>
        <!-- # 리스트 -->
        <div class="flex flex-col pt-5">
          <div class="flex flex-col justify-center">
            <div class="inline-block w-auto py-2 space-y-4 sm:px-6 lg:px-8">
              <div class="flex flex-wrap justify-between px-5">
                <p class="text-2xl font-medium">오늘 예정된 면접</p>
              </div>
              <div
                class="flex items-center justify-center overflow-hidden rounded-md shadow-lg w-fit"
              >
                <table class="min-w-full">
                  <thead class="bg-white border-b">
                    <tr>
                      <th
                        scope="col"
                        class="px-6 py-4 text-sm font-medium text-gray-900 text-middle"
                      >
                        전형명
                      </th>
                      <th
                        scope="col"
                        class="px-6 py-4 text-sm font-medium text-gray-900 text-middle"
                      >
                        면접일
                      </th>
                      <th
                        scope="col"
                        class="px-6 py-4 text-sm font-medium text-gray-900 text-middle"
                      >
                        면접시각
                      </th>
                      <th
                        scope="col"
                        class="px-6 py-4 text-sm font-medium text-gray-900 text-middle"
                      >
                        전형차수
                      </th>
                      <th
                        scope="col"
                        class="px-6 py-4 text-sm font-medium text-gray-900 text-middle"
                      ></th>
                    </tr>
                  </thead>
                  <tbody
                    v-for="(item, index) in scheduledList"
                    :content="item.value"
                    :key="index"
                  >
                    <tr
                      class="transition duration-300 ease-in-out bg-white border-b hover:bg-gray-100"
                    >
                      <td
                        class="px-6 py-4 text-lg font-medium text-gray-900 whitespace-nowrap"
                      >
                        {{ item.name }}
                      </td>
                      <td
                        class="px-6 py-4 text-lg font-light text-gray-900 whitespace-nowrap"
                      >
                        {{ item.startTime.slice(0, 10) }}
                      </td>
                      <td
                        class="px-6 py-4 text-lg font-light text-gray-900 whitespace-nowrap"
                      >
                        {{ item.startTime.slice(11, 16) }} ~
                        {{ item.endTime.slice(11, 16) }}
                      </td>
                      <td
                        class="px-6 py-4 text-lg font-light text-gray-900 whitespace-nowrap"
                      >
                        <div class="mx-10">
                          {{ item.interviewRound }}
                        </div>
                      </td>
                      <td
                        class="px-6 py-4 text-lg font-light text-gray-900 whitespace-nowrap"
                      >
                        <div class="flex items-center justify-center">
                          <button
                            @click="goSettingRoom"
                            type="button"
                            class="place-content-center inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                          >
                            입장
                          </button>
                        </div>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';

import EvaluatorHeader from '@/components/EvaluatorHeader.vue';
import EvaluatorNavbar from '@/components/EvaluatorNavbar.vue';

import router from '@/router';
const goSettingRoom = () => router.push({ name: 'InterviewRoom' });

const name = ref('');
const startTime = ref('');
const endTime = ref('');

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const evaluatorsName = ref('');
const getevaluatorsName = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/evaluators/me`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log(res.data);
      evaluatorsName.value = res.data.name;
    })
    .catch(err => {
      console.log(err.message);
    });
};
const scheduled = ref(null);
const scheduledList = ref(null);

const getRecentlySchedule = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/evaluators/next`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      scheduled.value = res;
      console.log(scheduled.value.data.name);
      name.value = scheduled.value.data.name;
      startTime.value = scheduled.value.data.startTime;
      endTime.value = scheduled.value.data.endTime;
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

onMounted(() => {
  getRecentlySchedule();
  getScheduleList();
  getevaluatorsName();
});
</script>

<style lang="scss" scoped></style>
