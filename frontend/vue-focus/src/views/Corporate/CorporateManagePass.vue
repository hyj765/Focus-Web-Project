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
          <p class="px-10 text-2xl font-medium">기업 전체전형</p>
          <p class="px-10 text-lg font-medium">
            <i class="text-indigo-600 bx bxs-check-circle"></i> 각 전형의
            합격자를 선택해주세요
          </p>
          <div class="space-y-5">
            <!-- 전형목록 -->
            <div
              v-for="(process, index) in processList"
              :content="process.value"
              :key="index"
              class="flex flex-row items-center justify-center space-x-10"
            >
              <div
                @click="goDetail(process.id)"
                class="flex flex-row items-center justify-center px-24 py-4 space-x-4 text-xl font-bold text-gray-600 bg-white rounded-md shadow-md hover:bg-gray-100"
              >
                <p class="text-2xl font-black">{{ process.name }}</p>
                <p>|</p>
                <p>
                  {{ process.startDate.slice(0, 10) }} ~
                  {{ process.endDate.slice(0, 10) }}
                </p>
                <p>|</p>
                <p>총 {{ process.interviewCount }} 차</p>
              </div>
            </div>
            <!-- 전형목록 -->
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
import { useRouter } from 'vue-router';
import axios from 'axios';
const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const processList = ref([]);
const getProgressprocess = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/companyusers/process/${user.id}`)
    .then(data => {
      console.log(data.data);
      processList.value = data.data;
      console.log(processList.value);
    })
    .catch(err => {
      console.log(err.data);
    });
};

const router = useRouter();
const goDetail = processId => {
  router.push({ name: 'CorporateManagePassDetail', params: { id: processId } });
};
onMounted(() => {
  getProgressprocess();
});
</script>

<style lang="scss" scoped></style>
