<template>
  <div>
    <CorporateHeader></CorporateHeader>
    <div class="flex">
      <CorporateNavbar></CorporateNavbar>
      <div class="w-screen">
        <div class="flex flex-col space-y-10">
          <nav class="flex flex-wrap justify-between p-8 text-gray-800">
            <h1 class="font-bold">네이버 님, 안녕하세요</h1>
            <h3 class="font-bold text-gray-500">Statics</h3>
          </nav>
          <p class="px-10 text-2xl font-medium">합격자 통계</p>
          <p class="px-10 pt-1 text-gray-700 font-regular">
            합격자의 통계를 확인하세요
          </p>

          <div v-if="processes.length === 0">
            현재 통계가 계산된 프로세스가 없습니다.
          </div>
          <div class="w-full px-20 pt-8">
            <div
              v-for="(process, index) in processes"
              :content="process.value"
              :key="index"
              class="w-full p-2 bg-white/50 shawow-lg rounded-xl"
            >
              <!-- # section 리스트 -->
              <Disclosure v-slot="{ open }">
                <DisclosureButton
                  class="flex justify-between w-full px-4 py-2 text-lg text-left text-gray-900 rounded-lg bg-white/50 hover:bg-white focus:outline-none focus-visible:ring focus-visible:ring-white focus-visible:ring-opacity-75"
                >
                  <span class="font-semibold">{{ process.name }}</span>
                  <ChevronUpIcon
                    :class="open ? '' : 'rotate-180 transform'"
                    class="text-gray-900 w-7 h-7"
                  />
                </DisclosureButton>
                <!-- # 패널 섹션 -->
                <DisclosurePanel class="px-4 pt-4 pb-2 text-sm text-gray-500">
                  <div>
                    <div
                      class="max-w-2xl px-4 py-16 mx-auto sm:py-24 sm:px-6 lg:max-w-7xl lg:px-8"
                    >
                      <div
                        class="grid grid-cols-1 gap-y-10 gap-x-6 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-6 xl:gap-x-8"
                      >
                        <a href="#" class="group">
                          <div
                            class="w-full overflow-hidden bg-gray-200 rounded-lg aspect-w-1 aspect-h-1 xl:aspect-w-7 xl:aspect-h-8"
                          >
                            <img
                              src="https://tailwindui.com/img/ecommerce-images/category-page-04-image-card-01.jpg"
                              alt="Tall slender porcelain bottle with natural clay textured body and cork stopper."
                              class="object-cover object-center w-full h-full group-hover:opacity-75"
                            />
                          </div>
                          <p class="mt-4 text-lg font-medium text-gray-900">
                            김민경
                          </p>
                        </a>

                        <!-- More products... -->
                      </div>
                    </div>
                  </div>
                </DisclosurePanel>
              </Disclosure>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import CorporateHeader from '@/components/CorporateHeader.vue';
import CorporateNavbar from '@/components/CorporateNavbar.vue';

import { Disclosure, DisclosureButton, DisclosurePanel } from '@headlessui/vue';
import { ChevronUpIcon } from '@heroicons/vue/20/solid';
import axios from 'axios';
import { onMounted, ref } from 'vue';

const processes = ref([]);
const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const getProcess = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/interview/process/passed/process`, {
      params: {
        CompanyId: `${user.id}`,
      },
    })
    .then(data => {
      processes.value = data.data;
      // console.log(data.data);
      console.log(processes.value.length);
    })
    .catch(err => {});
};

onMounted(() => {
  getProcess();
});
</script>

<style lang="scss" scoped></style>
