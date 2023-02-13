<template>
  <div>
    <div class="flex flex-col space-y-10">
      <!-- 
    # 네이버님 안녕하세요
    -->
      <nav class="flex flex-wrap justify-between p-8 text-gray-800">
        <h1 class="font-bold">네이버 님, 안녕하세요</h1>
        <h3 class="font-bold text-gray-500">DASHBOARD</h3>
      </nav>
      <!-- 
# 현재 인원
-->
      <div class="flex flex-row items-center justify-center px-20 space-x-10">
        <div class="flex flex-col space-y-6">
          <div
            class="justify-center w-40 text-sm bg-white rounded-lg shadow-lg"
          >
            <div
              class="flex items-center justify-between px-3 py-2 bg-white border-b border-gray-200 rounded-t-lg bg-clip-padding"
            >
              <p class="font-bold text-gray-500">현재 면접 진행 중 기업</p>
            </div>
            <div
              class="p-3 text-lg text-center text-gray-700 break-words bg-white rounded-b-lg"
            >
              {{ currentinterview }}개
            </div>
          </div>
          <div
            class="justify-center w-40 text-sm bg-white rounded-lg shadow-lg"
          >
            <div
              class="flex items-center justify-between px-3 py-2 bg-white border-b border-gray-200 rounded-t-lg bg-clip-padding"
            >
              <p class="font-bold text-gray-500">전체 계약 중인 기업</p>
            </div>
            <div
              class="p-3 text-lg text-center text-gray-700 break-words bg-white rounded-b-lg"
            >
              {{ currentcontract }} 개
            </div>
          </div>
          <div
            class="justify-center w-40 text-sm bg-white rounded-lg shadow-lg"
          >
            <div
              class="flex items-center justify-between px-3 py-2 bg-white border-b border-gray-200 rounded-t-lg bg-clip-padding"
            >
              <p class="font-bold text-gray-500">오늘 예정된 프로세스</p>
            </div>
            <div
              class="p-3 text-lg text-center text-gray-700 break-words bg-white rounded-b-lg"
            >
              {{ todayprocess }} 개
            </div>
          </div>
        </div>
        <!-- # 전체계약중인기업리스트 -->
        <div class="flex flex-col">
          <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
            <div class="inline-block min-w-full py-2 sm:px-6 lg:px-8">
              <div class="overflow-hidden rounded-md shadow-lg">
                <table class="min-w-full">
                  <thead class="bg-white border-b">
                    <tr>
                      <th
                        scope="col"
                        class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                      >
                        #
                      </th>
                      <th
                        scope="col"
                        class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                      >
                        기업로고
                      </th>
                      <th
                        scope="col"
                        class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                      >
                        기업명
                      </th>
                      <th
                        scope="col"
                        class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                      >
                        기업규모
                      </th>
                    </tr>
                  </thead>
                  <tbody
                    v-for="(currentcontractlist, index) in currentcontractlists"
                    :key="currentcontractlist.id"
                  >
                    <tr
                      class="transition duration-300 ease-in-out bg-white border-b hover:bg-gray-100"
                    >
                      <td
                        class="px-6 py-4 text-sm font-medium text-gray-900 whitespace-nowrap"
                      >
                        {{ index + 1 }}
                      </td>
                      <td
                        class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                      >
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          viewBox="0 0 488 512"
                          class="w-7 h-7"
                          style="color: #ea4335"
                        >
                          <!--! Font Awesome Pro 6.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. -->
                          <path
                            fill="currentColor"
                            d="M488 261.8C488 403.3 391.1 504 248 504 110.8 504 0 393.2 0 256S110.8 8 248 8c66.8 0 123 24.5 166.3 64.9l-67.5 64.9C258.5 52.6 94.3 116.6 94.3 256c0 86.5 69.1 156.6 153.7 156.6 98.2 0 135-70.4 140.8-106.9H248v-85.3h236.1c2.3 12.7 3.9 24.9 3.9 41.4z"
                          />
                        </svg>
                      </td>
                      <td
                        class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                      >
                        {{ currentcontractlist.companyName }}
                      </td>
                      <td
                        class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                      >
                        {{ currentcontractlist.size }}
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
        <!-- # 현재 면접 중인 기업 -->
        <div class="flex w-full">
          <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
            <div class="inline-block min-w-full py-2 sm:px-6 lg:px-8">
              <div class="overflow-hidden rounded-md shadow-lg">
                <table class="min-w-full">
                  <thead class="bg-white border-b">
                    <tr>
                      <th
                        scope="col"
                        class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                      >
                        #
                      </th>
                      <th
                        scope="col"
                        class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                      >
                        기업로고
                      </th>
                      <th
                        scope="col"
                        class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                      >
                        기업명
                      </th>
                      <th
                        scope="col"
                        class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                      >
                        프로세스 일정
                      </th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr
                      class="transition duration-300 ease-in-out bg-white border-b hover:bg-gray-100"
                    >
                      <td
                        class="px-6 py-4 text-sm font-medium text-gray-900 whitespace-nowrap"
                      >
                        1
                      </td>
                      <td
                        class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                      >
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          viewBox="0 0 488 512"
                          class="w-7 h-7"
                          style="color: #ea4335"
                        >
                          <!--! Font Awesome Pro 6.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. -->
                          <path
                            fill="currentColor"
                            d="M488 261.8C488 403.3 391.1 504 248 504 110.8 504 0 393.2 0 256S110.8 8 248 8c66.8 0 123 24.5 166.3 64.9l-67.5 64.9C258.5 52.6 94.3 116.6 94.3 256c0 86.5 69.1 156.6 153.7 156.6 98.2 0 135-70.4 140.8-106.9H248v-85.3h236.1c2.3 12.7 3.9 24.9 3.9 41.4z"
                          />
                        </svg>
                      </td>
                      <td
                        class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                      >
                        Google
                      </td>
                      <td
                        class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                      >
                        <p>1차 2022.02.15 ~ 2022.12.14</p>
                        <p>1차 2022.02.15 ~ 2022.12.14</p>
                        <p>1차 2022.02.15 ~ 2022.12.14</p>
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
import { ref, onMounted, shallowRef } from 'vue';
import axios from 'axios';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

// 현재 면접 진행중 기업 개수
const currentinterview = ref(null);
const getCurrentInterview = () => {
  axios
    .get(`${BASE_URL}/servicedashboard/proceeding/companies/count/`)
    .then(res => {
      // console.log(res.data);
      currentinterview.value = res.data.count;
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 전체 계약 중인 기업 개수
const currentcontract = ref(null);
const getCurrentContract = () => {
  axios
    .get(`${BASE_URL}/serviceusers/accounts/contracts/number`)
    .then(res => {
      // console.log(res.data);
      currentcontract.value = res.data;
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 오늘 예정된 프로세스 개수
const todayprocess = ref(null);
const getTodayProcess = () => {
  axios
    .get(`${BASE_URL}/serviceusers/dashboard/process/number`)
    .then(res => {
      // console.log(res);
      todayprocess.value = res.data;
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 전체 계약 중인 기업 리스트
const currentcontractlists = ref(null);
const getCurrentContractLists = () => {
  axios
    .get(`${BASE_URL}/serviceusers/accounts/contracts`)
    .then(res => {
      // console.log(res.data);
      currentcontractlists.value = res.data.slice(0, 5);
      // console.log(currentcontractlists.value);
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 현재 면접 진행 중 기업 리스트
const currentinterviewList = ref(null);
const getCurrentInterviewList = () => {
  axios
    .get(`${BASE_URL}/serviceusers/dashboard/proceeding/companies`)
    .then(res => {
      console.log(res);
    })
    .catch(err => {
      console.log(err.message);
    });
};

onMounted(() => {
  getCurrentInterview();
  getCurrentContract();
  getTodayProcess();
  getCurrentContractLists();
  getCurrentInterviewList();
});
</script>

<style lang="scss" scoped></style>
