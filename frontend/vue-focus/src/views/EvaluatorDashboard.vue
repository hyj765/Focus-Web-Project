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

      <div class="flex justify-center">
        <button
          type="button"
          class="text-white bg-gradient-to-r from-purple-500 via-purple-600 to-purple-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-purple-300 dark:focus:ring-purple-800 shadow-lg shadow-purple-500/50 dark:shadow-lg dark:shadow-purple-800/80 font-medium rounded-lg text-sm px-5 py-2.5 text-center mr-2 mb-2"
          @click="goSettingRoom"
        >
          입장
        </button>
      </div>
      <!-- 
# 현재 인원
-->
    </div>
  </div>
</template>

<script setup>
import { shallowRef } from 'vue';
import router from '@/router';
import { useRouter } from 'vue-router';

import InterviewRoom from './InterviewDisplay/InterviewRoom.vue';

const currentComp = shallowRef(InterviewRoom);
const changeCurrentComp = comp => (currentComp.value = comp);

const goSettingRoom = () => router.push({ name: 'InterviewRoom' });
</script>

<style lang="scss" scoped></style>
