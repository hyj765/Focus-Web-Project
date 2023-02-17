<template>
  <div>
    <!-- 
    # 네이버님 안녕하세요
    -->
    <nav class="flex flex-wrap justify-between p-8 text-gray-800">
      <h1 class="font-bold">네이버 님, 안녕하세요</h1>
      <h3 class="font-bold text-gray-500">Process</h3>
    </nav>
    <div class="flex justify-end mx-5">
      <button
        @click="emitProcessCreate()"
        type="button"
        class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
      >
        전형 추가
      </button>
    </div>
    <p class="px-10 text-2xl font-medium">예정된 전형</p>
    <div class="space-y-5">
      <!-- 전형목록 -->
      <div class="flex flex-row items-center justify-center space-x-10 my-5">
        <div
          class="flex flex-row items-center justify-center px-4 py-2 space-x-4 text-gray-600 bg-white rounded-md shadow-md"
        >
          <p class="text-lg font-bold">두나무 개발자 FE</p>
          <p>|</p>
          <p>2022.02.03 ~ 2022.03.05</p>
          <p>|</p>
          <p>총 3 차</p>
        </div>

        <!-- # 버튼 그룹 -->
        <div class="flex flex-row">
          <div class="flex justify-center space-x-2">
            <button
              type="button"
              class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-gray-400 rounded-full shadow-lg hover:bg-gray-500 hover:shadow-lg focus:bg-gray-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-gray-500 active:shadow-lg w-9 h-9"
            >
              <i class="text-xl bx bx-plus"></i>
            </button>
            <button
              type="button"
              class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-gray-400 rounded-full shadow-lg hover:bg-gray-500 hover:shadow-lg focus:bg-gray-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-gray-500 active:shadow-lg w-9 h-9"
            >
              <i class="text-xl bx bx-copy"></i>
            </button>
            <button
              type="button"
              class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-red-400 rounded-full shadow-lg hover:bg-red-500 hover:shadow-lg focus:bg-red-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-red-500 active:shadow-lg w-9 h-9"
            >
              <i class="text-xl bx bx-x"></i>
            </button>
          </div>
        </div>
      </div>
      <!-- 전형목록 -->
      <div class="flex flex-row items-center justify-center space-x-10">
        <div
          class="flex flex-row items-center justify-center px-4 py-2 space-x-4 text-gray-600 bg-white rounded-md shadow-md"
        >
          <p class="text-lg font-bold">두나무 개발자 FE</p>
          <p>|</p>
          <p>2022.02.03 ~ 2022.03.05</p>
          <p>|</p>
          <p>총 3 차</p>
        </div>

        <!-- # 버튼 그룹 -->
        <div class="flex flex-row">
          <div class="flex justify-center space-x-2">
            <!-- <button
              type="button"
              class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-gray-400 rounded-full shadow-lg hover:bg-gray-500 hover:shadow-lg focus:bg-gray-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-gray-500 active:shadow-lg w-9 h-9"
            >
              <i class="text-xl bx bx-plus"></i>
            </button> -->
            <!-- <button
              type="button"
              class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-gray-400 rounded-full shadow-lg hover:bg-gray-500 hover:shadow-lg focus:bg-gray-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-gray-500 active:shadow-lg w-9 h-9"
            >
              <i class="text-xl bx bx-copy"></i>
            </button> -->
            <button
              @click="emitProcessDetail(interview.id)"
              type="button"
              class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
            >
              전형정보 수정
            </button>
            <button
              @click="emitProcessRound(interview.id)"
              type="button"
              class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
            >
              세부전형 등록
            </button>
            <button
              @click="deleteProcess(interview.id)"
              type="button"
              class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-red-400 rounded-full shadow-lg hover:bg-red-500 hover:shadow-lg focus:bg-red-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-red-500 active:shadow-lg w-9 h-9"
            >
              <i class="text-xl bx bx-x"></i>
            </button>
          </div>
        </div>
      </div>
      <!-- <div class="flex flex-row items-center justify-center space-x-10">
        <div
          class="flex flex-row items-center justify-center px-4 py-2 space-x-4 text-gray-600 bg-transparent border-4 border-white rounded-md shadow-md opacity-80"
        >
          <p class="text-lg font-bold">두나무 개발자 FE</p>
          <p>|</p>
          <p>2022.02.03 ~ 2022.03.05</p>
          <p>|</p>
          <p>총 N 차</p>
        </div> -->

      <!-- # 버튼 그룹 -->
      <!-- <div class="flex flex-row">
          <div class="flex justify-center pr-20">
            <button
              type="button"
              class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-blue-400 rounded-full shadow-lg hover:bg-blue-500 hover:shadow-lg focus:bg-blue-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-500 active:shadow-lg w-9 h-9"
            >
              <i class="text-xl bx bx-check"></i>
            </button>
          </div>
        </div>
      </div> -->
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';

const emit = defineEmits(['process-create', 'process-detail', 'process-round']);
const emitProcessCreate = () => {
  emit('process-create');
};
const emitProcessDetail = processId => {
  emit('process-detail', processId);
};
const emitProcessRound = processId => {
  emit('process-round', processId);
};
const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const interviews = ref(null);

const getInterviewInfo = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/interview/process`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('interviews: ', res.data);
      interviews.value = res.data;
    });
};

const deleteProcess = processId => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .delete(`${BASE_URL}/interview/process/${processId}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('delete success!', res);
      getInterviewInfo();
    })
    .catch(err => {
      console.log(err.message);
    });
};

onMounted(() => {
  getInterviewInfo();
});
</script>

<style lang="scss" scoped></style>
