<template>
  <span class="text-sm font-medium">
    <!-- <EvaluatorTime
      v-for="(item, index) in scheduledList"
      :content="item.value"
      :key="index"
      :item="item"
    /> -->
    <!-- <router-link :to="{ name: 'EvaluatorTime', params: { id: item.id } }">
      {{ info.startTime.slice(11, 16) }} ~
      {{ info.endTime.slice(11, 16) }}
    </router-link> -->
  </span>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';

import EvaluatorTime from './EvaluatorTime.vue';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const scheduledList = ref(null);

const getScheduleList = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/evaluators/list/`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('res.data: ', res.data);
      scheduledList.value = res.data['2023-02-17'];
      console.log(scheduledList.value);
    });
};

onMounted(() => {
  getScheduleList();
});
</script>

<style lang="scss" scoped></style>
