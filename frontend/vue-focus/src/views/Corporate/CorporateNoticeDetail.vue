<template>
  <div>
    <ServiceHeader></ServiceHeader>
    <div class="flex">
      <ServiceNavbar></ServiceNavbar>

      <div class="w-screen">
        <div class="flex flex-col space-y-10">
          <nav class="flex flex-wrap justify-between p-8 text-gray-800">
            <h1 class="font-bold">김토끼 님, 안녕하세요</h1>
            <h3 class="font-bold text-gray-500">Notice</h3>
          </nav>
          <p class="px-10 text-xl font-medium font-gray-900">
            <i class="text-indigo-600 bx bxs-check-circle"></i>공지 상세 내용을
            조회하세요
          </p>
          <div class="flex items-center px-10">
            <div
              class="w-1/2 p-5 space-y-5 bg-white divide-y divide-gray-200 rounded-lg shadow-lg"
            >
              <div class="flex flex-row">
                <div class="m-1">
                  <p class="p-2 text-xl font-medium text-black">카테고리</p>

                  <p class="pt-2 pl-5">{{ category }}</p>
                </div>
                <div class="m-1">
                  <p class="p-2 text-xl font-medium text-black">공지 제목</p>
                  <p class="pt-2 pl-5">{{ title }}</p>
                </div>
              </div>
              <div>
                <p class="p-2 text-xl font-medium text-black">공지 내용</p>
                <p class="pt-2 pl-5">{{ content }}</p>
              </div>
              <div>
                <div class="flex justify-end pt-5">
                  <button
                    @click="moveToServiceNotice()"
                    type="button"
                    class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                  >
                    확인
                  </button>
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
import ServiceHeader from '@/components/ServiceHeader.vue';
import ServiceNavbar from '@/components/ServiceNavbar.vue';

import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const route = useRoute();
const router = useRouter();

const category = ref('');
const title = ref('');
const content = ref('');

const getNoticeDetail = () => {
  const serviceNoticeId = route.params.id;
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/serviceusers/notices/${serviceNoticeId}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('res: ', res.data);
      category.value = res.data.category;
      title.value = res.data.title;
      content.value = res.data.content;
    })
    .catch(err => {
      console.log(err.message);
    });
};

const moveToServiceNotice = () => {
  router.push({ name: 'ServiceNotice' });
};

onMounted(() => {
  getNoticeDetail();
});
</script>

<style lang="scss" scoped></style>
