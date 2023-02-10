<template>
  <div>
    <div>
      <p>회사이름</p>
      <br />
      <input
        v-model="companyName"
        type="text"
        id="companyName"
        required="true"
      />
      <p>계정생성일</p>
      <br />
      <br />
      <input v-model="startDate" type="date" id="startDate" required="true" />
      <p>계정만료일</p>
      <br />
      <input v-model="endDate" type="date" id="endDate" required="" />
      <p>담당자 전화번호</p>
      <br />
      <input
        v-model="tel"
        type="tel"
        id="tel"
        required="true"
        placeholder="010-1234-5678"
      />
      <p>담당자 이메일</p>
      <br />
      <input
        v-model="email"
        type="email"
        id="email"
        required="true"
        placeholder="example@blackbunny.com"
      />
      <p>담당자 이름</p>
      <br />
      <input v-model="name" type="text" id="name" required="true" />
      <p>회사직종</p>
      <br />
      <input v-model="industry" type="text" id="industry" required="true" />
      <p>회사규모</p>
      <br />
      <select v-model="size" id="companysize">
        <option
          v-for="category in sizeCategories"
          :value="category.value"
          :key="category.id"
        >
          {{ category.value }}
        </option>
      </select>
    </div>
    <br />
    <button @click="createCompanyAdmin()">계정생성</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

// 굳이 vuex store 사용할 필요없음
// logo image url과 skin color가 테이블 컬럼으로 있던데 이건 어떻게 처리?

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const getToday = date => {
  const year = date.getFullYear();
  const month = ('0' + (1 + date.getMonth())).slice(-2);
  const day = ('0' + date.getDate()).slice(-2);
  return year + '-' + month + '-' + day;
};

// 요청 시 'yyyy-mm-dd' 뒤에 'T00:00:00.000000'이 붙지 않으면 에러남
const changeDateFormat = date => {
  return date + 'T00:00:00.000000';
};
const sizeCategories = ref([
  { id: 1, value: '대기업' },
  { id: 2, value: '중견기업' },
  { id: 3, value: '중소기업' },
]);
const companyName = ref('');
const email = ref(null);
const startDate = ref(getToday(new Date()));
const endDate = ref(null);
const tel = ref('');
const name = ref('');
const industry = ref('');
const size = ref('');

const getCompanyInfo = () => {
  const companyInfo = {
    companyName: companyName.value,
    startDate: changeDateFormat(startDate.value),
    endDate: changeDateFormat(endDate.value),
    tel: tel.value,
    email: email.value,
    name: name.value,
    industry: industry.value,
    size: size.value,
    userRole: 2,
  };
  return companyInfo;
};

// 데이터 넘겨줄 때 JSON 문자열로 변환 필요
// localStorage의 토큰 가져올 시 객체로 파싱하여 접근
const createCompanyAdmin = () => {
  const companyInfo = JSON.stringify(getCompanyInfo());
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .post(`${BASE_URL}/serviceusers/accounts`, companyInfo, {
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
