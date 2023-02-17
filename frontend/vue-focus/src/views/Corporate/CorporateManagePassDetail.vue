<template>
  <div>
    <div
      v-for="(applicant, index) in applicantlist"
      :content="applicant.value"
      :key="index"
    >
      <p>{{ applicant.code }} | {{ applicant.name }}</p>
      <input type="radio" id="{{ applicant.id }}" />
    </div>
    <button @click="decisionNextStep()">전형 종료 [결과확정]</button>
  </div>
</template>

<script setup>
import { onMounted, ref } from '@vue/runtime-core';
import axios from 'axios';
import { useRoute } from 'vue-router';
const BASE_URL = 'https://i8a106.p.ssafy.io/api';
// 합불 처리 시에 받는 데이터
// {사용자 번호 Long, process번호  Long, 합불여부=문자  "p" or "np"}
//
const route = useRoute();

const applicantlist = ref([]);
const applicantResult = ref([]);
const decisionNextStep = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .post(`${BASE_URL}/interview/decision/pass`, applicantResult, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(data => {
      console.log(data.data);
    })
    .catch(err => {
      console.log(err);
    });
};
const getPassAplicant = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/interview/interview/applicants/${route.params.id}`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(data => {
      applicantlist.value = data.data;
      console.log(applicantlist.value);
    });
};

onMounted(() => {
  getPassAplicant();
});
</script>

<style lang="scss" scoped></style>
