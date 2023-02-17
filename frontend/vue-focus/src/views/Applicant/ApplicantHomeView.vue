<template>
  <div>
    <ApplicantHeader></ApplicantHeader>
    <div class="flex">
      <ApplicantNavbar></ApplicantNavbar>
      <div class="w-screen">
        <div class="flex flex-col space-y-10">
          <!-- 
    # 네이버님 안녕하세요
    -->
          <nav class="flex flex-wrap justify-between px-8 py-4 text-gray-800">
            <h1 class="font-bold">{{ applicantsName }} 님, 안녕하세요</h1>
            <h3 class="font-bold text-gray-500">DASHBOARD</h3>
          </nav>
        </div>
        <div class="p-10">
          <p class="p-2 text-lg font-bold text-gray-500">
            <i class="pr-1 text-indigo-500 bx bxs-check-circle"></i>
            잠시후에 시작할 면접입니다
          </p>
          <div class="inline-block">
            <div
              class="flex flex-row pb-3 pl-2 pr-4 bg-white rounded-lg shadow-lg"
            >
              <div
                class="flex flex-col items-center w-auto h-auto text-gray-900"
              >
                <p
                  class="flex items-center justify-center p-4 text-2xl font-bold"
                >
                  {{ name }}
                </p>
                <p
                  class="flex items-center justify-center px-4 py-1 text-white bg-indigo-900 rounded-xl"
                >
                  {{ startTime.slice(11, 16) }} ~
                  {{ endTime.slice(11, 16) }}
                </p>
              </div>
              <div class="flex items-center justify-center">
                <p>
                  {{ startTime.slice(0, 10) }} ~
                  {{ endTime.slice(0, 10) }}
                </p>
                <button
                  type="button"
                  class="place-content-center inline-block rounded bg-indigo-500 mx-3 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                >
                  <router-link
                    :to="{
                      name: 'InterviewRoom',
                      params: { id: 7 },
                    }"
                  >
                    입장
                  </router-link>
                </button>
              </div>
              <div></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

import ApplicantHeader from '@/components/ApplicantHeader.vue';
import ApplicantNavbar from '@/components/ApplicantNavbar.vue';

const applicantsName = ref('');
const getapplicantsName = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/applicants/me`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log(res.data);
      applicantsName.value = res.data.name;
    })
    .catch(err => {
      console.log(err.message);
    });
};

const router = useRouter();

const name = ref('');
const startTime = ref('');
const endTime = ref('');
const id = ref('');
const scheduled = ref(null);

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const moveToInterviewRoom = () => {
  router.push({ name: 'InterviewRoom', params: id.value });
};

const applicantScheduled = ref([]);

const getApplicantRecentlySchedule = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/applicants/next`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      scheduled.value = res;
      name.value = scheduled.value.data.name;
      startTime.value = scheduled.value.data.startTime;
      endTime.value = scheduled.value.data.endTime;
      id.value = scheduled.value.data.id;
      console.log('id : ', id.value);
      console.log('scheduled_id : ', scheduled.value.data.id);
    });
};

onMounted(() => {
  getApplicantRecentlySchedule();
  getapplicantsName();
});
</script>

<style lang="scss" scoped></style>
