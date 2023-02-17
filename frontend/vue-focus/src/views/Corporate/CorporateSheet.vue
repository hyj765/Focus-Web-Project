<template>
  <div>
    <CorporateHeader></CorporateHeader>
    <div class="flex">
      <CorporateNavbar></CorporateNavbar>
      <div class="w-screen">
        <div class="flex flex-col space-y-10">
          <nav class="flex flex-wrap justify-between p-8 text-gray-800">
            <h1 class="font-bold">네이버 님, 안녕하세요</h1>
            <h3 class="font-bold text-gray-500">Sheet</h3>
          </nav>
          <p class="px-10 text-2xl font-medium">평가지 관리</p>
          <p class="px-10 pt-1 text-gray-700 font-regular">
            평가지의 항목을 입력해주세요
          </p>

          <!-- # 탭 -->
          <div class="max-w-2xl mx-auto">
            <div class="mb-4 border-b border-gray-200">
              <ul
                class="flex flex-wrap -mb-px"
                id="myTab"
                data-tabs-toggle="#myTabContent"
                role="tablist"
              >
                <CorporateSheetList></CorporateSheetList>
                <!-- <li class="mr-2" role="presentation">
            <button
              class="inline-block px-4 py-4 text-sm font-medium text-center text-gray-500 border-b-2 border-transparent rounded-t-lg hover:text-gray-600 hover:border-gray-300 active"
              id="dashboard-tab"
              data-tabs-target="#dashboard"
              type="button"
              role="tab"
              aria-controls="dashboard"
              aria-selected="true"
            >
              평가지 2
            </button>
          </li>
          <li class="mr-2" role="presentation">
            <button
              class="inline-block px-4 py-4 text-sm font-medium text-center text-gray-500 border-b-2 border-transparent rounded-t-lg hover:text-gray-600 hover:border-gray-300"
              id="settings-tab"
              data-tabs-target="#settings"
              type="button"
              role="tab"
              aria-controls="settings"
              aria-selected="false"
            >
              평가지 3
            </button>
          </li> -->
                <li role="presentation">
                  <button
                    @click="$emit('update:sheet')"
                    class="inline-block px-4 py-4 text-sm font-medium text-center text-gray-500 border-b-2 border-transparent rounded-t-lg hover:text-gray-600 hover:border-gray-300"
                    id="contacts-tab"
                    data-tabs-target="#contacts"
                    type="button"
                    role="tab"
                    aria-controls="contacts"
                    aria-selected="false"
                  >
                    <i class="bx bx-plus"></i>
                  </button>
                </li>
              </ul>
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

import CorporateSheetList from './CorporateSheetList.vue';
</script>

<style lang="scss" scoped></style>
