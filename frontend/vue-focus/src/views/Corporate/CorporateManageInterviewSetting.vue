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
            <h1 class="font-bold">{{ companyUserName }} 님, 안녕하세요</h1>
            <h3 class="font-bold text-gray-500">Interview</h3>
          </nav>
          <p class="px-10 text-2xl font-medium">예정된 전형</p>
          <p class="px-10 pt-1 text-gray-700 font-regular">
            각각의 전형에 면접 일정을 추가해주세요
          </p>
          <div class="flex items-center p-10">
            <div
              class="p-5 space-y-5 bg-white divide-y divide-gray-200 rounded-lg shadow-lg"
            >
              <div>
                <p class="pb-2 text-xl font-bold text-gray-900">
                  면접정보 수정
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
                    v-model="interviewDate"
                    type="date"
                    id="interviewDate"
                    required="true"
                  />
                </div>
                <div>
                  <p class="text-lg font-medium text-gray-900">시작 시각</p>
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
                  <p class="text-lg font-medium text-gray-900">종료 시각</p>
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
                <div class="flex flex-row items-end justify-between pt-3 pb-2">
                  <!-- 평가자 현황 -->
                  <p class="text-lg font-medium text-gray-900">평가자 :</p>
                  <!-- 평가자 선택 -->
                  <Menu as="div" class="relative inline-block text-left">
                    <div>
                      <MenuButton
                        class="inline-flex justify-center w-full px-4 py-2 font-medium text-gray-700 bg-white border border-gray-300 rounded-md shadow-sm text-md hover:bg-gray-50"
                      >
                        {{ evaluatorLabel }}
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
                          <MenuItem v-slot="{ active }">
                            <p
                              @click.stop="setEvaluatorDefault()"
                              :class="[
                                active
                                  ? 'bg-gray-100 text-gray-900'
                                  : 'text-gray-700',
                                'block px-4 py-2 text-sm',
                              ]"
                            >
                              ------
                            </p>
                          </MenuItem>
                          <MenuItem
                            v-slot="{ active }"
                            v-for="evaluator in evaluatorsAll"
                            :key="evaluator.id"
                          >
                            <p
                              @click.stop="clickEvaluator(evaluator)"
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
                    <button @click="addEvaluator()">평가자 추가</button>
                  </Menu>
                </div>
                <div>
                  <div class="flex flex-row p-1 space-x-1">
                    <p v-for="evaluator in evaluators" :key="evaluator.id">
                      {{ evaluator.name }}({{ evaluator.id }})
                      <button @click.stop="deleteEvaluator(evaluator.id)">
                        X
                      </button>
                    </p>
                  </div>
                </div>
              </div>

              <div>
                <div class="flex flex-row items-end justify-between pt-3 pb-2">
                  <!-- 지원자 현황 -->
                  <p class="text-lg font-medium text-gray-900">지원자 :</p>
                  <!-- 지원자 선택 -->
                  <Menu as="div" class="relative inline-block text-left">
                    <div>
                      <MenuButton
                        class="inline-flex justify-center w-full px-4 py-2 font-medium text-gray-700 bg-white border border-gray-300 rounded-md shadow-sm text-md hover:bg-gray-50"
                      >
                        {{ applicantLabel }}
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
                          <MenuItem v-slot="{ active }">
                            <p
                              @click="setApplicantDefault()"
                              :class="[
                                active
                                  ? 'bg-gray-100 text-gray-900'
                                  : 'text-gray-700',
                                'block px-4 py-2 text-sm',
                              ]"
                            >
                              ------
                            </p>
                          </MenuItem>
                          <MenuItem
                            v-slot="{ active }"
                            v-for="applicant in applicantsAll"
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
                    <button @click="addApplicant()">지원자 추가</button>
                  </Menu>
                </div>
                <div>
                  <div class="flex flex-row p-1 space-x-1">
                    <p v-for="applicant in applicants" :key="applicant.id">
                      {{ applicant.name }}({{ applicant.id }})
                      <button @click.stop="deleteApplicant(applicant.id)">
                        X
                      </button>
                    </p>
                  </div>
                </div>
              </div>
              <div class="flex justify-end space-x-2">
                <button
                  @click.stop="saveInterview()"
                  type="button"
                  class="inline-block rounded bg-indigo-600 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                >
                  저장
                </button>
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

import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';
import router from '@/router';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const route = useRoute();
const processId = route.params.processid;

// 1))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
const interviewId = route.params.id;
// 현재 면접 평가자 및 지원자 정보
const evaluators = ref([]);
const applicants = ref([]);
// 현재 면접 일정 정보
const interviewName = ref('');
const interviewDate = ref('');
const startHour = ref('');
const startMinute = ref('');
const endHour = ref('');
const endMinute = ref('');
const makeInterviewStartTime = () => {
  return (
    interviewDate.value +
    'T' +
    startHour.value +
    ':' +
    startMinute.value +
    ':00'
  );
};
const makeInterviewEndTime = () => {
  return (
    interviewDate.value + 'T' + endHour.value + ':' + endMinute.value + ':00'
  );
};

// 현재 면접 정보 조회 및 업데이트
const currentInterview = ref(null);
const getCurrentInterview = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/interview/schedule/detail/${interviewId}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      currentInterview.value = res.data;
      console.log('currentInterview: ', currentInterview.value);
      interviewName.value = res.data.name;
      interviewDate.value = res.data.date;
      startHour.value = res.data.startTime.slice(11, 13);
      startMinute.value = res.data.startTime.slice(14, 16);
      endHour.value = res.data.endTime.slice(11, 13);
      endMinute.value = res.data.endTime.slice(14, 16);
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 해당 면접 지원자 리스트 조회 및 업데이트
const getInterviewApplicants = interviewId => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/interview/schedule/${interviewId}/applicants`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      // console.log('applicants: ', res.data);
      applicants.value = res.data;
      console.log('applicants: ', applicants.value);
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 해당 면접 평가자 리스트 조회 및 업데이트
const getInterviewEvaluators = interviewId => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/interview/schedule/${interviewId}/evaluators`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      // console.log('evaluators: ', res.data);
      evaluators.value = res.data;
      console.log('evaluators: ', evaluators.value);
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 2))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
// 선택용 평가자 및 지원자
const evaluatorsAll = ref([]);
const applicantsAll = ref([]);

// 선택용 평가자 및 지원자 조회용 정보
const getEvaluatorsAll = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/companyusers/evaluators/list`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      evaluatorsAll.value = res.data.content;
      console.log('evaluatorsAll: ', evaluatorsAll.value);
    })
    .catch(err => {
      console.log(err.message);
    });
};
const getApplicantsAll = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(
      `${BASE_URL}/interview/interview/applicants/${route.params.processid}`,
      {
        headers: {
          Authorization: `Bearer ${user.accessToken}`,
        },
      },
    )
    .then(res => {
      applicantsAll.value = res.data;
      console.log('applicantsAll: ', applicantsAll.value);
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 평가자 지원자 선택 변경용 (라벨과 해당 객체)
const evaluatorLabel = ref('평가자');
const applicantLabel = ref('지원자');
const chosenEvaluator = ref(null);
const chosenApplicant = ref(null);

const setEvaluatorDefault = () => {
  evaluatorLabel.value = '평가자';
  chosenEvaluator.value = null;
};
const setApplicantDefault = () => {
  applicantLabel.value = '지원자';
  chosenApplicant.value = null;
};

const clickEvaluator = evaluator => {
  evaluatorLabel.value = evaluator.name;
  chosenEvaluator.value = evaluator;
  console.log('chosenEvaluator: ', chosenEvaluator.value);
};
const clickApplicant = applicant => {
  applicantLabel.value = applicant.name;
  chosenApplicant.value = applicant;
  console.log('chosenApplicant: ', chosenApplicant.value);
};

// 평가자 및 지원자 추가
const evaluatorAddInfo = () => {
  const info = {
    evaluatorId: chosenEvaluator.value.id,
    interviewRoomId: interviewId,
    processId: processId,
  };
  return info;
};
const addEvaluator = () => {
  const info = JSON.stringify(evaluatorAddInfo());
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .post(`${BASE_URL}/interview/schedule/add/evaluator`, info, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('add evaluator success!', res);
      getInterviewEvaluators(interviewId);
      setEvaluatorDefault();
    })
    .catch(err => {
      console.log(err.message);
    });
};
const applicantAddInfo = () => {
  const info = {
    applicantId: chosenApplicant.value.id,
    interviewRoomId: interviewId,
  };
  return info;
};
const addApplicant = () => {
  const info = JSON.stringify(applicantAddInfo());
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .post(`${BASE_URL}/interview/schedule/add/applicant`, info, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('add applicant success!', res);
      getInterviewApplicants(interviewId);
      setApplicantDefault();
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 평가자 지원자 면접 일정에서 삭제
const deleteEvaluator = evalId => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .delete(`${BASE_URL}/interview/schedule/remove/evaluator`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
      data: {
        evaluatorId: evalId,
        interviewRoomId: interviewId,
        processId: processId,
      },
    })
    .then(res => {
      console.log('delete evaluator success!', res);
      getInterviewEvaluators(interviewId);
    })
    .catch(err => {
      console.log(err.message);
    });
};
const deleteApplicant = applicantId => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .delete(`${BASE_URL}/interview/schedule/remove/applicant`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
      data: {
        applicantId: applicantId,
        interviewRoomId: interviewId,
      },
    })
    .then(res => {
      console.log('delete applicant success!', res);
      getInterviewApplicants(interviewId);
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 현재 면접정보 저장 및 이동
const evaluatorIds = ref([]);
const applicantIds = ref([]);
const mapToEvaluatorId = () => {
  evaluatorIds.value = evaluators.value.map(evaluator => evaluator.id);
};
const mapToApplicantId = () => {
  applicantIds.value = applicants.value.map(applicant => applicant.id);
};

const getInterviewInfo = () => {
  mapToApplicantId();
  mapToEvaluatorId();
  const interviewInfo = {
    startTime: makeInterviewStartTime(),
    endTime: makeInterviewEndTime(),
    name: interviewName.value,
    interviewId: interviewId,
    applicants: applicantIds.value,
    evaluators: evaluatorIds.value,
  };
  return interviewInfo;
};

const saveInterview = () => {
  const interviewInfo = JSON.stringify(getInterviewInfo());
  console.log('interviewInfo: ', interviewInfo);
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .put(`${BASE_URL}/interview/schedule/${interviewId}`, interviewInfo, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
        'Content-Type': 'application/json',
      },
    })
    .then(res => {
      console.log('put success! ', res.data);
      router.push({
        name: 'CorporateManageInterviewList',
        params: { id: processId },
      });
    })
    .catch(err => {
      console.log(err.message);
    });
};
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
onMounted(() => {
  getInterviewApplicants(interviewId);
  getInterviewEvaluators(interviewId);
  getCurrentInterview();
  getEvaluatorsAll();
  getApplicantsAll();
  getCompanyUserName();
});
</script>

<style lang="scss" scoped></style>
