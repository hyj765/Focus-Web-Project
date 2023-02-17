<template>
  <div>
    <CorporateHeader></CorporateHeader>
    <div class="flex">
      <CorporateNavbar></CorporateNavbar>
      <div>
        <nav class="flex flex-wrap justify-between p-8 text-gray-800">
          <h1 class="font-bold">네이버 님, 안녕하세요</h1>
          <h3 class="font-bold text-gray-500">Process</h3>
        </nav>
        <p class="px-10 text-2xl font-medium">예정된 전형</p>
        <div
          v-for="interview in interviews"
          :key="interview.id"
          class="space-y-5"
        >
          <!-- 면접목록 -->
          <div
            @click.stop="goInterviewList(interview.id)"
            class="flex flex-row items-center justify-center space-x-10 my-5"
          >
            <div
              class="flex flex-row items-center justify-center px-4 py-2 space-x-4 text-gray-600 bg-white hover:bg-gray-100 rounded-md shadow-md"
            >
              <p class="text-lg font-bold">{{ interview.name }}</p>
              <p>|</p>
              <p>
                {{ interview.startDate.slice(0, 10) }} ~
                {{ interview.endDate.slice(0, 10) }}
              </p>
              <p>|</p>
              <p>총 {{ interview.interviewCount }} 차</p>
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

import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const goInterviewList = processId => {
  router.push({
    name: 'CorporateManageInterviewList',
    params: { id: processId },
  });
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

onMounted(() => {
  getInterviewInfo();
});
</script>

<style lang="scss" scoped></style>
