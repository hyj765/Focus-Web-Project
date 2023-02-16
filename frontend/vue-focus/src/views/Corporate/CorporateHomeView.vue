<template>
  <div>
    <CorporateHeader></CorporateHeader>
    <div class="flex">
      <CorporateNavbar></CorporateNavbar>
      <div class="w-screen">
        <!-- DASHBOARD -->
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
          <div class="flex space-x-2 justify-center">
            <div
              class="bg-white shadow-lg mx-auto w-2/5 max-w-full text-sm pointer-events-auto bg-clip-padding rounded-lg block"
            >
              <div
                class="bg-white flex justify-between items-center py-2 px-3 bg-clip-padding border-b border-gray-200 rounded-t-lg"
              >
                <p class="font-bold text-gray-500">현재 인원</p>
              </div>
              <!--         
        # 총 평가자 총 지원자
        -->
              <div
                class="space-x-3 flex flex-row justify-center p-3 text-lg bg-white rounded-b-lg break-words text-gray-700 text-center"
              >
                <p>총 평가자 {{ evaluatorsCount }}명</p>
                <p>|</p>
                <p>총 지원자 {{ applicantsCount }}</p>
              </div>
            </div>
          </div>
          <!-- 
    # 면접진행률
-->
          <!-- <div class="flex space-x-2 justify-center">
      <div
        class="bg-white shadow-lg mx-auto w-2/5 max-w-full text-sm pointer-events-auto bg-clip-padding rounded-lg block"
      >
        <div
          class="bg-white flex justify-between items-center py-2 px-3 bg-clip-padding border-b border-gray-200 rounded-t-lg"
        >
          <p class="font-bold text-gray-500">면접 진행률</p>
        </div>
        <div class="p-5">
          <div class="w-full bg-gray-200 rounded-full">
            <div
              class="bg-blue-600 text-xs font-medium text-blue-100 text-center p-0.5 leading-none rounded-l-full"
              style="width: 25%"
            >
              25%
            </div>
          </div>
        </div>
      </div>
    </div> -->
          <!--     
    # 예정된 면접
-->
          <div class="flex space-x-2 justify-center">
            <div
              class="bg-white shadow-lg mx-auto w-2/5 max-w-full text-sm pointer-events-auto bg-clip-padding rounded-lg block"
            >
              <div
                class="bg-white flex justify-between items-center py-2 px-3 bg-clip-padding border-b border-gray-200 rounded-t-lg"
              >
                <p class="font-bold text-gray-500">예정된 면접</p>
              </div>
              <!--         
        # 면접리스트
        -->
              <ul
                v-for="scheduledInterviewList in scheduledInterviewLists"
                :key="scheduledInterviewList.id"
              >
                <!-- # 면접리스트 1 -->
                <li
                  class="p-5 text-lg bg-white rounded-b-lg break-words text-gray-700"
                >
                  <section class="flex flex-row px-2 items-center">
                    <div class="flex flex-col space-y-2 w-1/2 justify-center">
                      <p class="text-xl font-bold">
                        {{ scheduledInterviewList.name }}
                      </p>
                      <p class="text-lg text-gray-600 font-regular">
                        현재
                        <strong
                          >{{ scheduledInterviewList.currentStep }}차</strong
                        >
                        / 총
                        <strong
                          >{{ scheduledInterviewList.interviewCount }}차</strong
                        >
                      </p>
                    </div>
                    <ul class="flex flex-col w-auto space-y-4">
                      <!-- 
              # 리스트 component
              -->

                      <li class="flex flex-row items-center">
                        <div
                          class="animate-bounce bg-blue-500 w-5 h-5 ring-1 ring-slate-900/5 shadow-lg rounded-full flex items-center justify-center"
                        >
                          <i class="bx bx-right-arrow-alt text-white"></i>
                        </div>
                        <div class="px-2">
                          <p>
                            {{ scheduledInterviewList.startDate.slice(0, 10) }}
                            ~
                            {{ scheduledInterviewList.endDate.slice(0, 10) }}
                          </p>
                        </div>
                      </li>
                      <!-- 
              #리스트 component end
              -->
                    </ul>
                  </section>
                </li>
              </ul>
              <!-- #리스트 끝 -->
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

import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { ref, onMounted } from 'vue';
import axios from 'axios';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const router = useRouter();
const store = useStore();

const logout = () => {
  store.dispatch('logout').then(() => {
    router.push({ name: 'Login' });
  });
};

// 총 평가자
const evaluatorsCount = ref(0);
const getEvaluators = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  // console.log(user.id);
  axios
    .get(`${BASE_URL}/companyusers/evaluatorCount/${user.id}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      // console.log(res.data);
      evaluatorsCount.value = res.data;
      // console.log(evaluatorsCount.value);
    });
};

// 총 지원자
const applicantsCount = ref(0);
const getApplicants = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  // console.log(user.id);
  axios
    .get(`${BASE_URL}/companyusers/applicantCount/${user.id}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      // console.log(res.data);
      applicantsCount.value = res.data;
      // console.log(applicantsCount.value);
    });
};

// 예정된 면접
const scheduledInterviewLists = ref(null);
const getScheduledInterviewLists = () => {
  const user = JSON.parse(localStorage.getItem('user'));

  axios
    .get(`${BASE_URL}/companyusers/process/${user.id}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log(res.data);
      scheduledInterviewLists.value = res.data;
      console.log(scheduledInterviewLists.value);
    });
};

onMounted(() => {
  getEvaluators();
  getApplicants();
  getScheduledInterviewLists();
});
</script>

<style lang="scss" scoped></style>
