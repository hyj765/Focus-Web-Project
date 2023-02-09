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

      <EvaluateHomeRecentlySchedule></EvaluateHomeRecentlySchedule>
      <EvaluateHomeSchedule></EvaluateHomeSchedule>
    </div>
  </div>
</template>

<script setup>
import router from '@/router';
import EvaluateHomeRecentlySchedule from './EvaluateHomeRecentlySchedule.vue';
import EvaluateHomeSchedule from './EvaluateHomeSchedule.vue';

const goSettingRoom = () => router.push({ name: 'InterviewRoom' });
</script>

<style lang="scss" scoped></style>
