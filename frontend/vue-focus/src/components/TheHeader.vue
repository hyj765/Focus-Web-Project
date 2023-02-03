<template>
  <nav class="bg-white/50 border-gray-200 px-2 sm:px-4 py-2.5 rounded">
    <div class="container flex flex-wrap items-center justify-between mx-auto">
      <div class="flex items-center space-x-2">
        <img class="h-7" src="../assets/blue_opacity.png" alt="logo" />
        <span class="self-center text-xl font-semibold whitespace-nowrap"
          >Focus</span
        >
      </div>
      <div class="hidden w-full md:block md:w-auto" id="navbar-default">
        <ul
          class="flex flex-col items-center p-4 mt-4 border border-gray-100 rounded-lg md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0"
        >
          <!-- <li>
            <RouterLink
              to="/service"
              class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0"
              aria-current="page"
              >서비스관리자</RouterLink
            >
          </li>
          <li>
            <RouterLink
              to="/corporate"
              class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0"
              aria-current="page"
              >기업관리자</RouterLink
            >
          </li>
          <li>
            <RouterLink
              to="/evaluate"
              class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0"
              aria-current="page"
              >평가자</RouterLink
            >
          </li>
          <li>
            <RouterLink
              to="/apply"
              class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0"
              aria-current="page"
              >지원자</RouterLink
            >
          </li> -->
          <!-- <li>
            <button
              type="button"
              class="btn header-item noti-icon waves-effect"
              id="page-header-notifications-dropdown"
              data-bs-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              <i class="bx bx-bell bx-tada"></i>
              <span class="badge bg-danger rounded-pill">3</span>
            </button>
          </li> -->
          <!-- # 채팅 -->

          <li class="bg-white/60 rounded-lg">
            <span
              class="inline-flex items-center justify-center h-12 w-12 text-2xl text-gray-800"
              ><i class="bx bx-conversation"></i
            ></span>
          </li>
          <!-- # FAQ -->
          <li class="bg-white/60 rounded-lg">
            <span
              class="inline-flex items-center justify-center h-12 w-12 text-2xl text-gray-800"
              ><i class="bx bx-question-mark"></i
            ></span>
          </li>
          <!-- # 메일보내기 -->
          <li class="bg-white/60 rounded-lg">
            <span
              class="inline-flex items-center justify-center h-12 w-12 text-2xl text-gray-800"
              ><i class="bx bx-paper-plane"></i
            ></span>
          </li>
          <!-- # 메일함 -->
          <li class="bg-white/60 rounded-lg">
            <span
              class="inline-flex items-center justify-center h-12 w-12 text-2xl text-gray-800"
              ><i class="bx bxs-inbox"></i
            ></span>
          </li>
          <!-- # 종 -->
          <li class="bg-white/60 rounded-lg">
            <span
              class="inline-flex items-center justify-center h-12 w-12 text-2xl text-gray-800"
              ><i class="bx bx-bell"></i
            ></span>
          </li>
          <!-- # 프로필 -->
          <div class="flex items-center space-x-2">
            <img
              class="h-8 rounded-full"
              src="http://www.gravatar.com/avatar/2acfb745ecf9d4dccb3364752d17f65f?s=260&d=mp"
              alt="James Bhatta"
            />

            <h4
              class="font-semibold text-gray-700 capitalize font-poppins tracking-wide"
            >
              James Smith
            </h4>
          </div>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup></script>

<style lang="scss" scoped></style>
