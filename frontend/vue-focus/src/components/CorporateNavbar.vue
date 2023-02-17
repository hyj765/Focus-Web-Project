<template>
  <div class="flex flex-none min-h-screen">
    <div class="flex flex-col w-56 overflow-hidden bg-white/50">
      <ul class="flex flex-col py-4 space-y-20 divide-y">
        <div>
          <li @click="moveToCorporateHome()">
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
        </div>
        <div>
          <li @click="moveToCorporateEvaluator()">
            <a
              href="#"
              class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
            >
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                ><i class="bx bxs-user-voice"></i
              ></span>
              <span class="text-sm font-medium">1 평가자 관리</span>
            </a>
          </li>
          <li @click="moveToCorporateProcess()">
            <a
              href="#"
              class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
            >
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                ><i class="bx bx-list-ol"></i
              ></span>
              <span class="text-sm font-medium">2 프로세스 관리</span>
            </a>
          </li>
          <li @click="moveToCorporateApplicant()">
            <a
              href="#"
              class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
            >
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                ><i class="bx bxs-user-detail"></i
              ></span>
              <span class="text-sm font-medium">3 지원자 관리</span>
            </a>
          </li>
          <li @click="moveToCorporateInterview()">
            <a
              href="#"
              class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
            >
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                ><i class="bx bx-calendar"></i
              ></span>
              <span class="text-sm font-medium">4 면접일정 관리</span>
            </a>
          </li>
          <li @click="moveToCorporatePass()">
            <a
              href="#"
              class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
            >
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                ><i class="bx bxs-user-plus"></i
              ></span>
              <span class="text-sm font-medium">5 합격자 관리</span>
            </a>
          </li>
        </div>
        <div>
          <li @click="moveToCorporateSheet()">
            <a
              href="#"
              class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
            >
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                ><i class="bx bx-spreadsheet"></i
              ></span>
              <span class="text-sm font-medium">평가지 관리</span>
            </a>
          </li>
          <li @click="moveToCorporateStatistics()">
            <a
              href="#"
              class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
            >
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                ><i class="bx bxs-doughnut-chart"></i
              ></span>
              <span class="text-sm font-medium">합격자 통계 조회</span>
            </a>
          </li>
          <li @click="moveToCorporateNotice()">
            <a
              href="#"
              class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
            >
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                ><i class="bx bxs-megaphone"></i
              ></span>
              <span class="text-sm font-medium">공지게시판</span>
            </a>
          </li>
          <li @click="moveToCorporateFAQ()">
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
import { useRouter } from 'vue-router';

const router = useRouter();

const moveToCorporateHome = () => {
  router.push({ name: 'Corporate' });
};

const moveToCorporateEvaluator = () => {
  router.push({ name: 'CorporateManageEvaluator' });
};

const moveToCorporateProcess = () => {
  router.push({ name: 'CorporateManageProcess' });
};

const moveToCorporateApplicant = () => {
  router.push({ name: 'CorporateManageApplicant' });
};

const moveToCorporateInterview = () => {
  router.push({ name: 'CorporateManageInterview' });
};

const moveToCorporatePass = () => {
  router.push({ name: 'CorporateManagePass' });
};

const moveToCorporateSheet = () => {
  router.push({ name: 'CorporateSheet' });
};

const moveToCorporateStatistics = () => {
  router.push({ name: 'CorporateStatistics' });
};

const moveToCorporateNotice = () => {
  router.push({ name: 'CorporateNotice' });
};

const moveToCorporateFAQ = () => {
  router.push({ name: 'CorporateFAQ' });
};
</script>

<style lang="scss" scoped></style>
