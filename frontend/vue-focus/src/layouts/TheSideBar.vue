<template>
  <div>
    <link
      rel="stylesheet"
      href="https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css"
    />

    <div class="flex flex-row min-h-screen">
      <div class="flex flex-col w-56 overflow-hidden bg-white/50">
        <ul class="flex flex-col py-4 divide-y space-y-60">
          <div>
            <li>
              <a
                href="#"
                class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
              >
                <span
                  class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                  ><i class="bx bxs-dashboard"></i
                ></span>
                <span class="text-sm font-medium">홈 / 대시보드</span>
              </a>
            </li>
            <li>
              <a
                href="#"
                class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
              >
                <span
                  class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                  ><i class="bx bx-id-card"></i
                ></span>
                <span class="text-sm font-medium">계정관리</span>
              </a>
            </li>
          </div>
          <li>
            <a
              href="#"
              class="flex flex-row items-center h-12 text-gray-500 transition-transform duration-200 ease-in transform hover:translate-x-2 hover:text-gray-800"
            >
              <span
                class="inline-flex items-center justify-center w-12 h-12 text-lg text-gray-400"
                ><i class="bx bxs-zap"></i
              ></span>
              <span class="text-sm font-medium">공지기능</span>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>
