<template>
  <div>
    <CorporateHeader></CorporateHeader>
    <div class="flex">
      <CorporateNavbar></CorporateNavbar>
      <div class="w-screen">
        <div class="flex flex-col space-y-10">
          <nav class="flex flex-wrap justify-between p-8 text-gray-800">
            <h1 class="font-bold">네이버 님, 안녕하세요</h1>
            <h3 class="font-bold text-gray-500">Applicant</h3>
          </nav>
          <p class="px-10 text-xl font-gray-900">지원자 세부 사항</p>

          <!-- 나 -->
          <div class="flex items-center px-10">
            <div class="flex flex-row p-5 space-y-5 rounded-lg shadow-lg bg-">
              <div class="p-3">
                <div>
                  <img class="w-full" id="image" src="" />
                  <div class="flex flex-row">
                    <input
                      class="bg-white min-w-fit"
                      type="file"
                      accept="image/*"
                      id="applicantPicture"
                    />
                    <button
                      type="button"
                      @click="uploadApplicantPicture()"
                      class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                    >
                      저장하기
                    </button>
                  </div>
                </div>
                <div>
                  <button @click="showModal = true">Open Modal</button>
                  <div class="modal-overlay" v-if="showModal">
                    <div class="modal-container">
                      <div class="modal-content">
                        <button class="modal-close" @click="showModal = false">
                          X
                        </button>
                        <img
                          class="w-full"
                          id="introduce"
                          src=""
                          alt="Modal Image"
                        />
                      </div>
                    </div>
                  </div>

                  <input type="file" accept="image/*" id="applicantIntroduce" />
                  <button
                    type="button"
                    @click="uploadApplicantIntroduce()"
                    class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                  >
                    저장하기
                  </button>
                </div>
              </div>
              <div class="divide-y divide-gray-200">
                <div class="m-1">
                  <!-- <p class="p-2 text-xl font-medium text-black">소속</p> -->
                  <div class="flex flex-row space-x-5">
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">수험번호</p>
                      <p class="p-5">{{ code }}</p>
                    </div>
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">이름</p>
                      <p class="p-5">{{ name }}</p>
                    </div>
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">전화번호</p>
                      <p class="p-5">{{ tel }}</p>
                    </div>
                    <div class="p-2">
                      <p class="text-lg font-medium text-gray-900">이메일</p>
                      <p class="p-5">{{ email }}</p>
                    </div>
                  </div>
                </div>
                <div class="m-1">
                  <!-- <p class="p-2 text-xl font-medium text-black">담당자 정보</p> -->
                  <div class="flex flex-row space-x-5">
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">생년월일</p>
                      <p class="p-5">{{ birth }}</p>
                    </div>
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">성별</p>
                      <p class="p-5">{{ gender }}</p>
                    </div>
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">학력</p>
                      <p class="p-5">{{ code }}</p>
                    </div>
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">대학교</p>
                      <p class="p-5">{{ converteddegree }}</p>
                    </div>
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">전공</p>
                      <p class="p-5">{{ major }}</p>
                    </div>
                  </div>
                </div>
                <div class="m-1">
                  <!-- <p class="p-2 text-xl font-medium text-black">담당자 정보</p> -->
                  <div class="flex flex-row justify-between space-x-5">
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">총학점</p>
                      <p class="p-5">{{ totalCredit }}</p>
                    </div>
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">학점</p>
                      <p class="p-5">{{ credit }}</p>
                    </div>
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">수상횟수</p>
                      <p class="p-5">{{ awardCount }}</p>
                    </div>
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">
                        대외활동 횟수
                      </p>
                      <p class="p-5">{{ activityCount }}</p>
                    </div>
                  </div>
                </div>
                <div>
                  <div class="flex justify-end pt-5">
                    <button
                      @click="goBack()"
                      type="button"
                      class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                    >
                      뒤로가기
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- 영준 -->
          <p>{{ name }}</p>
          <p>{{ major }}</p>
          <p>{{ credit }} : {{ totalCredit }}</p>
          <p>{{ email }}</p>
          <p>{{ tel }}</p>
          <p>수험번호: {{ code }}</p>

          <input type="file" accept="image/*" id="applicantPicture" />
          <button @click="uploadApplicantPicture()" type="button">
            지원자 사진 업로드
          </button>
          <input type="file" accept="image/*" id="applicantIntroduce" />
          <button @click="uploadApplicantIntroduce()" type="button">
            사용자 자기소개서 업로드
          </button>
          <button @click="loadApplicantImage()">이미지 가져오기</button>
          <img id="image" src="" />
          <img id="introduce" src="" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import CorporateHeader from '@/components/CorporateHeader.vue';
import CorporateNavbar from '@/components/CorporateNavbar.vue';

import { watch, ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const showModal = ref(false);

const awardCount = ref(0);
const activityCount = ref(0);
const birth = ref('');
const code = ref('');
const credit = ref(0);
const converteddegree = ref('');
const email = ref('');
const expireDate = ref('');
const gender = ref('');
const major = ref('');
const name = ref('');
const tel = ref('');
const totalCredits = ref([
  { id: 1, value: 4.5 },
  { id: 2, value: 4.3 },
]);
const totalCredit = ref(0);

const getdegree = degree => {
  if (degree === 'j') {
    converteddegree.value = '전문학사';
  } else if (degree === 'b') {
    converteddegree.value = '학사';
  } else if (degree === 'm') {
    converteddegree.value = '석사';
  } else if (degree === 'd') {
    converteddegree.value = '박사';
  }
};

const route = useRoute();
const router = useRouter();
const faceimage = new Image();
onMounted(() => {
  loadApplicantInfoDetail();
  console.log(route.params.id);
});
const applicantId = ref(0);
const uploadApplicantPicture = () => {
  const fm = new FormData();
  const user = JSON.parse(localStorage.getItem('user'));
  const image = document.getElementById('applicantPicture');
  fm.append('applicantId', applicantId.value);
  fm.append('file', image.files[0]);
  axios
    .post(`${BASE_URL}/data/upload/applicantface/image`, fm, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
        'Content-Type': 'multipart/form-data',
      },
    })
    .then(data => {
      console.log(data);
    })
    .then(err => {
      console.log(err);
    });
};
const uploadApplicantIntroduce = () => {
  const fm = new FormData();
  const user = JSON.parse(localStorage.getItem('user'));
  const image = document.getElementById('applicantIntroduce');
  console.log(applicantId);
  fm.append('applicantId', applicantId.value);
  fm.append('file', image.files[0]);
  axios
    .post(`${BASE_URL}/data/upload/introduce/image`, fm, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
        'Content-Type': 'multipart/form-data',
      },
    })
    .then(data => {
      console.log(data);
    })
    .then(err => {
      console.log(err);
    });
};

const loadApplicantInfoDetail = () => {
  axios
    .get(`${BASE_URL}/companyusers/applicants/${route.params.id}`)
    .then(data => {
      // applicantInfo.value = data.data;
      console.log(data.data);
      applicantId.value = data.data.id;
      code.value = data.data.code;
      activityCount.value = data.data.activityCount;
      awardCount.value = data.data.awardCount;
      birth.value = data.data.birth;
      credit.value = data.data.credit;
      email.value = data.data.email;
      expireDate.value = data.data.expireDate;
      gender.value = data.data.gender;
      major.value = data.data.major;
      name.value = data.data.name;
      tel.value = data.data.tel;
      totalCredit.value = data.data.totalCredit;
      getdegree(data.data.degree);
      loadApplicantImage();
      loadIntroduceImage();
    })
    .catch(err => {
      console.log(err);
    });
};

const loadApplicantImage = () => {
  axios
    .get(`${BASE_URL}/data/applicant/image/${applicantId.value}`)
    .then(data => {
      var image = new Image();
      image.src = `data:image/png;base64,${data.data}`;
      document.getElementById('image').src = image.src;
    })
    .catch(err => {
      console.log(err);
    });
};
const loadIntroduceImage = () => {
  axios
    .get(`${BASE_URL}/data/applicant/introduce/${applicantId.value}`)
    .then(data => {
      var image = new Image();
      image.src = `data:image/png;base64,${data.data}`;
      document.getElementById('introduce').src = image.src;
    });
};

const goBack = () => {
  router.go(-1);
};

onMounted(() => {
  loadApplicantInfoDetail();
  // loadApplicantImage();
});
</script>

<style lang="scss" scoped></style>
