<template>
  <div>
    <CorporateHeader></CorporateHeader>
    <div class="flex">
      <CorporateNavbar></CorporateNavbar>
      <div class="w-screen">
        <div class="flex flex-col space-y-10">
          <nav class="flex flex-wrap justify-between p-8 text-gray-800">
            <h1 class="font-bold">네이버 님, 안녕하세요</h1>
            <h3 class="font-bold text-gray-500">DASHBOARD</h3>
          </nav>
          <p class="px-10 text-xl font-gray-900">새로운 전형을 생성하세요</p>
          <div class="flex items-center p-10">
            <div
              class="p-5 space-y-5 bg-white divide-y divide-gray-200 rounded-lg shadow-lg"
            >
              <div class="m-1">
                <p class="p-2 text-xl font-medium text-black">전형기간</p>
                <div class="flex flex-row items-end space-x-5">
                  <div class="flex flex-col p-2">
                    <p class="text-lg font-medium text-gray-900">시작일</p>
                    <input
                      class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                      v-model="startDate"
                      type="date"
                      id="startDate"
                      required="true"
                    />
                  </div>
                  <div class="pb-3">
                    <p class="text-xl font-regular">~</p>
                  </div>
                  <div class="flex flex-col p-2">
                    <p class="text-lg font-medium text-gray-900">종료일</p>
                    <input
                      class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                      v-model="endDate"
                      type="date"
                      id="endDate"
                      required=""
                    />
                  </div>
                </div>
              </div>
              <div class="m-1">
                <p class="p-2 text-xl font-medium text-black">회사</p>
                <div class="flex flex-row space-x-5">
                  <div class="flex flex-col p-2">
                    <p class="text-lg font-medium text-gray-900">전형명</p>
                    <input
                      class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                      v-model="name"
                      type="text"
                      id="name"
                      required="true"
                    />
                  </div>
                  <div class="flex flex-col p-2">
                    <p class="text-lg font-medium text-gray-900">전형차수</p>
                    <input
                      class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                      v-model="interviewCount"
                      type="text"
                      id="interviewCount"
                      required="true"
                    />
                  </div>
                </div>
              </div>
              <div>
                <div class="flex justify-end pt-5">
                  <button
                    @click="createProcess()"
                    type="button"
                    class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                  >
                    프로세스 생성
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import CorporateHeader from '@/components/CorporateHeader.vue';
import CorporateNavbar from '@/components/CorporateNavbar.vue';

import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const router = useRouter();

const getToday = date => {
  const year = date.getFullYear();
  const month = ('0' + (1 + date.getMonth())).slice(-2);
  const day = ('0' + date.getDate()).slice(-2);
  return year + '-' + month + '-' + day;
};
const changeDateFormat = date => {
  return date + 'T00:00:00.000000';
};

const startDate = ref(getToday(new Date()));
const endDate = ref(null);
const name = ref('');
const interviewCount = ref(0);

const getProcessInfo = () => {
  const processInfo = {
    startDate: changeDateFormat(startDate.value),
    endDate: changeDateFormat(endDate.value),
    name: name.value,
    interviewCount: interviewCount.value,
  };
  return processInfo;
};

const createProcess = () => {
  const processInfo = JSON.stringify(getProcessInfo());
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .post(`${BASE_URL}/interview/process`, processInfo, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('process created! ', res.data);
      router.push({ name: 'CorporateManageProcess' });
    })
    .catch(err => {
      console.log(err.message);
    });
};
</script>

<style lang="scss" scoped></style>
