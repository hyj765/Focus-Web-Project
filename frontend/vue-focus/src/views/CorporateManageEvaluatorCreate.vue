<template>
  <div>
    <div>
      <p>사원번호</p>
      <br />
      <input v-model="code" type="text" id="evaluatorcode" required="true" />
      <p>부서</p>
      <br />
      <select v-model="department" id="evaluatordepartment">
        <option
          v-for="(department, index) in departments"
          :key="index"
          :value="department"
        >
          {{ department }}
        </option>
      </select>
      <p>이메일</p>
      <br />
      <input
        v-model="email"
        type="text"
        id="evaluatoremail"
        required="true"
        placeholder="example@blackbunny.com"
      />
      <p>이름</p>
      <br />
      <input v-model="name" type="text" id="evaluatorname" required="true" />
      <p>직책</p>
      <br />
      <select v-model="position" id="evaluatorposition">
        <option
          v-for="position in positions"
          :key="position.id"
          :value="position.value"
        >
          {{ position.value }}
        </option>
      </select>
      <p>전화번호</p>
      <br />
      <input
        v-model="tel"
        type="text"
        id="evaluatortel"
        required="true"
        placeholder="010-1234-5678"
      />
    </div>
    <br />
    <button @click="createEvaluator()">계정생성</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useStore } from 'vuex';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const store = useStore();

const positions = ref([
  { id: 1, value: '사장' },
  { id: 2, value: '부사장' },
  { id: 3, value: '전무' },
  { id: 4, value: '상무' },
  { id: 5, value: '이사' },
  { id: 6, value: '부장' },
  { id: 7, value: '차장' },
  { id: 8, value: '과장' },
  { id: 9, value: '대리' },
  { id: 10, value: '주임' },
  { id: 11, value: '사원' },
]);
const departments = store.state.currentDepartments;

const code = ref('');
const department = ref('');
const email = ref('');
const name = ref('');
const position = ref('');
const tel = ref('');

const getEvaluatorInfo = () => {
  const evaluatorInfo = {
    code: code.value,
    department: department.value,
    email: email.value,
    name: name.value,
    position: position.value,
    tel: tel.value,
  };
  return evaluatorInfo;
};

const createEvaluator = () => {
  const evaluatorInfo = getEvaluatorInfo();
  let evaluatorInfos = [];
  evaluatorInfos.push(evaluatorInfo);
  const evaluatorInfosJson = JSON.stringify(evaluatorInfos);
  const user = JSON.parse(localStorage.getItem('user'));
  console.log(evaluatorInfo);
  console.log(evaluatorInfos);
  console.log(evaluatorInfosJson);
  console.log(user.accessToken);
  axios
    .post(`${BASE_URL}/companyusers/evaluators`, evaluatorInfosJson, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log(res.data);
    })
    .catch(err => {
      console.log(err.message);
    });
};
</script>

<style lang="scss" scoped></style>
