<template>
  <div>
    <ServiceHeader></ServiceHeader>
    <div class="flex">
      <ServiceNavbar></ServiceNavbar>

      <div>
        <nav class="flex flex-wrap justify-between p-8 text-gray-800">
          <h1 class="font-bold">네이버 님, 안녕하세요</h1>
          <h3 class="font-bold text-gray-500">Notice</h3>
        </nav>
        <p class="px-10 text-xl font-gray-900">공지를 등록하세요</p>
        <div class="flex items-center p-10">
          <div
            class="p-5 space-y-5 bg-white divide-y divide-gray-200 rounded-lg shadow-lg"
          >
            <div class="flex flex-row">
              <div class="m-1">
                <p class="p-2 text-xl font-medium text-black">카테고리</p>
                <select
                  class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border0indigo-500 focus:ring=indigo-500 sm:text-sm"
                  v-model="selectedtag"
                  id="selectedtag"
                >
                  <option
                    v-for="category in Categories"
                    :value="category.id"
                    :key="category.value"
                  >
                    {{ category.value }}
                  </option>
                </select>
              </div>
              <div class="m-1">
                <p class="p-2 text-xl font-medium text-black">공지 제목</p>
                <input
                  class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                  type="text"
                  v-model="title"
                  maxlength="150"
                />
              </div>
            </div>
            <div>
              <p class="p-2 text-xl font-medium text-black">공지 내용</p>
              <textarea
                v-model="textContents"
                id="contents"
                rows="5"
                class="flex-1 block w-full border-gray-300 rounded-none rounded-r-md focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                placeholder="내용을 입력해주세요"
              />
            </div>
            <div>
              <div class="flex justify-end pt-5">
                <button
                  @click="createNotice"
                  type="button"
                  class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                >
                  작성하기
                </button>
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
import { useRouter } from 'vue-router';

const Categories = ref([
  { id: 1, value: '버전업데이트' },
  { id: 2, value: '점검 안내' },
  { id: 3, value: '일반' },
  { id: 4, value: '기타' },
]);
const title = ref('');
const selectedtag = ref(null);
const textContents = ref('');
// const BASE_URL = 'http://localhost:8082/api';

// const notices = ref(null);
// const getCorporateNotices = () => {
//   const user = JSON.parse(localStorage.getItem('user'));
//   axios
//     .get(`${BASE_URL}/serviceusers/notices`, {
//       Authoriation: `Bearer ${user.accessToken}`,
//     })
//     .then(res => {
//       console.log('res.data: ', res.data);
//     });
// };

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const getNoticeInfo = () => {
  const noticeInfo = {
    categoryId: selectedtag.value,
    content: textContents.value,
    title: title.value,
  };

  return noticeInfo;
};

const createNotice = () => {
  const noticeInfo = JSON.stringify(getNoticeInfo());
  const user = JSON.parse(localStorage.getItem('user'));
  console.log('noticeInfo: ', noticeInfo);
  console.log('user: ', user);
  axios
    .post(`${BASE_URL}/serviceusers/notices`, noticeInfo, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('success');
      moveToServiceNotice();
    })
    .catch(err => {
      console.log(err);
    });
};

const router = useRouter();
const moveToServiceNotice = () => {
  router.push({ name: 'ServiceNotice' });
};
</script>

<style lang="scss" scoped></style>
