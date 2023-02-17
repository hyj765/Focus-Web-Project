<template>
  <div class="flex flex-col space-y-10">
    <EvaluatorHeader></EvaluatorHeader>
    <!-- 
    # 네이버님 안녕하세요
    -->
    <nav class="flex flex-wrap justify-between p-8 text-gray-800">
      <h1 class="font-bold">네이버 님, 안녕하세요</h1>
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
              <p>ID : {{ userId }}</p>
              <p>이름 : {{ name }}</p>
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
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import EvaluatorHeader from '@/components/EvaluatorHeader.vue';

const myinfo = ref(null);
const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const name = ref('');
const email = ref('');
const tel = ref('');
const userId = ref('');

const getMyInfo = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/evaluators/me`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('myInfo : ', res.data);
      myinfo.value = res;
      name.value = myinfo.value.data.name;
      email.value = myinfo.value.data.email;
      tel.value = myinfo.value.data.tel;
      userId.value = myinfo.value.data.userId;
    });
};

onMounted(() => {
  getMyInfo();
});
</script>

<style lang="scss" scoped></style>
