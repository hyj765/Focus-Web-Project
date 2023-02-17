<template>
  <div>
    <div class="flex flex-col space-y-10">
      <!-- 
    # 네이버님 안녕하세요
    -->
      <nav class="flex flex-wrap justify-between px-8 py-4 text-gray-800">
        <h1 class="font-bold">네이버 님, 안녕하세요</h1>
        <h3 class="font-bold text-gray-500">DASHBOARD</h3>
      </nav>

    </div>
    <div class="flex flex-col items-center justify-center">
      <p class="p-2 text-lg font-bold text-gray-500">
        <i class="pr-1 text-indigo-500 bx bxs-check-circle"></i>
        잠시후에 시작할 면접입니다
      </p>
      <div
        class="flex flex-row w-auto h-auto pb-3 pl-2 pr-4 bg-white rounded-lg shadow-lg"
      >
        <div class="flex flex-col items-center w-auto h-auto text-gray-900">
          <p class="flex items-center justify-center p-4 text-2xl font-bold">
            두나무 증권 개발자
          </p>
          <p
            class="flex items-center justify-center px-4 py-1 text-white bg-indigo-900 rounded-xl"
          >
            13:40 ~ 14:40 (50분)
          </p>
        </div>
        <div class="flex items-center justify-center">
          <button
            type="button"
            class="place-content-center inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
          >
            입장
          </button>
        </div>
      </div>
    </div>
    <!-- # 리스트 -->
    <div class="flex flex-col pt-5">
      <div class="flex flex-col justify-center">
        <div class="inline-block w-auto py-2 space-y-4 sm:px-6 lg:px-8">
          <div class="flex flex-wrap justify-between px-5">
            <p class="text-2xl font-medium">오늘 예정된 면접</p>
          </div>
          <div
            class="flex items-center justify-center overflow-hidden rounded-md shadow-lg w-fit"
          >
            <table class="min-w-full">
              <thead class="bg-white border-b">
                <tr>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-gray-900 text-middle"
                  >
                    전형명
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-gray-900 text-middle"
                  >
                    면접일
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-gray-900 text-middle"
                  >
                    면접시각
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-gray-900 text-middle"
                  >
                    전형차수
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-gray-900 text-middle"
                  ></th>
                </tr>
              </thead>
              <tbody>
                <tr
                  class="transition duration-300 ease-in-out bg-white border-b hover:bg-gray-100"
                >
                  <td
                    class="px-6 py-4 text-lg font-medium text-gray-900 whitespace-nowrap"
                  >
                    두나무 개발자 FE
                  </td>
                  <td
                    class="px-6 py-4 text-lg font-light text-gray-900 whitespace-nowrap"
                  >
                    02/25
                  </td>
                  <td
                    class="px-6 py-4 text-lg font-light text-gray-900 whitespace-nowrap"
                  >
                    13:40 ~ 15:50 (50분)
                  </td>
                  <td
                    class="px-6 py-4 text-lg font-light text-gray-900 whitespace-nowrap"
                  >
                    3차
                  </td>
                  <td
                    class="px-6 py-4 text-lg font-light text-gray-900 whitespace-nowrap"
                  >
                    <div class="flex items-center justify-center">
                      <button
                        type="button"
                        class="place-content-center inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                      >
                        입장
                      </button>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>


      <div class="flex justify-center">
        <!-- <button
          @click="changeCurrentComp(InterviewRoom)"
          type="button"
          class="text-white bg-gradient-to-r from-red-400 via-red-500 to-red-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 shadow-lg shadow-red-500/50 dark:shadow-lg dark:shadow-red-800/80 font-medium rounded-lg text-sm px-5 py-2.5 text-center mr-2 mb-2"
        >
          Red
        </button> -->
        <InterviewRoom></InterviewRoom>
      </div>
      <!-- 
# 현재 인원
-->

    </div>
  </div>
</template>

<script setup>
import { shallowRef } from 'vue';
import InterviewRoom from './InterviewDisplay/InterviewRoom.vue';

const currentComp = shallowRef(InterviewRoom);
const changeCurrentComp = comp => (currentComp.value = comp);
</script>

<style lang="scss" scoped></style>
