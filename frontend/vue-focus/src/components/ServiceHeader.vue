<template>
  <nav class="bg-white/50 border-gray-200 px-2 sm:px-4 py-2.5 rounded">
    <div class="container flex flex-wrap items-center justify-between mx-auto">
      <div class="flex items-center space-x-2">
        <img
          @click="moveToServiceHome()"
          class="h-7"
          src="../assets/blue_opacity.png"
          alt="logo"
        />
        <span class="self-center text-xl font-semibold whitespace-nowrap"
          >Focus</span
        >
      </div>
      <div class="hidden w-full md:block md:w-auto" id="navbar-default">
        <ul
          class="flex flex-col items-center p-4 mt-4 border border-gray-100 rounded-lg md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0"
        >
          <!-- # FAQ -->
          <li @click="moveToServiceFAQ()" class="rounded-lg bg-white/60">
            <button
              class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
            >
              <i class="bx bx-question-mark"></i>
            </button>
          </li>
          <!-- # 메일보내기 -->
          <!-- <li class="rounded-lg bg-white/60">
            <span
              class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
              ><i class="bx bx-paper-plane"></i
            ></span>
          </li> -->
          <!-- # 메일함 -->
          <!-- <li class="rounded-lg bg-white/60">
            <span
              class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
              ><i class="bx bxs-inbox"></i
            ></span>
          </li> -->
          <!-- # Notice -->
          <li @click="moveToServiceNotice()" class="rounded-lg bg-white/60">
            <button
              class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
            >
              <i class="bx bx-bell"></i>
            </button>
          </li>
          <!-- # 프로필 -->
          <div class="flex items-center pl-2 space-x-2">
            <img
              class="h-8 rounded-full"
              src="http://www.gravatar.com/avatar/2acfb745ecf9d4dccb3364752d17f65f?s=260&d=mp"
              alt="James Bhatta"
            />

            <h4
              class="font-semibold tracking-wide text-gray-700 capitalize font-poppins"
            >
              {{ serviceUserName }}
            </h4>
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
import { onMounted, ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const serviceUserName = ref('');
const getServiceUserName = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/serviceusers/me`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log(res.data);
      serviceUserName.value = res.data.name;
    })
    .catch(err => {
      console.log(err.message);
    });
};

const router = useRouter();
const store = useStore();

const moveToServiceHome = () => {
  router.push({ name: 'Service' });
};

const moveToServiceFAQ = () => {
  router.push({ name: 'ServiceFAQ' });
};

const moveToServiceNotice = () => {
  router.push({ name: 'ServiceNotice' });
};

const logout = () => {
  store.dispatch('logout').then(() => {
    router.push({ name: 'Login' });
  });
};

onMounted(() => {
  getServiceUserName();
});
</script>

<style lang="scss" scoped></style>
