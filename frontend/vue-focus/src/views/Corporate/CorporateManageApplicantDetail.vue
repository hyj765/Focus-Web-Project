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
          <div class="flex flex-row justify-between">
            <p class="px-10 text-xl font-bold font-gray-900">
              지원자 세부 사항
            </p>
            <div class="pr-40">
              <button
                @click="goBack()"
                type="button"
                class="mr-20 inline-block rounded bg-red-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-red-700 hover:shadow-lg focus:bg-red-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-red-800 active:shadow-lg"
              >
                뒤로가기
              </button>
            </div>
          </div>
          <!-- 나 -->
          <div class="flex items-center px-10">
            <div
              class="flex flex-row p-5 space-y-5 bg-white rounded-lg shadow-lg"
            >
              <div class="flex flex-col justify-center p-4">
                <img class="w-64 p-2" id="image" src="" />

                <button
                  @click="showImg = !showImg"
                  type="button"
                  class="px-3 py-2 ml-2 text-sm font-medium leading-4 text-gray-700 bg-white border border-gray-300 rounded-md shadow-sm w-36 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                >
                  사진 변경
                </button>
                <!-- 사진 변경 -->
                <div v-if="showImg" class="flex flex-row py-4">
                  <input
                    class="block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4 file:rounded-full file:border-0 file:text-sm file:font-regular file:bg-indigo-900 file:text-white hover:file:bg-indigo-800"
                    type="file"
                    accept="image/*"
                    id="applicantPicture"
                  /><button
                    @click.stop="uploadApplicantPicture()"
                    type="button"
                    class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-indigo-400 rounded shadow-lg hover:bg-indigo-500 hover:shadow-lg focus:bg-indigo-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-500 active:shadow-lg w-9 h-9"
                  >
                    <i class="text-lg bx bx-check"></i>
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
                      <p class="text-lg font-medium text-gray-900">최종학력</p>
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
                  <div class="flex flex-row space-x-5">
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">학점</p>
                      <p class="p-5">{{ credit }} / {{ totalCredit }}</p>
                    </div>
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">수상횟수</p>
                      <p class="p-5">{{ awardCount }}회</p>
                    </div>
                    <div class="flex flex-col p-2">
                      <p class="text-lg font-medium text-gray-900">
                        대외활동 횟수
                      </p>
                      <p class="p-5">{{ activityCount }}회</p>
                    </div>
                  </div>
                </div>
                <!-- # 모달 -->
                <div class="pt-4">
                  <button
                    class="px-4 py-2 text-white bg-indigo-900 rounded"
                    @click="showModal = true"
                  >
                    자소서 보기
                  </button>
                  <div
                    class="fixed top-0 left-0 z-50 w-full h-full bg-white"
                    v-if="showModal"
                  >
                    <div
                      class="fixed -translate-x-1/2 -translate-y-1/2 top-1/2 left-1/2 z-60"
                    >
                      <div class="bg-white p=2 relative">
                        <button
                          class="absolute top-0 right-0 p-0.5"
                          @click="showModal = false"
                        >
                          X
                        </button>
                        <img
                          @click="showModal = false"
                          id="introduce"
                          src="@/assets/introduce.png"
                          alt="Modal Image"
                        />
                      </div>
                    </div>
                  </div>
                  <!-- # 모달 창 끝 -->
                  <button
                    @click="showIntroduce = !showIntroduce"
                    type="button"
                    class="px-3 py-2 ml-2 text-sm font-medium leading-4 text-gray-700 bg-white border border-gray-300 rounded-md shadow-sm w-36 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                  >
                    자소서 변경
                  </button>
                  <!-- 사진 변경 -->
                  <div v-if="showIntroduce" class="flex flex-row py-4">
                    <input
                      class="block w-full text-sm text-gray-500 file:mr-4 file:py-2 file:px-4 file:rounded-full file:border-0 file:text-sm file:font-regular file:bg-indigo-900 file:text-white hover:file:bg-indigo-800"
                      type="file"
                      accept="image/*"
                      id="applicantIntroduce"
                    /><button
                      @click.stop="uploadApplicantIntroduce()"
                      type="button"
                      class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-indigo-400 rounded shadow-lg hover:bg-indigo-500 hover:shadow-lg focus:bg-indigo-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-500 active:shadow-lg w-9 h-9"
                    >
                      <i class="text-lg bx bx-check"></i>
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
</template>

<script setup>
import CorporateHeader from '@/components/CorporateHeader.vue';
import CorporateNavbar from '@/components/CorporateNavbar.vue';

import { watch, ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const showImg = ref(false);
const showIntroduce = ref(false);

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
      console.log('image: ', image.src);

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
