<template>
  <div>
    <CorporateHeader></CorporateHeader>
    <div class="flex">
      <CorporateNavbar></CorporateNavbar>
      <div>
        <p class="px-10 text-xl font-gray-900">{{ name }}</p>
        <div class="flex items-center p-10">
          <div
            class="p-5 space-y-5 bg-white divide-y divide-gray-200 rounded-lg shadow-lg"
          >
            <div class="m-1">
              <p class="p-2 text-xl font-medium text-black">프로세스 정보</p>
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
                  @click="modifyProcess(currentProcessId)"
                  type="button"
                  class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                >
                  정보 수정
                </button>
              </div>
            </div>
          </div>
        </div>

        <!-- 면접 N차 생성 -->

        <div class="flex items-center p-10">
          <div
            class="p-5 space-y-5 bg-white divide-y divide-gray-200 rounded-lg shadow-lg"
          >
            <div class="m-1">
              <p class="p-2 text-xl font-medium text-black">세부전형 등록</p>
              <div class="flex flex-row items-end space-x-5">
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">시작일</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="interviewStartDate"
                    type="date"
                    id="interviewStartDate"
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
                    v-model="interviewEndDate"
                    type="date"
                    id="interviewEndDate"
                    required=""
                  />
                </div>
              </div>
            </div>
            <div class="m-1">
              <div class="flex flex-row space-x-5">
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">전형명</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="interviewName"
                    type="text"
                    id="interviewName"
                    required="true"
                  />
                </div>
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">전형차수</p>
                  <select
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border0indigo-500 focus:ring=indigo-500 sm:text-sm"
                    v-model="interviewStep"
                    id="interviewStep"
                  >
                    <option
                      v-for="interviewStep in interviewSteps"
                      :value="interviewStep.value"
                      :key="interviewStep.id"
                    >
                      {{ interviewStep.value }}
                    </option>
                  </select>
                </div>
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">평가지</p>
                  <select
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border0indigo-500 focus:ring=indigo-500 sm:text-sm"
                    v-model="evaluationSheet"
                    id="evaluationSheetName"
                  >
                    <option
                      v-for="interviewSheet in interviewSheets"
                      :value="interviewSheet"
                      :key="interviewSheet.id"
                    >
                      {{ interviewSheet.content }}
                    </option>
                  </select>
                </div>
              </div>
            </div>
            <div>
              <div class="flex justify-end pt-5">
                <button
                  @click="createInterview()"
                  type="button"
                  class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                >
                  전형 추가
                </button>
              </div>
            </div>
          </div>
        </div>

        <!-- 등록된 면접 -->

        <div v-if="interviewList">
          <div class="flex flex-col space-y-10">
            <div class="flex flex-col">
              <div class="flex flex-col justify-center">
                <div class="inline-block w-auto py-2 space-y-4 sm:px-6 lg:px-8">
                  <div class="overflow-hidden rounded-md shadow-lg">
                    <table class="min-w-full">
                      <thead class="bg-white border-b">
                        <tr>
                          <th
                            scope="col"
                            class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                          ></th>
                          <th
                            scope="col"
                            class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                          >
                            전형명
                          </th>
                          <th
                            scope="col"
                            class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                          >
                            전형기간
                          </th>
                          <th
                            scope="col"
                            class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                          >
                            전형차수
                          </th>
                        </tr>
                      </thead>
                      <tbody
                        v-for="(interview, index) in interviewList"
                        :key="interview.id"
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
                            {{ interview.name }}
                          </td>
                          <td
                            class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                          >
                            {{ interview.startDate.slice(0, 10) }} ~
                            {{ interview.endDate.slice(0, 10) }}
                          </td>
                          <td
                            class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                          >
                            {{ interview.step }} 차
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
import CorporateHeader from '@/components/CorporateHeader.vue';
import CorporateNavbar from '@/components/CorporateNavbar.vue';

import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const route = useRoute();
const router = useRouter();

// 프로세스용 데이터
const currentStep = ref(1);
const startDate = ref('');
const endDate = ref('');
const currentProcessId = ref(0);
const interviewCount = ref(0);
const name = ref('');
const interviewList = ref([]);

const getCurrentProcess = () => {
  const processId = route.params.id;
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/interview/process/${processId}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('response: ', res.data);
      currentStep.value = res.data.currentStep;
      startDate.value = res.data.startDate.slice(0, 10);
      endDate.value = res.data.endDate.slice(0, 10);
      interviewCount.value = res.data.interviewCount;
      interviewList.value = res.data.interviewList;
      name.value = res.data.name;
      currentProcessId.value = res.data.id;
      console.log('interviewList: ', interviewList.value);
    })
    .catch(err => {
      console.log(err.message);
    });
};

const changeDateFormat = date => {
  return date + 'T00:00:00.000000';
};
const getProcessInfo = () => {
  const processInfo = {
    startDate: changeDateFormat(startDate.value),
    endDate: changeDateFormat(endDate.value),
    name: name.value,
    interviewCount: interviewCount.value,
  };
  return processInfo;
};

const modifyProcess = processId => {
  const processInfo = JSON.stringify(getProcessInfo());
  console.log(processInfo);
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .put(`${BASE_URL}/interview/process/${processId}`, processInfo, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
        'Content-Type': 'application/json',
      },
    })
    .then(res => {
      console.log('put success!');
      const resId = res.data.id;
      router.push({
        name: 'CorporateManageProcessDetail',
        params: { id: resId },
      });
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 입력한 평가지 이름 --> () --> 평가지 id
// 면접 N차 생성용 데이터
const interviewSheets = ref([]);
const interviewName = ref('');
const evaluationSheet = ref('');
const interviewSteps = ref([
  { id: 1, value: 1 },
  { id: 2, value: 2 },
  { id: 3, value: 3 },
]);
const interviewStep = ref(0);
const interviewStartDate = ref('');
const interviewEndDate = ref('');

const getSheets = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/evaluation/sheets/list/${user.id}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      interviewSheets.value = res.data;
      console.log('interviewSheets: ', interviewSheets.value);
    })
    .catch(err => {
      console.log(err.message);
    });
};

const getInterviewInfo = () => {
  const interviewInfo = {
    startDate: changeDateFormat(interviewStartDate.value),
    endDate: changeDateFormat(interviewEndDate.value),
    name: interviewName.value,
    step: interviewStep.value,
    evaluationSheetId: evaluationSheet.value.id,
  };
  return interviewInfo;
};

const createInterview = () => {
  const processId = route.params.id;
  const interviewInfo = getInterviewInfo();
  let interviewInfos = [];
  interviewInfos.push(interviewInfo);
  const interviewInfosJson = JSON.stringify(interviewInfos);
  console.log('interviewInfosJson: ', interviewInfosJson);
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .post(`${BASE_URL}/interview/round/${processId}`, interviewInfosJson, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log(res);
      getCurrentProcess();
    })
    .catch(err => {
      console.log(err.message);
    });
};

onMounted(() => {
  getSheets();
  getCurrentProcess();
});
</script>

<style lang="scss" scoped></style>
