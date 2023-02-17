<template>
  <nav class="bg-white/50 border-gray-200 px-2 sm:px-4 py-2.5 rounded">
    <div class="container flex flex-wrap items-center justify-between mx-auto">
      <div class="flex items-center space-x-2">
        <img
          @click="moveToCorporateHome()"
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
          <!-- <li class="rounded-lg bg-white/60">
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
                ><i class="bx bx-conversation"></i
              ></span>
            </li> -->
          <!-- # FAQ -->
          <li class="rounded-lg bg-white/60">
            <span
              class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
              ><i class="bx bx-question-mark"></i
            ></span>
          </li>
          <!-- # 메일보내기 -->
          <!-- <li class="rounded-lg bg-white/60">
            <span
              class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
              ><i class="bx bx-paper-plane"></i
            ></span>
          </li> -->
          <!-- # Notice -->
          <li @click="moveToCorporateNotice()" class="rounded-lg bg-white/60">
            <button
              class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
            >
              <i class="bx bx-bell"></i>
            </button>
          </li>
          <!-- # 프로필 -->
          <button
            class="flex items-center space-x-2"
            @click="moveToCorporateProfile()"
          >
            <img
              class="h-8 rounded-full"
              src="http://www.gravatar.com/avatar/2acfb745ecf9d4dccb3364752d17f65f?s=260&d=mp"
              alt="James Bhatta"
            />

            <h4
              class="font-semibold tracking-wide text-gray-700 capitalize font-poppins"
            >
              삼성물산_김민경
            </h4>
            <!-- 로그아웃 -->
            <li class="rounded-lg bg-white/60" @click="logout()">
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-2xl text-gray-800"
                ><i class="bx bx-log-out"></i
              ></span>
            </li>
          </button>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';

const router = useRouter();
const store = useStore();

const moveToCorporateHome = () => {
  router.push({ name: 'Corporate' });
};
const moveToCorporateNotice = () => {
  router.push({ name: 'CorporateNotice' });
};
const moveToCorporateProfile = () => {
  router.push({ name: 'CorporateProfile' });
};
const logout = () => {
  store.dispatch('logout').then(() => {
    router.push({ name: 'Login' });
  });
};
</script>

<style lang="scss" scoped></style>
