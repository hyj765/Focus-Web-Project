<template>
  <div>
    <ApplicantHeader></ApplicantHeader>
    <div class="flex">
      <ApplicantNavbar></ApplicantNavbar>
      <div class="w-screen">
        <!-- 
    # 네이버님 안녕하세요
    -->
        <nav class="flex flex-wrap justify-between p-8 text-gray-800">
          <h1 class="font-bold">{{ name }} 님, 안녕하세요</h1>
          <h3 class="font-bold text-gray-500">Profile</h3>
        </nav>
        <div class="flex justify-center space-x-2">
          <div
            class="block w-2/5 max-w-full mx-auto text-sm bg-white rounded-lg shadow-lg pointer-events-auto bg-clip-padding"
          >
            <div
              class="flex items-center justify-between px-3 py-2 bg-white border-b border-gray-200 rounded-t-lg bg-clip-padding"
            >
              <div class="flex flex-row p-4 space-x-5">
                <i class="text-5xl text-blue-700 bx bxs-user-circle"></i>
                <div class="space-y-2 text-lg">
                  <p>지원번호 : {{ code }}</p>
                  <p>이름 : {{ name }}</p>
                  <p>전공 : {{ major }}</p>
                  <p>학점 : {{ credit }} / {{ totalCredit }}</p>
                </div>
              </div>
            </div>
            <!--         
        # 총 평가자 총 지원자
        -->
            <div
              class="relative flex p-4 text-lg text-gray-700 break-words bg-white rounded-b-lg"
            >
              <div class="space-y-3">
                <p>전화번호 : {{ tel }}</p>
                <p>Email : {{ email }}</p>
              </div>
              <button class="absolute top-0 right-0 p-5">
                <i class="text-2xl bx bx-edit-alt"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import ApplicantHeader from '@/components/ApplicantHeader.vue';
import ApplicantNavbar from '@/components/ApplicantNavbar.vue';

const applicantMyInfo = ref(null);
const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const code = ref('');
const credit = ref('');
const major = ref('');
const name = ref('');
const email = ref('');
const tel = ref('');
const totalCredit = ref('');
const userId = ref('');

const getApplicantMyInfo = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/applicants/me`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('myapplicantInfo : ', res.data);
      applicantMyInfo.value = res;
      code.value = applicantMyInfo.value.data.code;
      name.value = applicantMyInfo.value.data.name;
      credit.value = applicantMyInfo.value.data.credit;
      email.value = applicantMyInfo.value.data.email;
      major.value = applicantMyInfo.value.data.major;
      userId.value = applicantMyInfo.value.data.userId;
      tel.value = applicantMyInfo.value.data.tel;
      totalCredit.value = applicantMyInfo.value.data.totalCredit;
      console.log('userId : ', userId.value);
    });
};

onMounted(() => {
  getApplicantMyInfo();
});
</script>

<style lang="scss" scoped></style>
