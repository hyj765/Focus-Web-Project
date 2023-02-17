<template>
  <div>
    <nav class="flex flex-wrap justify-between p-8 text-gray-800">
      <h1 class="font-bold">{{ companyUserName }} 님, 안녕하세요</h1>
      <h3 class="font-bold text-gray-500">Sheet</h3>
    </nav>
    <p class="px-10 text-xl font-gray-900">평가지를 수정하세요</p>
    <div class="max-w-2xl mx-auto">
      <div id="myTabContent">
        <div
          class="hidden p-4 rounded-lg bg-gray-50"
          id="profile"
          role="tabpanel"
          aria-labelledby="profile-tab"
        >
          <p class="text-sm text-gray-500">평가지의 질문을 입력해주세요</p>
        </div>
        <div id="dashboard" role="tabpanel" aria-labelledby="dashboard-tab">
          <!-- # 평가지 내용 작성 -->
          <div class="mt-10 sm:mt-0">
            <form action="#" method="POST">
              <div class="overflow-hidden shadow sm:rounded-md">
                <div class="px-4 py-5 bg-white sm:p-6">
                  <div class="grid grid-cols-6 gap-6">
                    <div @submit.prevent class="col-span-6 sm:col-span-4">
                      <label
                        for="first-name"
                        class="block text-sm font-medium text-gray-700"
                        >평가지명</label
                      >
                      <div class="flex flex=row">
                        <input
                          type="text"
                          name="first-name"
                          id="first-name"
                          autocomplete="given-name"
                          class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                        />
                        <div class="flex flex-row pl-5">
                          <div class="flex justify-center space-x-2">
                            <button
                              type="button"
                              class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-blue-400 rounded-full shadow-lg hover:bg-blue-500 hover:shadow-lg focus:bg-blue-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-500 active:shadow-lg w-9 h-9"
                            >
                              <i class="text-xl bx bx-check"></i>
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div class="col-span-12 sm:col-span-12">
                      <label
                        for="email-address"
                        class="block text-sm font-medium text-gray-700"
                        >평가지 항목</label
                      >
                      <div class="flex flex-row">
                        <input
                          type="text"
                          name="email-address"
                          id="email-address"
                          autocomplete="email"
                          class="block w-full mt-1 border-gray-300 rounded-md shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                        />
                        <div class="flex flex-row pl-5">
                          <div class="flex justify-center space-x-2">
                            <button
                              type="button"
                              class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-gray-400 rounded-full shadow-lg hover:bg-gray-500 hover:shadow-lg focus:bg-gray-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-gray-500 active:shadow-lg w-9 h-9"
                            >
                              <i class="text-xl bx bx-plus"></i>
                            </button>
                            <button
                              type="button"
                              class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-blue-400 rounded-full shadow-lg hover:bg-blue-500 hover:shadow-lg focus:bg-blue-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-500 active:shadow-lg w-9 h-9"
                            >
                              <i class="text-xl bx bx-check"></i>
                            </button>
                            <button
                              type="button"
                              class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-red-400 rounded-full shadow-lg hover:bg-red-500 hover:shadow-lg focus:bg-red-500 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-red-500 active:shadow-lg w-9 h-9"
                            >
                              <i class="text-xl bx bx-x"></i>
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="px-4 py-3 text-right bg-gray-50 sm:px-6">
                  <button
                    type="submit"
                    class="inline-flex justify-center px-4 py-2 text-sm font-medium text-white bg-indigo-600 border border-transparent rounded-md shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                  >
                    Save
                  </button>
                  <button
                    @click="goSheetDetail"
                    type="submit"
                    class="inline-flex justify-center px-4 py-2 text-sm font-medium text-white bg-red-600 border border-transparent rounded-md shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                  >
                    Cancle
                  </button>
                </div>
              </div>
            </form>
          </div>

          <!-- # 평가지 내용 작성 끝 -->
        </div>
        <div
          class="hidden p-4 rounded-lg bg-gray-50"
          id="settings"
          role="tabpanel"
          aria-labelledby="settings-tab"
        >
          <p class="text-sm text-gray-500">웅냥</p>
        </div>
        <div
          class="hidden p-4 rounded-lg bg-gray-50"
          id="contacts"
          role="tabpanel"
          aria-labelledby="contacts-tab"
        >
          <p class="text-sm text-gray-500">
            This is some placeholder content the
            <strong class="font-medium text-gray-800"
              >Contacts tab's associated content</strong
            >. Clicking another tab will toggle the visibility of this one for
            the next. The tab JavaScript swaps classes to control the content
            visibility and styling.
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
const route = useRoute();
const router = useRouter();
const id = route.params.id;

const goSheetDetail = () => {
  // router.push(`/evaluation/sheets/${id}`);
  router.push({ name: 'SheetDetail', params: { id } });
};
</script>

<style lang="scss" scoped></style>
