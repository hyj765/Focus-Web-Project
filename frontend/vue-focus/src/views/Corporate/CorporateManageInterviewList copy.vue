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
            <h1 class="font-bold">네이버 님, 안녕하세요</h1>
            <h3 class="font-bold text-gray-500">Interview</h3>
          </nav>
          <p class="px-10 text-2xl font-medium">예정된 전형</p>
          <p class="px-10 pt-1 text-gray-700 font-regular">
            각각의 전형에 면접 일정을 추가해주세요
          </p>
          <div class="flex justify-center">
            <!-- 현재 프로세스 정보 -->
            <div>
              <div class="inline-block px-4 py-2 bg-indigo-200 rounded-lg">
                {{ currentProcessName }} |
                {{ currentProcessStartDate.slice(0, 10) }} ~
                {{ currentProcessEndDate.slice(0, 10) }} | 총
                {{ currentProcessInterviewCount }} 차
              </div>
              <div>
                <!-- 1, 2, 3차 탭 -->
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
                  <button
                    @click.stop="deleteSchedule(currentStep, schedule.id)"
                  >
                    삭제
                  </button>
                  <hr />
                </div>
              </div>
            </div>
          </div>
          <!-- 면접 일정 생성 -->
          <div>
            <h1>면접 일정 생성</h1>
            <button @click.stop="createInterview()">면접 생성</button>
          </div>
          <!-- 해당 차수의 면접 일정 생성란 (1차, 2차, 3차 선택해야 뜸) -->
          <div v-if="currentInterviewId">
            <!-- 면접 일정 생성 -->
            <div>
              <h1>면접 일정 생성</h1>
              <div>
                <p>면접명</p>
                <input type="text" v-model="interviewName" /><br />
                <input
                  v-model="interviewStartDate"
                  type="date"
                  id="interviewStartDate"
                  required="true"
                />
                <br />
                <!-- <input
                  v-model="interviewEndDate"
                  type="date"
                  id="interviewEndDate"
                  required="true"
                />
                <br /> -->
                <input type="text" v-model="startHour" />시
                <input type="text" v-model="startMinute" />분<br />
                <input type="text" v-model="endHour" />시
                <input type="text" v-model="endMinute" />분<br />

                <!-- 평가자 현황 -->
                <div>
                  <p>평가자</p>
                  <br />
                  <p
                    v-for="evaluator in currentEvaluatorState"
                    :key="evaluator.id"
                  >
                    {{ evaluator.name }}({{ evaluator.id }})
                  </p>
                </div>
                <!-- 지원자 현황 -->
                <div>
                  <p>지원자</p>
                  <br />
                  <p
                    v-for="applicant in currentApplicantState"
                    :key="applicant.id"
                  >
                    {{ applicant.name }}({{ applicant.id }})
                  </p>
                </div>

                <!-- 평가자 선택 -->
                <Menu as="div" class="relative inline-block text-left">
                  <div>
                    <MenuButton
                      class="inline-flex justify-center w-full px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md shadow-sm hover:bg-gray-50"
                    >
                      평가자
                      <ChevronDownIcon
                        class="w-5 h-5 ml-2 -mr-1"
                        aria-hidden="true"
                      />
                    </MenuButton>
                  </div>

                  <transition
                    enter-active-class="transition duration-100 ease-out"
                    enter-from-class="transform scale-95 opacity-0"
                    enter-to-class="transform scale-100 opacity-100"
                    leave-active-class="transition duration-75 ease-in"
                    leave-from-class="transform scale-100 opacity-100"
                    leave-to-class="transform scale-95 opacity-0"
                  >
                    <MenuItems
                      class="absolute right-0 z-10 w-56 mt-2 origin-top-right bg-white rounded-md shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none"
                    >
                      <div class="py-1">
                        <MenuItem
                          v-slot="{ active }"
                          v-for="evaluator in evaluators"
                          :key="evaluator.id"
                        >
                          <p
                            @click="clickEvaluator(evaluator)"
                            :class="[
                              active
                                ? 'bg-gray-100 text-gray-900'
                                : 'text-gray-700',
                              'block px-4 py-2 text-sm',
                            ]"
                          >
                            {{ evaluator.name }}
                          </p>
                        </MenuItem>
                      </div>
                    </MenuItems>
                  </transition>
                </Menu>

                <!-- 지원자 선택 -->
                <Menu as="div" class="relative inline-block text-left">
                  <div>
                    <MenuButton
                      class="inline-flex justify-center w-full px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md shadow-sm hover:bg-gray-50"
                    >
                      지원자
                      <ChevronDownIcon
                        class="w-5 h-5 ml-2 -mr-1"
                        aria-hidden="true"
                      />
                    </MenuButton>
                  </div>

                  <transition
                    enter-active-class="transition duration-100 ease-out"
                    enter-from-class="transform scale-95 opacity-0"
                    enter-to-class="transform scale-100 opacity-100"
                    leave-active-class="transition duration-75 ease-in"
                    leave-from-class="transform scale-100 opacity-100"
                    leave-to-class="transform scale-95 opacity-0"
                  >
                    <MenuItems
                      class="absolute right-0 z-10 w-56 mt-2 origin-top-right bg-white rounded-md shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none"
                    >
                      <div class="py-1">
                        <MenuItem
                          v-slot="{ active }"
                          v-for="applicant in applicants"
                          :key="applicant.id"
                        >
                          <p
                            @click="clickApplicant(applicant)"
                            :class="[
                              active
                                ? 'bg-gray-100 text-gray-900'
                                : 'text-gray-700',
                              'block px-4 py-2 text-sm',
                            ]"
                          >
                            {{ applicant.name }}
                          </p>
                        </MenuItem>
                      </div>
                    </MenuItems>
                  </transition>
                </Menu>
              </div>
              <button @click.stop="createInterview()">면접 생성</button>
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

import { Menu, MenuButton, MenuItem, MenuItems } from '@headlessui/vue';
import { ChevronDownIcon } from '@heroicons/vue/20/solid';

import { ref, onMounted, watch } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';
import { useStore } from 'vuex';

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

const store = useStore();
const evaluators = ref([]);
// const evalutorCount = ref(0);
const applicants = ref([]);
// const applicantCount = ref(0);

const saveEvaluators = () => {
  store.dispatch('saveEvaluators', evaluators);
};

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
      // evalutorCount.value = res.data.totalElements;
      console.log('evaluators: ', evaluators.value);
      // console.log('evalutorCount: ', evalutorCount.value);
    })
    .then(res => {
      saveEvaluators();
    })
    .catch(err => {
      console.log(err.message);
    });
};

const saveApplicants = () => {
  store.dispatch('saveApplicants', applicants);
};

const getApplicantsInfo = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/interview/interview/applicants/${route.params.id}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      // console.log('getApplicants: ', res.data);
      applicants.value = res.data;
      // applicantCount.value = res.data.totalElements;
      console.log('applicants: ', applicants.value);
      // console.log('evalutorCount: ', applicantCount.value);
    })
    .then(res => {
      saveApplicants();
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 1, 2, 3차탭 누를 때마다 해당 프로세스의 면접 아이디 (몇 차인지) 갱신 --> 해당 면접에 일정 생성용
const currentInterviewId = ref(0);
const currentInterview = ref(null);
const interviewName = ref('');
const interviewStartDate = ref('');
// const interviewEndDate = ref('');
watch(currentInterviewId, (newvalue, oldvalue) => {
  // console.log('currentInterviewList: ', currentInterviewList.value);
  currentInterview.value = currentInterviewList.value.filter(
    interview => interview.id === currentInterviewId.value,
  );
  console.log('currentInterview: ', currentInterview.value);
  interviewName.value = currentInterview.value[0].name;
  interviewStartDate.value = currentInterview.value[0].startDate.slice(0, 10);
  // interviewEndDate.value = currentInterview.value[0].endDate.slice(0, 10);
  console.log('interviewName: ', interviewName.value);
});

const startHour = ref('15');
const startMinute = ref('30');
const endHour = ref('16');
const endMinute = ref('00');

const makeInterviewStartTime = () => {
  return (
    interviewStartDate.value +
    'T' +
    startHour.value +
    ':' +
    startMinute.value +
    ':00'
  );
};
const makeInterviewEndTime = () => {
  return (
    // interviewEndDate.value + 'T' + endHour.value + ':' + endMinute.value + ':00'
    interviewStartDate.value +
    'T' +
    endHour.value +
    ':' +
    endMinute.value +
    ':00'
  );
};

// 입력란 현황
const currentEvaluatorState = ref([]);
const currentApplicantState = ref([]);

// 드롭다운 탭 누르면 추가, 삭제
const clickEvaluator = evaluator => {
  if (currentEvaluatorState.value.includes(evaluator)) {
    currentEvaluatorState.value = currentEvaluatorState.value.filter(
      checkEvaluator => checkEvaluator != evaluator,
    );
  } else {
    currentEvaluatorState.value.push(evaluator);
  }
};
const clickApplicant = applicant => {
  if (currentApplicantState.value.includes(applicant)) {
    currentApplicantState.value = currentApplicantState.value.filter(
      checkApplicant => checkApplicant != applicant,
    );
  } else {
    currentApplicantState.value.push(applicant);
  }
};

// 면접 생성 시 넘겨줄 값
const interviewEvaluators = ref([]);
const interviewApplicants = ref([]);
const mapToEvaluatorId = () => {
  interviewEvaluators.value = currentEvaluatorState.value.map(
    evaluator => evaluator.id,
  );
};
const mapToApplicantId = () => {
  interviewApplicants.value = currentApplicantState.value.map(
    applicant => applicant.id,
  );
};

const getInterviewInfo = () => {
  mapToApplicantId();
  mapToEvaluatorId();
  const interviewInfo = {
    startTime: makeInterviewStartTime(),
    endTime: makeInterviewEndTime(),
    name: interviewName.value,
    interviewId: currentInterviewId.value,
    applicants: interviewApplicants.value,
    evaluators: interviewEvaluators.value,
  };
  return interviewInfo;
};

const createInterview = () => {
  const interviewInfo = JSON.stringify(getInterviewInfo());
  console.log('interviewInfo: ', interviewInfo);
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .post(`${BASE_URL}/interview/schedule/${processId}`, interviewInfo, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('interview created! ', res.data);
      router.push({
        name: 'CorporateManageInterviewList',
        params: { id: processId },
      });
    })
    .catch(err => {
      console.log(err.message);
    });
};

onMounted(() => {
  getCurrentProcess();
  getEvaluatorsInfo();
  getApplicantsInfo();
  // getInterviewSchedule(1);
});
</script>

<style lang="scss" scoped></style>
