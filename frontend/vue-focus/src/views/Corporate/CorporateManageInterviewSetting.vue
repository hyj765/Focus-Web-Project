<template>
  <!-- <CorporateHeader></CorporateHeader> -->
  <div class="flex">
    <CorporateNavbar></CorporateNavbar>
    <div class="w-screen">
      <h1 class="font-bold">면접정보 수정</h1>
      <div>
        <p>면접명</p>
        <input type="text" v-model="interviewName" /><br />
        <input
          v-model="interviewDate"
          type="date"
          id="interviewDate"
          required="true"
        />
        <br />
        <input type="text" v-model="startHour" />시
        <input type="text" v-model="startMinute" />분<br />
        <input type="text" v-model="endHour" />시
        <input type="text" v-model="endMinute" />분<br />

        <!-- 평가자 현황 -->
        <div>
          <p>평가자</p>
          <br />
          <p v-for="evaluator in evaluators" :key="evaluator.id">
            {{ evaluator.name }}({{ evaluator.id }})
          </p>
        </div>
        <!-- 지원자 현황 -->
        <div>
          <p>지원자</p>
          <br />
          <p v-for="applicant in applicants" :key="applicant.id">
            {{ applicant.name }}({{ applicant.id }})
          </p>
        </div>
      </div>
    </div>
    <!-- 평가 -->
    <div class="w-screen">
      <h1 class="font-bold">평가자 지원자 단일 추가</h1>
      <!-- 평가자 선택 -->
      <Menu as="div" class="relative inline-block text-left">
        <div>
          <MenuButton
            class="inline-flex justify-center w-full px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md shadow-sm hover:bg-gray-50"
          >
            {{ evaluatorLabel }}
            <ChevronDownIcon class="w-5 h-5 ml-2 -mr-1" aria-hidden="true" />
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
                    active ? 'bg-gray-100 text-gray-900' : 'text-gray-700',
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
                    active ? 'bg-gray-100 text-gray-900' : 'text-gray-700',
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

      <!-- 지원자 선택 -->
      <Menu as="div" class="relative inline-block text-left">
        <div>
          <MenuButton
            class="inline-flex justify-center w-full px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md shadow-sm hover:bg-gray-50"
          >
            {{ applicantLabel }}
            <ChevronDownIcon class="w-5 h-5 ml-2 -mr-1" aria-hidden="true" />
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
                    active ? 'bg-gray-100 text-gray-900' : 'text-gray-700',
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
                    active ? 'bg-gray-100 text-gray-900' : 'text-gray-700',
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

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const route = useRoute();
// const processId = route.params.processid;

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
const store = useStore();
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
const addEvaluator = () => {
  evaluators.value.push(chosenEvaluator.value);
};
const addApplicant = () => {
  applicants.value.push(chosenApplicant.value);
};

onMounted(() => {
  getInterviewApplicants(interviewId);
  getInterviewEvaluators(interviewId);
  getCurrentInterview();
  getEvaluatorsAll();
  getApplicantsAll();
});
</script>

<style lang="scss" scoped></style>
