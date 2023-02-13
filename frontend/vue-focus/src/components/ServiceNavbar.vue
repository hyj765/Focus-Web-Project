<template>
  <div class="flex flex-none min-h-screen">
    <div class="flex flex-col w-56 overflow-hidden bg-white/50">
      <ul class="flex flex-col py-4 divide-y space-y-60">
        <div>
          <li @click="moveToServiceHome()">
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
          <li @click="moveToServiceAccount()">
            <a
              href="#"
              class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
            >
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                ><i class="bx bx-id-card"></i
              ></span>
              <span class="text-sm font-medium">계정관리</span>
            </a>
          </li>
        </div>
        <div>
          <li @click="moveToServiceNotice()">
            <a
              href="#"
              class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
            >
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                ><i class="bx bxs-megaphone"></i
              ></span>
              <span class="text-sm font-medium">공지기능</span>
            </a>
          </li>
          <li @click="moveToServiceFAQ()">
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
import { useStore } from 'vuex';

const router = useRouter();
const store = useStore();

const moveToServiceHome = () => {
  router.push({ name: 'Service' });
};

const moveToServiceAccount = () => {
  router.push({ name: 'ServiceAccount' });
};

const moveToServiceNotice = () => {
  router.push({ name: 'ServiceNotice' });
};

const moveToServiceFAQ = () => {
  router.push({ name: 'ServiceFAQ' });
};
</script>

<style lang="scss" scoped></style>
