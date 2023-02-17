<template>
  <div>
    <ServiceHeader></ServiceHeader>
    <div class="flex">
      <ServiceNavbar></ServiceNavbar>

      <!-- ACCOUNT -->

      <div class="w-screen">
        <div class="flex flex-col space-y-10">
          <!-- 
        # 네이버님 안녕하세요
        -->
          <nav class="flex flex-wrap justify-between p-8 text-gray-800">
            <h1 class="font-bold">{{ serviceUserName }} 님, 안녕하세요</h1>
            <h3 class="font-bold text-gray-500">DASHBOARD</h3>
          </nav>
          <div class="flex flex-col">
            <div class="flex flex-col justify-center">
              <div class="inline-block w-auto py-2 space-y-4 sm:px-6 lg:px-8">
                <div class="flex flex-wrap justify-between px-5">
                  <p class="text-2xl font-medium">계정 리스트 조회</p>
                  <div class="flex justify-center space-x-2">
                    <button
                      type="button"
                      class="inline-block rounded bg-blue-600 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg"
                      @click="moveToAccountCreate()"
                    >
                      계정 생성
                    </button>
                  </div>
                </div>
                <div class="overflow-hidden rounded-md shadow-lg">
                  <table class="min-w-full">
                    <thead class="bg-white border-b">
                      <tr>
                        <th
                          scope="col"
                          class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                        ></th>
                        <th
                          scope="col"
                          class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                        >
                          기업명
                        </th>
                        <th
                          scope="col"
                          class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                        >
                          계약기간
                        </th>
                        <th
                          scope="col"
                          class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                        >
                          담당자
                        </th>
                        <th
                          scope="col"
                          class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                        >
                          ID
                        </th>
                        <th
                          scope="col"
                          class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                        >
                          연락처
                        </th>
                        <th
                          scope="col"
                          class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                        >
                          이메일
                        </th>
                      </tr>
                    </thead>
                    <tbody
                      v-for="(corporate, index) in corporates"
                      :key="corporate.id"
                    >
                      <tr
                        class="transition duration-300 ease-in-out bg-white border-b hover:bg-gray-100"
                      >
                        <td
                          class="px-6 py-4 text-sm font-medium text-gray-900 whitespace-nowrap"
                        >
                          {{ index + 1 }}
                        </td>
                        <td
                          class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                        >
                          {{ corporate.companyName }}
                        </td>
                        <td
                          class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                        >
                          {{ corporate.startDate.slice(0, 10) }} ~
                          {{ corporate.endDate.slice(0, 10) }}
                        </td>
                        <td
                          class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                        >
                          {{ corporate.name }}
                        </td>
                        <td
                          class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                        >
                          {{ corporate.userId }}
                        </td>
                        <td
                          class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                        >
                          {{ corporate.tel }}
                        </td>
                        <td
                          class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                        >
                          {{ corporate.email }}
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import ServiceHeader from '@/components/ServiceHeader.vue';
import ServiceNavbar from '@/components/ServiceNavbar.vue';

import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const corporates = ref(null);
const getCorporateInfos = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  console.log(user.accessToken);
  axios
    .get(`${BASE_URL}/serviceusers/accounts/`, {
      Authorization: `Bearer ${user.accessToken}`,
    })
    .then(res => {
      console.log('res.data: ', res.data);
      corporates.value = res.data;
      console.log('corporates.value: ', corporates.value);
    });
};

const serviceUserName = ref('');
const getServiceUserName = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/serviceusers/me`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log(res.data);
      serviceUserName.value = res.data.name;
    })
    .catch(err => {
      console.log(err.message);
    });
};

const router = useRouter();
const moveToAccountCreate = () => {
  router.push({ name: 'ServiceAccountCreate' });
};

onMounted(() => {
  getCorporateInfos();
  getServiceUserName();
});
</script>

<style lang="scss" scoped></style>
