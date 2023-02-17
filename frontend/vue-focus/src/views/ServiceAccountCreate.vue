<template>
  <div>
    <nav class="flex flex-wrap justify-between p-8 text-gray-800">
      <h1 class="font-bold">네이버 님, 안녕하세요</h1>
      <h3 class="font-bold text-gray-500">DASHBOARD</h3>
    </nav>
    <p class="px-10 text-xl font-gray-900">기업 계정을 생성하세요</p>
    <div class="flex items-center p-10">
      <div
        class="p-5 space-y-5 bg-white divide-y divide-gray-200 rounded-lg shadow-lg"
      >
        <div class="m-1">
          <p class="p-2 text-xl font-medium text-black">계약기간</p>
          <div class="flex flex-row items-end space-x-5">
            <div class="flex flex-col p-2">
              <p class="text-lg font-medium text-gray-900">계정생성일</p>
              <input
                class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                v-model="startDate"
                type="date"
                id="startDate"
                required="true"
              />
            </div>
            <div class="pb-3">
              <p class="text-xl font-regular">~</p>
            </div>
            <div class="flex flex-col p-2">
              <p class="text-lg font-medium text-gray-900">계정만료일</p>
              <input
                class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                v-model="endDate"
                type="date"
                id="endDate"
                required=""
              />
            </div>
          </div>
        </div>
        <div class="m-1">
          <p class="p-2 text-xl font-medium text-black">회사</p>
          <div class="flex flex-row space-x-5">
            <div class="flex flex-col p-2">
              <p class="text-lg font-medium text-gray-900">회사명</p>
              <input
                class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                v-model="companyName"
                type="text"
                id="companyName"
                required="true"
              />
            </div>
            <div class="flex flex-col p-2">
              <p class="text-lg font-medium text-gray-900">직종</p>
              <input
                class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                v-model="industry"
                type="text"
                id="industry"
                required="true"
              />
            </div>
            <div class="flex flex-col p-2">
              <p class="text-lg font-medium text-gray-900">규모</p>
              <select
                class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border0indigo-500 focus:ring=indigo-500 sm:text-sm"
                v-model="size"
                id="companysize"
              >
                <option
                  v-for="category in sizeCategories"
                  :value="category.value"
                  :key="category.id"
                >
                  {{ category.value }}
                </option>
              </select>
            </div>
          </div>
        </div>
        <div class="m-1">
          <p class="p-2 text-xl font-medium text-black">담당자</p>
          <div class="flex flex-row space-x-5">
            <div class="p-2">
              <p class="text-lg font-medium text-gray-900">이름</p>
              <input
                class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                v-model="name"
                type="text"
                id="name"
                required="true"
              />
            </div>
            <div class="p-2">
              <p class="text-lg font-medium text-gray-900">이메일</p>
              <input
                class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                v-model="email"
                type="email"
                id="email"
                required="true"
                placeholder="example@blackbunny.com"
              />
            </div>
            <div class="p-2">
              <p class="text-lg font-medium text-gray-900">전화번호</p>
              <input
                class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                v-model="tel"
                type="tel"
                id="tel"
                required="true"
                placeholder="010-xxx-xxxx"
              />
            </div>
          </div>
        </div>
        <div>
          <div class="flex justify-end pt-5">
            <button
              @click="[createCompanyAdmin(), emitCompanyCreated()]"
              type="button"
              class="inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
            >
              계정생성
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';

const store = useStore();

const emit = defineEmits(['company-created']);

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
const emitCompanyCreated = () => {
  emit('company-created');
};
</script>

<style lang="scss" scoped></style>
