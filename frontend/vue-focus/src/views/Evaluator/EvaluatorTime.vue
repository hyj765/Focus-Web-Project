<template>
  <div>
    <EvaluatorHeader></EvaluatorHeader>
    <div class="flex">
      <EvaluatorNavbar></EvaluatorNavbar>
      <div class="w-screen">
        <div
          v-for="(schedule, index) in scheduledList"
          :content="schedule.value"
          :key="index"
          class="flex flex-col space-y-10"
        >
          <!-- 
    # 네이버님 안녕하세요
    -->
          <div v-if="Number(props.id) === schedule.id">
            <nav class="flex flex-wrap justify-between px-8 py-4 text-gray-800">
              <h1 class="font-bold">{{ evaluatorsName }} 님, 안녕하세요</h1>
              <h3 class="font-bold text-gray-500">Time</h3>
            </nav>
            <div class="flex">
              <div class="px-4 py-2 ml-10 text-white bg-indigo-900 rounded-lg">
                {{ schedule.startTime.slice(11, 16) }} ~
                {{ schedule.endTime.slice(11, 16) }} | 면접대기실
              </div>
            </div>
            <div class="flex flex-col items-center justify-center">
              <div
                class="flex flex-row w-auto h-auto pb-1 pl-2 pr-4 bg-white rounded-lg shadow-lg"
              >
                <div
                  class="flex flex-col items-center w-auto h-auto p-4 text-gray-900"
                >
                  <p
                    class="flex items-center justify-center px-4 py-1 text-white bg-indigo-900 rounded-xl"
                  >
                    {{ schedule.startTime.slice(11, 16) }} ~
                    {{ schedule.endTime.slice(11, 16) }}
                  </p>
                  <p
                    class="flex items-center justify-center p-2 text-2xl font-bold"
                  >
                    {{ schedule.name }}
                  </p>
                </div>
                <ul class="flex flex-col w-auto pt-4 space-y-4">
                  <!-- 
              # 리스트 component
              -->
                  <li class="flex flex-row items-center">
                    <div
                      class="flex items-center justify-center w-5 h-5 bg-indigo-500 rounded-full shadow-lg ring-1 ring-slate-900/5"
                    >
                      <i class="text-white bx bx-right-arrow-alt"></i>
                    </div>
                    <div class="px-2">
                      <p>
                        {{ schedule.startTime.slice(0, 10) }} ~
                        {{ schedule.endTime.slice(0, 10) }}
                      </p>
                    </div>
                  </li>
                  <!-- 
              #리스트 component end
              -->
                </ul>
                <div class="flex items-center justify-center p-2">
                  <button
                    type="button"
                    class="place-content-center inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                  >
                    <router-link
                      :to="{
                        name: 'InterviewRoom',
                        params: { id: schedule.id },
                      }"
                    >
                      입장
                    </router-link>
                  </button>
                </div>
              </div>
            </div>

            <div>
              <!-- scheduledApplicants -->
              <div
                class="max-w-2xl px-4 mx-auto sm:py-10sm:px-6 lg:max-w-7xl lg:px-8"
              >
                <p class="pb-5 text-2xl font-medium">평가 예정 지원자</p>

                <div
                  class="grid grid-cols-1 gap-y-10 gap-x-6 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 xl:gap-x-8"
                >
                  <a
                    v-for="(i, key) in scheduledApplicants[props.id]"
                    :key="i"
                    class="group"
                  >
                    <div class="w-full overflow-hidden bg-gray-200 rounded-lg">
                      <img
                        src="~@/assets/오상식.jpg"
                        alt=""
                        class="object-cover object-center w-full h-full group-hover:opacity-75"
                      />
                    </div>
                    <p class="flex flex-row">
                      {{ scheduledApplicants[props.id][key].name }} <br />
                      {{ scheduledApplicants[props.id][key].tel }} <br />
                      {{ scheduledApplicants[props.id][key].email }} <br />
                      {{ scheduledApplicants[props.id][key].major }} <br />
                      학점 : {{ scheduledApplicants[props.id][key].credit }} /
                      {{ scheduledApplicants[props.id][key].totalCredit }}
                    </p>
                  </a>
                </div>
              </div>
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

import EvaluatorHeader from '@/components/EvaluatorHeader.vue';
import EvaluatorNavbar from '@/components/EvaluatorNavbar.vue';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const scheduledList = ref([]);
const scheduledApplicants = ref([]);

const props = defineProps({
  id: String,
});

const getScheduleList = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/evaluators/list/`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      // console.log('res.data: ', res.data);
      scheduledList.value = res.data['2023-02-17'];
      // console.log('scheduledList : ', scheduledList.value);
      // console.log('>>>>>>>>>>>>>. id: ', scheduledList.value.id);
    });
};

const getScheduledApplicant = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/evaluators/applicants`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(response => {
      console.log('response.data : ', response.data);
      scheduledApplicants.value = response.data;
      console.log('scheduledApplicants.value ', scheduledApplicants.value);
    });
};
const evaluatorsName = ref('');
const getevaluatorsName = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/evaluators/me`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log(res.data);
      evaluatorsName.value = res.data.name;
    })
    .catch(err => {
      console.log(err.message);
    });
};
onMounted(() => {
  getScheduleList();
  getScheduledApplicant();
  getevaluatorsName();
});
</script>

<style lang="scss" scoped></style>
