<template>
  <nav class="bg-white border-gray-200 px-2 sm:px-4 py-2.5 rounded">
    <div class="container flex flex-wrap items-center justify-between mx-auto">
      <span class="self-center text-xl font-semibold whitespace-nowrap"
        >Focus</span
      >
      <div class="hidden w-full md:block md:w-auto" id="navbar-default">
        <ul
          class="flex flex-col p-4 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0 md:bg-white"
        >
          <li>
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
          </li>
          <div class="flex items-center space-x-4 p-2">
            <img
              class="h-12 rounded-full"
              src="http://www.gravatar.com/avatar/2acfb745ecf9d4dccb3364752d17f65f?s=260&d=mp"
              alt="James Bhatta"
            />
            <div>
              <h4
                class="font-semibold text-lg text-gray-700 capitalize font-poppins tracking-wide"
              >
                James Bhatta
              </h4>
              <span class="text-sm tracking-wide flex items-center space-x-1">
                <svg
                  class="h-4 text-green-500"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z"
                  /></svg
                ><span class="text-gray-600">Verified</span>
              </span>
            </div>
          </div>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup></script>

<style lang="scss" scoped></style>
