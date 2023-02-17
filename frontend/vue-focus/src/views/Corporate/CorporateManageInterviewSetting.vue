<template>
  <div>
    {{ route.params.processid }}
    {{ route.params.id }}
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute } from 'vue-router';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const route = useRoute();
const processId = route.params.processid;
const interviewId = route.params.id;
const evaluators = ref([]);
const applicants = ref([]);

// 해당 면접 지원자 리스트 조회
const getInterviewApplicants = interviewId => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/interview/schedule/${interviewId}/applicants`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('applicants: ', res.data);
      applicants.value = res.data;
      console.log(applicants.value);
    })
    .catch(err => {
      console.log(err.message);
    });
};

// 해당 면접 평가자 리스트 조회
const getInterviewEvaluators = interviewId => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/interview/schedule/${interviewId}/evaluators`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('evaluators: ', res.data);
      evaluators.value = res.data;
      console.log(evaluators.value);
    })
    .catch(err => {
      console.log(err.message);
    });
};

onMounted(() => {
  getInterviewApplicants(interviewId);
  getInterviewEvaluators(interviewId);
});
</script>

<style lang="scss" scoped></style>
