<template>
  <div>
    <!-- <CorporateHeader></CorporateHeader> -->
    <div>
      <!-- <CorporateNavbar></CorporateNavbar> -->
      <!-- 현재 프로세스 정보 -->
      <div>
        {{ currentProcessName }} | {{ currentProcessStartDate.slice(0, 10) }} ~
        {{ currentProcessEndDate.slice(0, 10) }} | 총
        {{ currentProcessInterviewCount }} 차
      </div>
    </div>
    <div class="flex">
      <div>
        <!-- 1, 2, 3차 탭 -->
        <div>
          <ul
            class="flex flex-wrap text-sm font-medium text-center text-gray-500 dark:text-gray-400"
          >
            <li class="mr-2" v-for="tabnumber in 3" :key="tabnumber">
              <a
                @click="getInterviewSchedule(tabnumber)"
                class="inline-block px-4 py-3 rounded-lg hover:text-gray-900 hover:bg-gray-100 dark:hover:bg-gray-800 dark:hover:text-white"
                aria-current="page"
                >{{ tabnumber }} 차</a
              >
            </li>
          </ul>
        </div>
        <!-- 해당 프로세스 해당 차수의 면접 일정들 -->
        <div
          v-for="schedule in currentSchedules"
          :key="schedule.id"
          @click.stop="goScheduleSetting(processId, schedule.id)"
        >
          <p>{{ schedule.name }}</p>
          <p>{{ schedule.date }}</p>
          <p>
            {{ schedule.startTime.slice(11, 16) }} ~
            {{ schedule.endTime.slice(11, 16) }}
          </p>
          <button @click.stop="deleteSchedule(currentStep, schedule.id)">
            삭제
          </button>
          <hr />
        </div>
      </div>
      <!-- 면접 일정 생성 -->
      <div>
        <h1>면접 일정 생성</h1>
        <button @click.stop="createInterview()">면접 생성</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import CorporateHeader from '@/components/CorporateHeader.vue';
import CorporateNavbar from '@/components/CorporateNavbar.vue';

import { ref, onMounted, watch } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';

const companyId = JSON.parse(localStorage.getItem('user')).id;
const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const route = useRoute();
const router = useRouter();
const processId = route.params.id;
const currentProcessName = ref('');
const currentProcessStartDate = ref('');
const currentProcessEndDate = ref('');
const currentProcessInterviewCount = ref(0);
const currentInterviewList = ref([]);
const currentStep = ref(0);

// 현재 프로세스 정보 가져오기
const getCurrentProcess = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/interview/process/${processId}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('currentProcess: ', res.data);
      currentProcessName.value = res.data.name;
      currentProcessStartDate.value = res.data.startDate;
      currentProcessEndDate.value = res.data.endDate;
      currentProcessInterviewCount.value = res.data.interviewCount;
      currentInterviewList.value = res.data.interviewList;
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 1, 2, 3차 탭 눌러 해당 차수의 면접 일정 조회
const currentSchedules = ref([]);
const getInterviewSchedule = interviewId => {
  currentInterviewId.value = interviewId;
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/interview/schedule/${interviewId}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('interviewSchedules: ', res.data);
      currentSchedules.value = res.data;
      currentStep.value = res.data.currentStep;
      console.log('currentInterviewId: ', currentInterviewId.value);
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 각 면접 일정을 통해 해당 면접 일정 상세 화면으로 이동
const goScheduleSetting = (processId, interviewId) => {
  router.push({
    name: 'CorporateManageInterviewSetting',
    params: { processid: processId, id: interviewId },
  });
};

// 각 면접 일정 삭제
const deleteSchedule = (currentStep, interviewRoomId) => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .delete(`${BASE_URL}/interview/schedule/${interviewRoomId}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('delete sucess!', res);
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 면접 일정 생성 ("applicants": [1,2,3], "endTime": "2021-11-09T11:44:30.327959", "evaluators": [1,2,3], "interviewId": 1, "name": "두나무 FE1-1", "startTime": "2021-11-09T11:44:30.327959"}

const evaluators = ref([]);
const evalutorCount = ref(0);
const applicants = ref([]);
const applicantCount = ref(0);

const getEvaluatorsInfo = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/companyusers/evaluators/list`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      // console.log('getEvaluators: ', res.data);
      evaluators.value = res.data.content;
      evalutorCount.value = res.data.totalElements;
      console.log('evaluators: ', evaluators.value);
      console.log('evalutorCount: ', evalutorCount.value);
    })
    .catch(err => {
      console.log(err.message);
    });
};

const getApplicantsInfo = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/companyusers/applicants/${route.params.id}/list`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      // console.log('getApplicants: ', res.data);
      applicants.value = res.data.content;
      applicantCount.value = res.data.totalElements;
      console.log('evaluators: ', applicants.value);
      console.log('evalutorCount: ', applicantCount.value);
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 1, 2, 3차탭 누를 때마다 해당 프로세스의 면접 아이디 (몇 차인지) 갱신 --> 해당 면접에 일정 생성용
const currentInterviewId = ref(0);
const currentInterview = ref(null);
const interviewName = ref('');
watch(currentInterviewId, (newvalue, oldvalue) => {
  // console.log('currentInterviewList: ', currentInterviewList.value);
  currentInterview.value = currentInterviewList.value.filter(
    interview => interview.id === currentInterviewId.value,
  );
  console.log('currentInterview: ', currentInterview.value);
  interviewName.value = currentInterview.value[0].name;
  console.log('interviewName: ', interviewName.value);
});
const startHour = ref('15');
const startMinute = ref('30');
const endHour = ref('16');
const endMinute = ref('00');

const makeInterviewStartTime = () => {
  return (
    currentProcessStartDate.value.slice(0, 10) +
    'T' +
    startHour.value +
    ':' +
    startMinute.value +
    ':00'
  );
};
const makeInterviewEndTime = () => {
  return (
    currentProcessEndDate.value.slice(0, 10) +
    'T' +
    startHour.value +
    ':' +
    startMinute.value +
    ':00'
  );
};

const createInterview = () => {
  const startTime = makeInterviewStartTime();
  const endTime = makeInterviewEndTime();
  console.log(startTime, endTime);
};

onMounted(() => {
  getCurrentProcess();
  getEvaluatorsInfo();
  getApplicantsInfo();
});
</script>

<style lang="scss" scoped></style>
