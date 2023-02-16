<template>
  <div class="flex flex-none min-h-screen">
    <div class="flex flex-col w-56 overflow-hidden bg-white/50">
      <ul class="flex flex-col py-4 divide-y space-y-60">
        <div>
          <li @click="moveToApplicantHome()">
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
          <li>
            <a
              class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
              ><span
                class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                ><i class="bx bx-time-five"></i
              ></span>
              <span class="text-sm font-medium">
                {{ startTime.slice(11, 16) }} ~
                {{ endTime.slice(11, 16) }}
              </span>
            </a>
          </li>
        </div>
        <div>
          <li @click="moveToApplicantFAQ()">
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
import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const name = ref('');
const startTime = ref('');
const endTime = ref('');
const id = ref('');
const scheduled = ref(null);

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const router = useRouter();

const moveToApplicantHome = () => {
  router.push({ name: 'Applicant' });
};

const moveToApplicantFAQ = () => {
  router.push({ name: 'ApplicantFAQ' });
};

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
});
</script>

<style lang="scss" scoped></style>
