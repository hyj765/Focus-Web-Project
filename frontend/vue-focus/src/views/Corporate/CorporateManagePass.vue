<template>
  <div>
    <CorporateHeader></CorporateHeader>
    <div class="flex">
      <CorporateNavbar></CorporateNavbar>
      <div class="w-screen">
        <div class="flex flex-col space-y-10">
          <nav class="flex flex-wrap justify-between p-8 text-gray-800">
            <h1 class="font-bold">삼성물산 님, 안녕하세요</h1>
            <h3 class="font-bold text-gray-500">Pass</h3>
          </nav>
          <p class="px-10 text-2xl font-medium">진행된 전형</p>
          <p class="px-10 text-lg font-medium">
            <i class="text-indigo-600 bx bxs-check-circle"></i> 각 전형의
            합격자를 선택해주세요
          </p>
          <div class="space-y-5">
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
</script>

<style lang="scss" scoped></style>
