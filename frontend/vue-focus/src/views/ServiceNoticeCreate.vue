<template>
  <div>
    <div class="flex flex-col space-y-10">
      <input tpye="textarea" v-model="title" maxlength="150" />
      <select v-model="selectedtag" id="selectedtag">
        <option
          v-for="category in Categories"
          :value="category.id"
          :key="category.value"
        >
          {{ category.value }}
        </option>
      </select>
      <input
        type="textarea"
        v-model="textContents"
        id="contents"
        maxlength="2500"
      />
      <button @click="createNotice">글 생성하기</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
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
    })
    .catch(err => {
      console.log(err);
    });
};
</script>

<style lang="scss" scoped></style>
