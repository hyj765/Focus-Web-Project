<template>
  <div class="flex space-x-2 justify-center">
    <div
      class="bg-black shadow-lg mx-auto w-2/5 max-w-full text-sm pointer-events-auto bg-clip-padding rounded-lg block"
    >
      <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
        <div class="overflow-hidden shadow-lg rounded-md">
          <table class="min-w-full">
            <thead class="bg-white border-b">
              <tr>
                <th
                  scope="col"
                  class="text-sm font-medium text-gray-900 px-2 py-4 text-left"
                >
                  <p style="padding-left: 50px">전형명</p>
                </th>
                <th
                  scope="col"
                  class="text-sm font-medium text-gray-900 px-2 text-left"
                >
                  <p style="padding-left: 15px">면접일</p>
                </th>
                <th
                  scope="col"
                  class="text-sm font-medium text-gray-900 px-2 text-left"
                >
                  <p style="padding-left: 50px">면접시각</p>
                </th>
                <th
                  scope="col"
                  class="text-sm font-medium text-gray-900 px-2 text-left"
                  style="margin-right: 10rem"
                >
                  <p style="padding-left: 1px">전형차수</p>
                </th>
                <th
                  scope="col"
                  class="text-sm font-medium text-gray-900 px-6 py-4 text-left"
                ></th>
              </tr>
            </thead>
            <tbody>
              <tr
                class="bg-white border-b transition duration-300 ease-in-out hover:bg-gray-100"
              >
                <td
                  class="px-6 py-1 whitespace-nowrap text-sm font-medium text-gray-900"
                >
                  두나무 개발자 FE
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  02/25
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <p>13:40 ~ 15:50 (50분)</p>
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <p>3차</p>
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <button
                    type="button"
                    class="text-white font-medium rounded-lg text-sm px-4 py-2.5 text-center"
                    style="width: auto; background-color: rgb(9, 44, 76)"
                    @click="goSettingRoom"
                  >
                    입장
                  </button>
                </td>
              </tr>
              <tr
                class="bg-white border-b transition duration-300 ease-in-out hover:bg-gray-100"
              >
                <td
                  class="px-6 py-1 whitespace-nowrap text-sm font-medium text-gray-900"
                >
                  두나무 개발자 FE
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  02/25
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <p>13:40 ~ 15:50 (50분)</p>
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <p>3차</p>
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <button
                    type="button"
                    class="text-white font-medium rounded-lg text-sm px-4 py-2.5 text-center"
                    style="width: auto; background-color: rgb(9, 44, 76)"
                    @click="goSettingRoom"
                  >
                    입장
                  </button>
                </td>
              </tr>
              <tr
                class="bg-white border-b transition duration-300 ease-in-out hover:bg-gray-100"
              >
                <td
                  class="px-6 py-1 whitespace-nowrap text-sm font-medium text-gray-900"
                >
                  두나무 개발자 FE
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  02/25
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <p>13:40 ~ 15:50 (50분)</p>
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <p>3차</p>
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <button
                    type="button"
                    class="text-white font-medium rounded-lg text-sm px-4 py-2.5 text-center"
                    style="width: auto; background-color: rgb(9, 44, 76)"
                    @click="goSettingRoom"
                  >
                    입장
                  </button>
                </td>
              </tr>
              <tr
                class="bg-white border-b transition duration-300 ease-in-out hover:bg-gray-100"
              >
                <td
                  class="px-6 py-1 whitespace-nowrap text-sm font-medium text-gray-900"
                >
                  두나무 개발자 FE
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  02/25
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <p>13:40 ~ 15:50 (50분)</p>
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <p>3차</p>
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <button
                    type="button"
                    class="text-white font-medium rounded-lg text-sm px-4 py-2.5 text-center"
                    style="width: auto; background-color: rgb(9, 44, 76)"
                    @click="goSettingRoom"
                  >
                    입장
                  </button>
                </td>
              </tr>
              <tr
                class="bg-white border-b transition duration-300 ease-in-out hover:bg-gray-100"
              >
                <td
                  class="px-6 py-1 whitespace-nowrap text-sm font-medium text-gray-900"
                >
                  두나무 개발자 FE
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  02/25
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <p>13:40 ~ 15:50 (50분)</p>
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <p>3차</p>
                </td>
                <td
                  class="text-sm text-gray-900 font-light px-6 py-1 whitespace-nowrap"
                >
                  <button
                    type="button"
                    class="text-white font-medium rounded-lg text-sm px-4 py-2.5 text-center"
                    style="width: auto; background-color: rgb(9, 44, 76)"
                    @click="goSettingRoom"
                  >
                    입장
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import router from '@/router';

const goSettingRoom = () => router.push({ name: 'InterviewRoom' });
</script>

<style lang="scss" scoped></style>
