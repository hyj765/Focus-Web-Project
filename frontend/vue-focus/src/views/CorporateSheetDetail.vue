<template>
  <div>
    <h2>평가 문항</h2>
    <p>평가 점수</p>
  </div>
  <div class="flex flex-row pl-5">
    <div class="flex justify-center space-x-2">
      <button
        @click="goSheetCreate"
        type="button"
        class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-gray-400 rounded-full shadow-lg hover:bg-gray-500 hover:shadow-lg focus:bg-gray-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-gray-500 active:shadow-lg w-9 h-9"
      >
        <i class="text-xl bx bx-plus"></i>
      </button>
      <button
        @click="goSheetEdit"
        type="button"
        class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-blue-400 rounded-full shadow-lg hover:bg-blue-500 hover:shadow-lg focus:bg-blue-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-500 active:shadow-lg w-9 h-9"
      >
        <i class="text-xl bx bx-check"></i>
      </button>
      <button
        @click="goCorporateHome"
        type="button"
        class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-red-400 rounded-full shadow-lg hover:bg-red-500 hover:shadow-lg focus:bg-red-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-red-500 active:shadow-lg w-9 h-9"
      >
        <i class="text-xl bx bx-x"></i>
      </button>
    </div>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();

const id = route.params.id;

const goCorporateHome = () => router.push({ name: 'Corporate' });
const goSheetEdit = () => router.push({ name: 'SheetEdit', params: { id } });
const goSheetCreate = () => router.push({ name: 'SheetCreate' });
</script>

<style lang="scss" scoped></style>
