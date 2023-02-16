<template>
  <div>
    <CorporateHeader></CorporateHeader>

    <div class="flex">
      <CorporateNavbar></CorporateNavbar>
      <div class="w-screen">
        <div class="flex flex-col space-y-5">
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
          <div class="flex flex-col">
            <div
              class="flex flex-row items-center justify-center my-5 space-x-10"
            >
              <div
                class="flex flex-row items-center justify-center px-12 py-4 space-x-4 text-xl font-bold text-gray-600 bg-white rounded-md shadow-md hover:bg-gray-100"
              >
                <p class="text-xl font-bold">{{ currentProcessName }}</p>
                <p>|</p>
                <p>
                  {{ currentProcessStartDate.slice(0, 10) }} ~
                  {{ currentProcessEndDate.slice(0, 10) }}
                </p>
                <p>|</p>
                <p>총 {{ currentProcessInterviewCount }} 차</p>
              </div>
            </div>
            <div class="flex flex-row">
              <!-- 1, 2, 3차 탭 -->
              <div class="w-1/2">
                <p class="px-3 pt-6 text-lg font-medium">
                  <i class="text-indigo-600 bx bxs-check-circle"></i> 탭을
                  클릭하여 면접일정을 확인해주세요.
                </p>
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
                <div class="overflow-hidden rounded-md shadow-lg">
                  <table class="min-w-full">
                    <thead class="bg-white border-b">
                      <tr>
                        <th
                          scope="col"
                          class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                        >
                          면접명
                        </th>
                        <th
                          scope="col"
                          class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                        >
                          날짜
                        </th>
                        <th
                          scope="col"
                          class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                        >
                          시간
                        </th>
                        <th
                          scope="col"
                          class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                        >
                          삭제
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr
                        v-for="schedule in currentSchedules"
                        class="transition duration-300 ease-in-out bg-white border-b hover:bg-gray-100"
                        :key="schedule.id"
                        @click.stop="goScheduleSetting(processId, schedule.id)"
                      >
                        <td
                          class="px-6 py-4 text-sm font-medium text-gray-900 whitespace-nowrap"
                        >
                          {{ schedule.name }}
                        </td>
                        <td
                          class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                        >
                          {{ schedule.date }}
                        </td>
                        <td
                          class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                        >
                          {{ schedule.startTime.slice(11, 16) }} ~{{
                            schedule.endTime.slice(11, 16)
                          }}
                        </td>
                        <td
                          class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                        >
                          <div class="flex justify-center space-x-2">
                            <button
                              @click.stop="
                                deleteSchedule(currentStep, schedule.id)
                              "
                              type="button"
                              class="inline-block rounded bg-red-600 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-red-700 hover:shadow-lg focus:bg-red-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-red-800 active:shadow-lg"
                            >
                              삭제
                            </button>
                          </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
              <!-- 생성탭 -->
              <div class="w-1/2">
                <!-- 해당 차수의 면접 일정 생성란 (1차, 2차, 3차 선택해야 뜸) -->
                <div v-if="currentInterviewId">
                  <!-- 면접 일정 생성 -->
                  <div class="flex items-center p-10">
                    <div
                      class="p-5 space-y-5 bg-white divide-y divide-gray-200 rounded-lg shadow-lg"
                    >
                      <div>
                        <p class="pb-2 text-xl font-bold text-gray-900">
                          차수별 면접을 생성하세요
                        </p>
                        <p class="text-lg font-medium text-gray-900">면접명</p>
                        <input
                          class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                          type="text"
                          v-model="interviewName"
                        />
                      </div>
                      <div class="flex flex-row space-x-4">
                        <div>
                          <p class="text-lg font-medium text-gray-900">일시</p>
                          <input
                            class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                            v-model="interviewStartDate"
                            type="date"
                            id="interviewStartDate"
                            required="true"
                          />
                        </div>
                        <div>
                          <p class="text-lg font-medium text-gray-900">
                            시작 시각
                          </p>
                          <div class="flex flex-row items-end">
                            <input
                              class="block w-12 mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                              v-model="startHour"
                              type="text"
                            />:
                            <input
                              class="block w-12 mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                              v-model="startMinute"
                              type="text"
                            />
                          </div>
                        </div>
                        <div>
                          <p class="text-lg font-medium text-gray-900">
                            종료 시각
                          </p>
                          <div class="flex flex-row items-end">
                            <input
                              class="block w-12 mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                              v-model="endHour"
                              type="text"
                            />:
                            <input
                              class="block w-12 mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                              v-model="endMinute"
                              type="text"
                            />
                          </div>
                        </div>
                      </div>
                      <div>
                        <div
                          class="flex flex-row items-end justify-between pt-3 pb-2"
                        >
                          <!-- 평가자 현황 -->
                          <p class="text-lg font-medium text-gray-900">
                            평가자 :
                          </p>
                          <!-- 평가자 선택 -->
                          <Menu
                            as="div"
                            class="relative inline-block text-left"
                          >
                            <div>
                              <MenuButton
                                class="inline-flex justify-center w-full px-4 py-2 font-medium text-gray-700 bg-white border border-gray-300 rounded-md shadow-sm text-md hover:bg-gray-50"
                              >
                                선택
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
                        </div>
                        <div>
                          <div class="flex flex-row p-1 space-x-1">
                            <p
                              v-for="evaluator in currentEvaluatorState"
                              :key="evaluator.id"
                            >
                              {{ evaluator.name }}({{ evaluator.id }})
                            </p>
                          </div>
                        </div>
                      </div>

                      <div>
                        <div
                          class="flex flex-row items-end justify-between pt-3 pb-2"
                        >
                          <!-- 지원자 현황 -->
                          <p class="text-lg font-medium text-gray-900">
                            지원자 :
                          </p>
                          <!-- 지원자 선택 -->
                          <Menu
                            as="div"
                            class="relative inline-block text-left"
                          >
                            <div>
                              <MenuButton
                                class="inline-flex justify-center w-full px-4 py-2 font-medium text-gray-700 bg-white border border-gray-300 rounded-md shadow-sm text-md hover:bg-gray-50"
                              >
                                선택
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
                        <div>
                          <div class="flex flex-row p-1 space-x-1">
                            <p
                              v-for="applicant in currentApplicantState"
                              :key="applicant.id"
                            >
                              {{ applicant.name }}({{ applicant.id }})
                            </p>
                          </div>
                        </div>
                      </div>
                      <div class="flex justify-end space-x-2">
                        <button
                          @click.stop="createInterview(currentStep)"
                          type="button"
                          class="inline-block rounded bg-indigo-600 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                        >
                          면접 생성
                        </button>
                      </div>
                    </div>
                  </div>
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
// step 정보를 바탕으로 현재 process의 interviewid로 변환하여, 해당 interviewid의 일정들로 최신화
const getInterviewSchedule = step => {
  currentStep.value = step;
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/interview/schedule/interview/ids/${processId}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      return res.data;
    })
    .then(res => {
      const currentInterview = res.filter(
        interview => interview.step === currentStep.value,
      );
      return currentInterview[0].interviewId;
    })
    .then(res => {
      console.log(res);
      currentInterviewId.value = res;
      axios
        .get(`${BASE_URL}/interview/schedule/${res}`, {
          headers: {
            Authorization: `Bearer ${user.accessToken}`,
          },
        })
        .then(res => {
          currentSchedules.value = res.data;
          console.log(currentSchedules.value);
        });
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
  if (confirm('면접 일정을 삭제하시겠습니까?')) {
    axios
      .delete(`${BASE_URL}/interview/schedule/${interviewRoomId}`, {
        headers: {
          Authorization: `Bearer ${user.accessToken}`,
        },
      })
      .then(res => {
        console.log('delete sucess!', res);
        alert('면접 일정이 삭제되었습니다!');
        getInterviewSchedule(currentStep);
      })
      .catch(err => {
        console.log(err.message);
      });
  } else {
    alert('삭제가 취소되었습니다!');
  }
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

const createInterview = step => {
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
      alert('면접 일정이 생성되었습니다!');
      console.log('interview created! ', res.data);
      interviewName.value = currentInterview.value[0].name;
      interviewStartDate.value = currentInterview.value[0].startDate.slice(
        0,
        10,
      );
      currentEvaluatorState.value = [];
      currentApplicantState.value = [];
      getInterviewSchedule(step);
    })
    .catch(err => {
      console.log(err.message);
    });
};

onMounted(() => {
  getCurrentProcess();
  getEvaluatorsInfo();
  getApplicantsInfo();
});
</script>

<style lang="scss" scoped></style>
