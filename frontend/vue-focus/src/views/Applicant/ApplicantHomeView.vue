<template>
  <div class="flex flex-row">
    <div v-for="(i, key) in scheduledApplicants[1]" :key="i">
      <ul
        class="inline-block text-sm font-medium text-center text-gray-500 border-b border-gray-200 dark:border-gray-700 dark:text-gray-400"
      >
        <li class="mr-2">
          <button
            v-bind:class="{
              active: currentTab === scheduledApplicants[1][key].id,
            }"
            v-on:click="currentTab = scheduledApplicants[1][key].id"
            href="#"
            aria-current="page"
            class="inline-block p-4 text-blue-600 bg-gray-100 rounded-t-lg active dark:bg-gray-800 dark:text-blue-500"
          >
            {{ scheduledApplicants[1][key].code }} /
            {{ scheduledApplicants[1][key].name }}
          </button>
        </li>
        <div
          style="overflow: auto"
          class="absolute"
          v-show="currentTab == scheduledApplicants[1][key].id"
        ></div>
      </ul>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const scheduledApplicants = ref([]);
let currentTab = ref('');

const getScheduledApplicant = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/evaluators/applicants`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(response => {
      console.log('response.data : ', response.data);
      scheduledApplicants.value = response.data;
      console.log('scheduledApplicants.value ', scheduledApplicants.value);
    });
};

onMounted(() => {
  getScheduledApplicant();
});
</script>

<style lang="scss" scoped></style>
