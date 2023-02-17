<template>
  <nav class="bg-white/50 border-gray-200 px-2 sm:px-4 py-2.5 rounded">
    <div class="container flex flex-wrap items-center justify-between mx-auto">
      <div class="flex items-center space-x-2">
        <img class="h-7" src="../assets/blue_opacity.png" alt="logo" />
        <span class="self-center text-xl font-semibold whitespace-nowrap"
          >Focus</span
        >
      </div>
      <div class="hidden w-full md:block md:w-auto" id="navbar-default">
        <ul
          class="flex flex-col items-center p-4 mt-4 border border-gray-100 rounded-lg md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0"
        >
          <!-- # FAQ -->
          <li class="rounded-lg bg-white/60">
            <span
              class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
              ><i class="bx bx-question-mark"></i
            ></span>
          </li>

          <!-- # 메일함 -->
          <li class="rounded-lg bg-white/60">
            <span
              class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
              ><i class="bx bxs-inbox"></i
            ></span>
          </li>

          <!-- # 프로필 -->
          <div class="flex items-center space-x-2">
            <img
              class="h-8 rounded-full"
              src="~@/assets/오상식.jpg"
              alt="James Bhatta"
            />
            <button @click="moveToEvaluatorProfile()">
              <h4
                class="font-semibold tracking-wide text-gray-700 capitalize font-poppins"
              >
                {{ userId }} {{ name }}
              </h4>
            </button>
            <!-- 로그아웃 -->
            <li class="rounded-lg bg-white/60" @click="logout()">
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
                ><i class="bx bx-log-out"></i
              ></span>
            </li>
          </div>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
import axios from 'axios';
import { ref, shallowRef, onMounted } from 'vue';

import { useRouter } from 'vue-router';
import { useStore } from 'vuex';

const router = useRouter();
const store = useStore();

const myinfo = ref(null);
const name = ref('');
const email = ref('');
const tel = ref('');
const userId = ref('');

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const moveToEvaluatorProfile = () => {
  router.push({ name: 'EvaluatorProfile' });
};
const logout = () => {
  store.dispatch('logout').then(() => {
    router.push({ name: 'Login' });
  });
};

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
