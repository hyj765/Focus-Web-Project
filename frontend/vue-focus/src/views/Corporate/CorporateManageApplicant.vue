<template>
  <div>
    <CorporateHeader></CorporateHeader>
    <div class="flex">
      <CorporateNavbar></CorporateNavbar>
      <div class="w-screen">
        <div class="flex flex-col space-y-10">
          <!-- 
          # 네이버님 안녕하세요
          -->
          <nav class="flex flex-wrap justify-between p-8 text-gray-800">
            <h1 class="font-bold">{{ companyUserName }} 님, 안녕하세요</h1>
            <h3 class="font-bold text-gray-500">Applicant</h3>
          </nav>
          <p class="px-10 text-2xl font-medium">예정된 전형</p>
          <p class="px-10 pt-1 text-gray-700 font-regular">
            각각의 전형에 지원자를 배정해주세요
          </p>
          <div class="space-y-5">
            <!-- 전형목록 -->
            <div
              @click="getInterviewApplicants(interview.id)"
              v-for="interview in interviews"
              :key="interview.id"
              class="flex flex-row items-center justify-center space-x-10"
            >
              <div
                class="flex flex-row items-center justify-center px-24 py-4 space-x-4 text-xl font-bold text-gray-600 bg-white rounded-md shadow-md hover:bg-gray-100"
              >
                <p class="text-2xl font-black">{{ interview.name }}</p>
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
  </div>
</template>

<script setup>
import CorporateHeader from '@/components/CorporateHeader.vue';
import CorporateNavbar from '@/components/CorporateNavbar.vue';

import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const store = useStore();
const router = useRouter();

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const interviews = ref(null);
const companyUserName = ref('');
const getCompanyUserName = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/companyusers/me`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log(res.data);
      companyUserName.value = res.data.companyName;
    })
    .catch(err => {
      console.log(err.message);
    });
};
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
const getInterviewApplicants = processId => {
  router.push({
    name: 'CorporateManageApplicantList',
    params: { id: processId },
  });
  store.dispatch('saveCurrentApplicantProcessId', processId);
};

onMounted(() => {
  getInterviewInfo();
  getCompanyUserName();
});
</script>

<style lang="scss" scoped></style>
