<template>
  <div>
    <CorporateHeader></CorporateHeader>
    <div class="flex">
      <CorporateNavbar></CorporateNavbar>
      <div>
        <nav class="flex flex-wrap justify-between p-8 text-gray-800">
          <h1 class="font-bold">네이버 님, 안녕하세요</h1>
          <h3 class="font-bold text-gray-500">DASHBOARD</h3>
        </nav>
        <p class="px-10 text-xl font-gray-900">지원자 계정을 생성하세요</p>
        <div class="flex items-center p-10">
          <div
            class="p-5 space-y-5 bg-white divide-y divide-gray-200 rounded-lg shadow-lg"
          >
            <div class="m-1">
              <!-- <p class="p-2 text-xl font-medium text-black">소속</p> -->
              <div class="flex flex-row space-x-5">
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">수험번호</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="code"
                    type="text"
                    id="code"
                    required="true"
                  />
                </div>
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">이름</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="name"
                    type="text"
                    id="name"
                    required="true"
                  />
                </div>
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">전화번호</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="tel"
                    type="text"
                    id="tel"
                    required="true"
                    placeholder="010-xxxx-xxxx"
                  />
                </div>
                <div class="p-2">
                  <p class="text-lg font-medium text-gray-900">이메일</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="email"
                    type="email"
                    id="email"
                    required="true"
                    placeholder="example@blackbunny.com"
                  />
                </div>
              </div>
            </div>
            <div class="m-1">
              <!-- <p class="p-2 text-xl font-medium text-black">담당자 정보</p> -->
              <div class="flex flex-row space-x-5">
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">생년월일</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="birth"
                    type="date"
                    id="birth"
                    required="true"
                  />
                </div>
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">성별</p>
                  <select
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border0indigo-500 focus:ring=indigo-500 sm:text-sm"
                    v-model="genderKR"
                    id="gender"
                  >
                    <option
                      v-for="gender in genders"
                      :value="gender.value"
                      :key="gender.id"
                    >
                      {{ gender.value }}
                    </option>
                  </select>
                </div>
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">학력</p>
                  <select
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border0indigo-500 focus:ring=indigo-500 sm:text-sm"
                    v-model="degreeKR"
                    id="degree"
                  >
                    <option
                      v-for="degree in degrees"
                      :value="degree.value"
                      :key="degree.id"
                    >
                      {{ degree.value }}
                    </option>
                  </select>
                </div>
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">대학교</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="univId"
                    type="text"
                    id="univ"
                    required="true"
                  />
                </div>
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">전공</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="major"
                    type="text"
                    id="major"
                    required="true"
                  />
                </div>
              </div>
            </div>
            <div class="m-1">
              <!-- <p class="p-2 text-xl font-medium text-black">담당자 정보</p> -->
              <div class="flex flex-row space-x-5">
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">총학점</p>
                  <select
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border0indigo-500 focus:ring=indigo-500 sm:text-sm"
                    v-model="totalCredit"
                    id="totalCredit"
                  >
                    <option
                      v-for="totalCredit in totalCredits"
                      :value="totalCredit.value"
                      :key="totalCredit.id"
                    >
                      {{ totalCredit.value }}
                    </option>
                  </select>
                </div>
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">학점</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="credit"
                    type="text"
                    id="credit"
                    required="true"
                  />
                </div>
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">수상횟수</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="awardCount"
                    type="text"
                    id="awardCount"
                    required="true"
                  />
                </div>
                <div class="flex flex-col p-2">
                  <p class="text-lg font-medium text-gray-900">대외활동 횟수</p>
                  <input
                    class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    v-model="activityCount"
                    type="text"
                    id="activityCount"
                    required="true"
                  />
                </div>
              </div>
            </div>
            <div>
              <div class="flex justify-end pt-5">
                <button
                  @click="createApplicant()"
                  type="button"
                  class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                >
                  계정생성
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
// 1. 수험번호(텍스트) 2. 이름(텍스트) 3. 전화번호(텍스트) 4. 생년월일(달력) 5. 이메일(텍스트) 6. 성(선택) 7. 학위(드랍다운)
// 8. 대학교(??) 9. 전공(텍스트) 10. 학점(int) 11. 총학점(드랍다운) 12. 대외활동 횟수(텍스트->int) 13. 수상 횟수(텍스트->int)

import { ref } from 'vue';
import axios from 'axios';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const store = useStore();
const router = useRouter();
const processId = store.state.currentApplicantProcessId;

const code = ref('');
const name = ref('');
const tel = ref('');
const birth = ref('');
const email = ref('');
const genders = ref([
  { id: 1, value: '남' },
  { id: 2, value: '여' },
]);
const genderKR = ref('');
const genderEN = () => {
  if (genderKR.value === '남') {
    return 'm';
  } else if (genderKR.value === '여') {
    return 'f';
  }
};
const degrees = ref([
  { id: 1, value: '대학원 박사' },
  { id: 2, value: '대학원 석사' },
  { id: 3, value: '4년제 학사' },
  { id: 4, value: '2년제 학사' },
]);
const degreeKR = ref('');
const degreeEN = () => {
  if (degreeKR.value === '대학원 박사') {
    return 'd';
  } else if (degreeKR.value === '대학원 석사') {
    return 'm';
  } else if (degreeKR.value === '4년제 학사') {
    return 'b';
  } else if (degreeKR.value === '2년제 학사') {
    return 'j';
  } else {
    return 'invalid degree!';
  }
};
const univId = ref(null);
const major = ref('');
const credit = ref('');
const totalCredits = ref([
  { id: 1, value: 4.5 },
  { id: 2, value: 4.3 },
]);
const totalCredit = ref(0);
const activityCount = ref('');
const awardCount = ref('');

const getApplicantInfo = () => {
  const applicantInfo = {
    code: code.value,
    name: name.value,
    tel: tel.value,
    birth: birth.value,
    email: email.value,
    gender: genderEN(),
    degree: degreeEN(),
    univId: univId.value,
    major: major.value,
    credit: parseFloat(credit.value),
    totalCredit: totalCredit.value,
    activityCount: parseInt(activityCount.value),
    awardCount: parseInt(awardCount.value),
  };
  return applicantInfo;
};

const createApplicant = () => {
  const applicantInfo = getApplicantInfo();
  let applicantInfos = [];
  applicantInfos.push(applicantInfo);
  const applicantInfosJson = JSON.stringify(applicantInfos);
  const user = JSON.parse(localStorage.getItem('user'));
  console.log('applicantInfo: ', applicantInfo);
  console.log('applicantInfos: ', applicantInfos);
  console.log('applicantInfosJson: ', applicantInfosJson);
  console.log(user.accessToken);
  axios
    .post(
      `${BASE_URL}/companyusers/applicants/${processId}`,
      applicantInfosJson,
      {
        headers: {
          Authorization: `Bearer ${user.accessToken}`,
        },
      },
    )
    .then(res => {
      console.log(res.data);
      router.push({
        name: 'CorporateManageApplicantList',
        params: { id: processId },
      });
    })
    .catch(err => {
      console.log(err.message);
    });
};
</script>

<style lang="scss" scoped></style>
