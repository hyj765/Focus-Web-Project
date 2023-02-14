<template>
  <div class="board-detail">
    <p>{{ title.value }}</p>
    <p>{{ content.value }}</p>
    <p>{{ tag.value }}</p>
    <p>{{ createdAt.value }}</p>
  </div>
</template>
<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const route = useRoute();

onMounted(() => {
  onLoadBoardDetail();
});

const title = ref('');
const content = ref('');
const tag = ref('');
const createdAt = ref(null);

const onLoadBoardDetail = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .post(`${BASE_URL}/serviceusers/notices/${route.params.id}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      title.value = res.data.title;
      content.value = res.data.content;
      tag.value = res.data.tag;
      createdAt.value = res.data.createdAt;
    })
    .catch(err => {
      console.log(err.message);
    });
};
</script>

<style></style>
