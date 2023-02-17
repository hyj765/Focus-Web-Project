<template>
  <div>
    <h1>서비스 어드민 계정생성</h1>
    <h2>{{ name }}</h2>
    <div>
      회사이름<input
        v-model="companyName"
        type="text"
        id="companyName"
        required="true"
      />
      계정생성일<input
        v-model="startDate"
        type="date"
        id="startDate"
        required="true"
      />
      계정만료일<input v-model="endDate" type="date" id="endDate" required="" />
      담당자 전화번호<input
        v-model="tel"
        type="tel"
        id="tel"
        required="true"
        placeholder="010-xxx-xxxx"
      />
      담당자 이메일<input
        v-model="email"
        type="email"
        id="email"
        required="true"
        placeholder="example@blackbunny.com"
      />
      담당자 이름<input
        v-model="managerName"
        type="text"
        id="managerName"
        required="true"
      />
      회사직종<input
        v-model="industry"
        type="text"
        id="industry"
        required="true"
      />
      회사규모<select v-model="size" id="companysize">
        <option v-for="item in items" :value="item.value" :key="item.id">
          {{ item.value }}
        </option>
      </select>
    </div>
    <button @click="createCompanyAdmin">계정생성</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';

const store = useStore();

const items = ref([
  { id: 1, value: '대기업' },
  { id: 2, value: '중견기업' },
  { id: 3, value: '중소기업' },
]);
const companyName = ref('');
const startDate = ref(new Date().toLocaleDateString);
const endDate = ref(null);
const tel = ref(null);
const email = ref(null);
const managerName = ref(null);
const industry = ref(null);
const size = ref(null);

// const check = () => {
//   if (typeof this.companyName === 'undefined') {
//     return false;
//   }
//   if (typeof this.startDate === 'undefined') {
//     return false;
//   }
//   if (typeof this.endDate === 'undefined') {
//     return false;
//   }
//   if (typeof this.tel === 'undefined') {
//     return false;
//   }
//   if (typeof this.email === 'undefined') {
//     return false;
//   }
//   if (typeof this.managerName === 'undefined') {
//     return false;
//   }
//   if (typeof this.size === 'undefined') {
//     return false;
//   }
//   return true;
// };
const createCompanyAdmin = () => {
  console.log(store.state.user);
  store
    .dispatch('createcompanyaccount', {
      companyName: companyName.value,
      startDate: startDate.value,
      endDate: endDate.value,
      tel: tel.value,
      email: email.value,
      managerName: managerName.value,
      industry: industry.value,
      size: size.value,
    })
    .then(data => {
      console.log(data);
    });
};
</script>

<style lang="scss" scoped></style>
