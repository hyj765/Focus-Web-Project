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
      <div>
        <h1 @click="getEvaluatorCount(companyId)">test</h1>
      </div>
    </div>
  </div>
</template>

<script setup>
import CorporateHeader from '@/components/CorporateHeader.vue';
import CorporateNavbar from '@/components/CorporateNavbar.vue';

import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const route = useRoute();
const router = useRouter();
const currentProcessName = ref('');
const currentProcessStartDate = ref('');
const currentProcessEndDate = ref('');
const currentProcessInterviewCount = ref(0);
const currentInterviewList = ref([]);
const processId = route.params.id;
const currentStep = ref(0);

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
// interviewList 안에 있는 각 객체의 id값으로 해당 면접의 일정 api get 요청

const currentSchedules = ref([]);

const getInterviewSchedule = interviewId => {
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
    })
    .catch(err => {
      console.log(err.message);
    });
};

const goScheduleSetting = (processId, interviewId) => {
  router.push({
    name: 'CorporateManageInterviewSetting',
    params: { processid: processId, id: interviewId },
  });
};

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

const companyId = JSON.parse(localStorage.getItem('user')).id;
let evaluatorCount = 0;
let applicantCount = 0;
const getEvaluatorCount = id => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/companyusers/evaluatorCount/${id}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      evaluatorCount = res.data;
      console.log(evaluatorCount);
    })
    .catch(err => {
      console.log(err.message);
    });
};
const getApplicantCount = id => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/companyusers/applicantCount/${id}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      applicantCount = res.data;
      console.log(applicantCount);
    })
    .catch(err => {
      console.log(err.message);
    });
};

const getEvaluatorsInfo = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  // let evaluatorCount = 0;
  // let remainder = 0;
  axios
    .get(`${BASE_URL}/companyusers/evaluators/list`, {
      // params: {
      //   size: 15,
      //   page: currentPage.value,
      // },
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('getEvaluators: ', res.data);
    })
    .catch(err => {
      console.log(err.message);
    });
};

onMounted(() => {
  getCurrentProcess();
  getEvaluatorCount(companyId);
  getApplicantCount(companyId);
  getEvaluatorsInfo();
});
</script>

<style lang="scss" scoped></style>
