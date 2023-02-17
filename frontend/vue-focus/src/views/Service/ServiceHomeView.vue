<template>
  <div>
    <ServiceHeader></ServiceHeader>
    <div class="flex">
      <ServiceNavbar></ServiceNavbar>

      <!-- DASHBOARD -->

      <div class="w-screen">
        <div class="flex flex-col space-y-10">
          <nav class="flex flex-wrap justify-between p-8 text-gray-800">
            <h1 class="font-bold">네이버 님, 안녕하세요</h1>
            <h3 class="font-bold text-gray-500">DASHBOARD</h3>
          </nav>

          <!-- 현재 인원 -->
          <div
            class="flex flex-row items-center justify-center px-20 space-x-10"
          >
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
                  {{ currentInterviewCount }}개
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
                  {{ currentContractCount }} 개
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
                  {{ todayProcessCount }} 개
                </div>
              </div>
            </div>

            <!-- # 전체 계약 중인 기업리스트 -->
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
                          <!-- <th
                            scope="col"
                            class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                          >
                            기업로고
                          </th> -->
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
                        v-for="(corporate, index) in corporatesInContract"
                        :key="corporate.id"
                      >
                        <tr
                          class="transition duration-300 ease-in-out bg-white border-b hover:bg-gray-100"
                        >
                          <td
                            class="px-6 py-4 text-sm font-medium text-gray-900 whitespace-nowrap"
                          >
                            {{ index + 1 }}
                          </td>
                          <!-- <td
                            class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                          >
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              viewBox="0 0 488 512"
                              class="w-7 h-7"
                              style="color: #ea4335"
                            >
                              <path
                                fill="currentColor"
                                d="M488 261.8C488 403.3 391.1 504 248 504 110.8 504 0 393.2 0 256S110.8 8 248 8c66.8 0 123 24.5 166.3 64.9l-67.5 64.9C258.5 52.6 94.3 116.6 94.3 256c0 86.5 69.1 156.6 153.7 156.6 98.2 0 135-70.4 140.8-106.9H248v-85.3h236.1c2.3 12.7 3.9 24.9 3.9 41.4z"
                              />
                            </svg>
                          </td> -->
                          <td
                            class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                          >
                            {{ corporate.companyName }}
                          </td>
                          <td
                            class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                          >
                            {{ corporate.size }}
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
                          <!-- <th
                            scope="col"
                            class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                          >
                            기업로고
                          </th> -->
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
                          <!-- <td
                            class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                          >
                            <svg
                              xmlns="http://www.w3.org/2000/svg"
                              viewBox="0 0 488 512"
                              class="w-7 h-7"
                              style="color: #ea4335"
                            >
                              <path
                                fill="currentColor"
                                d="M488 261.8C488 403.3 391.1 504 248 504 110.8 504 0 393.2 0 256S110.8 8 248 8c66.8 0 123 24.5 166.3 64.9l-67.5 64.9C258.5 52.6 94.3 116.6 94.3 256c0 86.5 69.1 156.6 153.7 156.6 98.2 0 135-70.4 140.8-106.9H248v-85.3h236.1c2.3 12.7 3.9 24.9 3.9 41.4z"
                              />
                            </svg>
                          </td> -->
                          <td
                            v-for="name in corporateNamesInProcess"
                            :key="name.id"
                            class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                          >
                            {{ name }}
                          </td>
                          <td
                            class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                          >
                            <p
                              v-for="interview in interviews"
                              :key="interview.id"
                            >
                              {{ interview.processName }} |
                              {{ interview.startDate.slice(0, 10) }} ~
                              {{ interview.endDate.slice(0, 10) }}
                            </p>
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
    </div>
  </div>
</template>

<script setup>
import ServiceHeader from '@/components/ServiceHeader.vue';
import ServiceNavbar from '@/components/ServiceNavbar.vue';

import { ref, onMounted } from 'vue';
import axios from 'axios';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

// 현재 면접 진행중 기업 개수
const currentInterviewCount = ref(0);
const getCurrentInterviewCount = () => {
  axios
    .get(`${BASE_URL}/servicedashboard/proceeding/companies/count/`)
    .then(res => {
      // console.log(res.data);
      currentInterviewCount.value = res.data.count;
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 전체 계약 중인 기업 개수
const currentContractCount = ref(0);
const getCurrentContractCount = () => {
  axios
    .get(`${BASE_URL}/serviceusers/accounts/contracts/number`)
    .then(res => {
      // console.log(res.data);
      currentContractCount.value = res.data;
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 오늘 예정된 프로세스 개수
const todayProcessCount = ref(null);
const getTodayProcessCount = () => {
  axios
    .get(`${BASE_URL}/serviceusers/dashboard/process/number`)
    .then(res => {
      // console.log(res.data);
      todayProcessCount.value = res.data;
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 전체 계약 중인 기업 리스트
const corporatesInContract = ref([]);
const getCorporatesInContract = () => {
  axios
    .get(`${BASE_URL}/serviceusers/accounts/contracts`)
    .then(res => {
      console.log(res.data);
      corporatesInContract.value = res.data.slice(0, 5);
      // console.log(currentcontractlists.value);
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 현재 면접 진행 중 기업 리스트
const corporateNamesInProcess = ref(null);
const corporateInterviews = ref(null);
const interviews = ref(null);
const getCorporatesInProcess = () => {
  axios
    .get(`${BASE_URL}/servicedashboard/proceeding/companies/`)
    .then(res => {
      // console.log(Object.keys(res.data));
      // console.log(Object.values(res.data));
      corporateNamesInProcess.value = Object.keys(res.data);
      corporateInterviews.value = Object.values(res.data);
      console.log(corporateNamesInProcess.value);
      console.log('corporateInterviews: ', corporateInterviews.value[0]);
      interviews.value = corporateInterviews.value[0];
      console.log('interviews: ', interviews.value);
      // for (interview of corporateInterviews.value[0]) {
      //   interviews.value.push(interview);
      // }
    })
    .catch(err => {
      console.log(err.message);
    });
};

onMounted(() => {
  getCurrentInterviewCount();
  getCurrentContractCount();
  getTodayProcessCount();
  getCorporatesInContract();
  getCorporatesInProcess();
});
</script>

<style lang="scss" scoped></style>
