<template>
  <div class="flex flex-row">
    <div v-for="(i, key) in scheduledApplicants[props.roomid]" :key="i">
      <ul
        class="inline-block text-sm font-medium text-center text-gray-500 border-b border-gray-200"
      >
        <li class="mr-2">
          <button
            v-bind:class="{
              active: currentTab === scheduledApplicants[props.roomid][key].id,
            }"
            v-on:click="currentTab = scheduledApplicants[props.roomid][key].id"
            href="#"
            aria-current="page"
            class="inline-block p-4 text-blue-600 bg-gray-100 rounded-t-lg active"
          >
            {{ scheduledApplicants[props.roomid][key].id }} /
            {{ scheduledApplicants[props.roomid][key].name }}
          </button>
        </li>
        <div
          style="overflow: auto"
          class="absolute"
          v-show="currentTab == scheduledApplicants[props.roomid][key].id"
        >
          <EvaluatorInterviewSheetsItem
            :interviewRoomId="props.roomid"
            :applicantId="scheduledApplicants[props.roomid][key].id"
          ></EvaluatorInterviewSheetsItem>
        </div>
      </ul>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import EvaluatorInterviewSheetsItem from './EvaluatorInterviewSheetsItem.vue';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const evaluationSheets = ref([]);
const scheduledApplicants = ref([]);
let currentTab = ref('');

const props = defineProps({
  roomid: {
    Type: String,
    required: true,
  },
});

// const getEvaluationSheets = () => {
//   const user = JSON.parse(localStorage.getItem('user'));
//   axios
//     .get(`${BASE_URL}/evaluation/interview/${props.roomid}`, {
//       headers: {
//         Authorization: `Bearer ${user.accessToken}`,
//       },
//     })
//     .then(res => {
//       console.log('evaluationSheets.data : ', res.data);
//       evaluationSheets.value = res.data;
//     });
// };

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

onMounted(() => {
  // getEvaluationSheets();
  getScheduledApplicant();
});
</script>

<style lang="scss" scoped></style>
