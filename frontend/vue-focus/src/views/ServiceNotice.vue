<template>
  <div>
    <h1 class="text-2xl text-red-900">Notice</h1>
    <ul>
      <li v-for="notice in notices" :key="notice">{{ notice.title }}</li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const notices = ref([]);

onMounted(() => {
  axios
    .get('http://localhost:3000/notices')
    .then(({ data }) => {
      notices.value = data.notices.notices;
    })
    .catch(err => {
      console.log(err.message);
    });
});
</script>

<style lang="scss" scoped></style>
