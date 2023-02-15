<template>
  <div>
    <CorporateHeader></CorporateHeader>
    <div class="flex">
      <CorporateNavbar></CorporateNavbar>
      <div class="w-screen">
        <div class="flex flex-col space-y-10">
          <div class="flex flex-col space-y-10">
            <!-- 
    # 네이버님 안녕하세요
    -->
            <nav class="flex flex-wrap justify-between p-8 text-gray-800">
              <h1 class="font-bold">삼성물산 님, 안녕하세요</h1>
              <h3 class="font-bold text-gray-500">Notice</h3>
            </nav>
            <div class="flex flex-wrap justify-between px-5">
              <p class="text-2xl font-medium">공지 게시판</p>
            </div>
            <div class="flex flex-col">
              <div class="flex flex-col justify-center px-10">
                <div
                  class="px-10 overflow-hidden bg-white border-b rounded-md shadow-lg"
                >
                  <table class="min-w-full">
                    <thead>
                      <tr>
                        <th
                          scope="col"
                          class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                        >
                          카테고리
                        </th>
                        <th
                          scope="col"
                          class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                        >
                          공지명
                        </th>
                        <th
                          scope="col"
                          class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                        >
                          날짜
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr
                        v-for="(item, index) in boardList.slice().reverse()"
                        :content="item.value"
                        :key="index"
                        class="transition duration-300 ease-in-out bg-white border-b hover:bg-gray-100"
                      >
                        <td
                          class="px-6 py-4 text-sm font-medium text-gray-900 whitespace-nowrap"
                        >
                          {{ index + 1 }}
                        </td>
                        <td
                          class="px-6 py-4 text-sm font-medium text-gray-900 whitespace-nowrap"
                        >
                          <a @click="goDetail(item.id)" :data="item">
                            {{ item.title }}
                          </a>
                        </td>
                        <td
                          class="px-6 py-4 text-sm font-medium text-gray-900 whitespace-nowrap"
                        >
                          {{ item.createdAt.slice(0, 10) }}
                        </td>
                      </tr>
                    </tbody>
                  </table>
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

import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const boardList = ref([]);

onMounted(() => {
  getContextList();
});
const list = [];
const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const user = JSON.parse(localStorage.getItem('user'));
const getContextList = () => {
  axios
    .get(`${BASE_URL}/serviceusers/notices`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(data => {
      console.log(data.data.content);
      boardList.value = data.data.content;
    })
    .catch(err => {
      console.log(err);
    });
};

const router = useRouter();
const moveToNoticeCreate = () => {
  router.push({ name: 'CorporateNoticeCreate' });
};
const goDetail = corporateNoticeId => {
  router.push({
    name: 'CorporateNoticeDetail',
    params: { id: corporateNoticeId },
  });
};
</script>

<style lang="scss" scoped></style>
