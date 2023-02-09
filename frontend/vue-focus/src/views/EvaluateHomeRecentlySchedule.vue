<template>
  <div class="flex space-x-2 justify-center" style="margin-top: 20px">
    <div
      class="bg-white shadow-lg mx-auto w-2/5 max-w-full text-sm pointer-events-auto bg-clip-padding rounded-lg block"
    >
      <ul>
        <!-- # 면접리스트 1 -->
        <li
          class="text-lg bg-white rounded-b-lg break-words text-gray-700"
          style="padding: 5px; border-radius: 12px"
        >
          <section class="flex flex-row px-2">
            <div class="flex flex-col space-y-2 w-1/2 justify-center">
              <div style="margin-top: 4px; margin-left: 4px">
                <p
                  class=""
                  style="
                    background-color: rgb(9, 44, 76);
                    font-weight: bold;
                    color: white;
                    font-size: 13px;
                    border-radius: 11px;
                    width: 10rem;
                    height: auto;
                    text-align: center;
                    padding-left: 5px;
                    padding-right: 5px;
                    margin-top: 5px;
                  "
                >
                  13:40 ~ 15:50 (50분)
                </p>
              </div>
              <div style="margin-top: 10px">
                <p
                  class="text-xl font-bold"
                  style="margin-bottom: 1px; font-size: 18px; margin-left: 4px"
                >
                  두나무 증권 FE 개발자
                </p>
              </div>
              <div style="margin-top: 2px">
                <p style="font-size: 13px; margin-left: 4px">
                  평가자 3 | 지원자 4
                </p>
              </div>
            </div>
            <div style="margin-top: 3px">
              <ul class="flex flex-col w-auto space-y-2">
                <!-- 
                # 리스트 component
                -->
                <li
                  class="flex flex-row items-center"
                  style="margin-left: -45px"
                >
                  <div
                    class="bg-blue-500 w-5 h-5 ring-1 ring-slate-900/5 shadow-lg rounded-full flex items-center justify-center"
                  >
                    <i class="bx bx-right-arrow-alt text-white"></i>
                  </div>
                  <div class="px-2">
                    <p style="font-size: 14px">2022.02.04 ~ 2022.02.05</p>
                  </div>
                </li>
                <li
                  class="flex flex-row items-center"
                  style="margin-left: -45px"
                >
                  <div
                    class="animate-bounce bg-blue-500 w-5 h-5 ring-1 ring-slate-900/5 shadow-lg rounded-full flex items-center justify-center"
                  >
                    <i class="bx bx-right-arrow-alt text-white"></i>
                  </div>
                  <div class="px-2">
                    <p style="font-size: 14px">2022.02.04 ~ 2022.02.05</p>
                  </div>
                </li>
                <li
                  class="flex flex-row items-center"
                  style="margin-left: -45px"
                >
                  <div
                    class="bg-blue-500 w-5 h-5 ring-1 ring-slate-900/5 shadow-lg rounded-full flex items-center justify-center"
                  >
                    <i class="bx bx-right-arrow-alt text-white"></i>
                  </div>
                  <div class="px-2">
                    <p style="font-size: 14px">2022.02.04 ~ 2022.02.05</p>
                  </div>
                </li>
                <!-- 
                #리스트 component end
                -->
              </ul>
            </div>
            <div class="buttonBox" style="margin-left: 20px; margin-top: 7px">
              <div class="buttonItem">
                <button
                  type="button"
                  class="text-white font-medium rounded-lg text-sm px-5 py-2.5 text-center"
                  style="width: auto; background-color: rgb(9, 44, 76)"
                  @click="goSettingRoom"
                >
                  입장
                </button>
              </div>
            </div>
          </section>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import router from '@/router';

const goSettingRoom = () => router.push({ name: 'InterviewRoom' });
</script>

<style>
.buttonBox {
  display: flex;
  align-items: center;
  margin-left: auto;
}

.buttonItem {
  display: inline-block;
}
</style>
