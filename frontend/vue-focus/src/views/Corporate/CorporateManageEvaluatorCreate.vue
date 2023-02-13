<template>
  <div>
    <CorporateHeader></CorporateHeader>
    <div class="flex">
      <CorporateNavbar></CorporateNavbar>
      <div>
        <nav class="flex flex-wrap justify-between p-8 text-gray-800">
          <h1 class="font-bold">네이버 님, 안녕하세요</h1>
          <h3 class="font-bold text-gray-500">DASHBOARD</h3>
        </nav>
        <p class="px-10 text-xl font-gray-900">평가자 계정을 생성하세요</p>
        <!-- <div class="flex justify-end mx-5">
          <button
            @click="copyEvaluatorForm()"
            type="button"
            class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
          >
            평가자 추가
          </button>
        </div> -->
        <div class="flex items-center p-10" id="evaluator-form">
          <div
            class="p-5 space-y-5 bg-white divide-y divide-gray-200 rounded-lg shadow-lg"
          >
            <div class="m-1">
              <p class="p-2 text-xl font-medium text-black">소속</p>
              <div class="flex flex-row space-x-5">
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">사원번호</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="code"
                    type="text"
                    id="code"
                    required="true"
                  />
                </div>
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">부서</p>
                  <select
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border0indigo-500 focus:ring=indigo-500 sm:text-sm"
                    v-model="department"
                    id="department"
                  >
                    <option
                      v-for="department in departments"
                      :key="department.id"
                      :value="department"
                    >
                      {{ department }}
                    </option>
                  </select>
                </div>
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">직책</p>
                  <select
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border0indigo-500 focus:ring=indigo-500 sm:text-sm"
                    v-model="position"
                    id="position"
                  >
                    <option
                      v-for="position in positions"
                      :value="position.value"
                      :key="position.id"
                    >
                      {{ position.value }}
                    </option>
                  </select>
                </div>
              </div>
            </div>
            <div class="m-1">
              <p class="p-2 text-xl font-medium text-black">담당자 정보</p>
              <div class="flex flex-row space-x-5">
                <div class="p-2">
                  <p class="text-lg font-medium text-gray-900">이름</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="name"
                    type="text"
                    id="name"
                    required="true"
                  />
                </div>
                <div class="p-2">
                  <p class="text-lg font-medium text-gray-900">이메일</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="email"
                    type="email"
                    id="email"
                    required="true"
                    placeholder="example@blackbunny.com"
                  />
                </div>
                <div class="p-2">
                  <p class="text-lg font-medium text-gray-900">전화번호</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="tel"
                    type="tel"
                    id="tel"
                    required="true"
                    placeholder="010-1234-5678"
                  />
                </div>
              </div>
            </div>
            <div>
              <div class="flex justify-end pt-5">
                <button
                  @click="createEvaluator()"
                  type="button"
                  class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                >
                  계정생성
                </button>
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
import axios from 'axios';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const store = useStore();
const router = useRouter();

const positions = ref([
  { id: 1, value: '사장' },
  { id: 2, value: '부사장' },
  { id: 3, value: '전무' },
  { id: 4, value: '상무' },
  { id: 5, value: '이사' },
  { id: 6, value: '부장' },
  { id: 7, value: '차장' },
  { id: 8, value: '과장' },
  { id: 9, value: '대리' },
  { id: 10, value: '주임' },
  { id: 11, value: '사원' },
]);
const departments = store.state.currentDepartments;

const code = ref('');
const department = ref('');
const email = ref('');
const name = ref('');
const position = ref('');
const tel = ref('');

const getEvaluatorInfo = () => {
  const evaluatorInfo = {
    code: code.value,
    department: department.value,
    email: email.value,
    name: name.value,
    position: position.value,
    tel: tel.value,
  };
  return evaluatorInfo;
};

const createEvaluator = () => {
  const evaluatorInfo = getEvaluatorInfo();
  let evaluatorInfos = [];
  evaluatorInfos.push(evaluatorInfo);
  const evaluatorInfosJson = JSON.stringify(evaluatorInfos);
  const user = JSON.parse(localStorage.getItem('user'));
  console.log(evaluatorInfo);
  console.log(evaluatorInfos);
  console.log(evaluatorInfosJson);
  console.log(user.accessToken);
  axios
    .post(`${BASE_URL}/companyusers/evaluators`, evaluatorInfosJson, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log(res.data);
      router.push({ name: 'CorporateManageEvaluator' });
    })
    .catch(err => {
      console.log(err.message);
    });
};

// let evalutorFormIdNum = 0;
// const copyEvaluatorForm = () => {
//   const evaluatorForm = document.getElementById('evaluator-form');
//   const newEvaluatorForm = evaluatorForm.cloneNode(true);
//   evalutorFormIdNum++;
//   newEvaluatorForm.id = 'copy-evaluator-form' + evalutorFormIdNum;
//   evaluatorForm.after(newEvaluatorForm);
// };

onMounted(() => {
  console.log(store.state.currentDepartments);
  console.log(departments);
});
</script>

<style lang="scss" scoped></style>
