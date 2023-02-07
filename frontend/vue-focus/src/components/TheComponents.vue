<template>
  <div>
    <!-- # 작은 버튼 -->
    <div class="flex justify-center space-x-2">
      <div>
        <button
          type="button"
          class="inline-block leading-normal text-white uppercase transition duration-150 ease-in-out bg-blue-600 rounded-full shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg w-9 h-9"
        >
          <svg
            aria-hidden="true"
            focusable="false"
            data-prefix="fas"
            data-icon="download"
            class="w-3 mx-auto"
            role="img"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 512 512"
          >
            <path
              fill="currentColor"
              d="M216 0h80c13.3 0 24 10.7 24 24v168h87.7c17.8 0 26.7 21.5 14.1 34.1L269.7 378.3c-7.5 7.5-19.8 7.5-27.3 0L90.1 226.1c-12.6-12.6-3.7-34.1 14.1-34.1H192V24c0-13.3 10.7-24 24-24zm296 376v112c0 13.3-10.7 24-24 24H24c-13.3 0-24-10.7-24-24V376c0-13.3 10.7-24 24-24h146.7l49 49c20.1 20.1 52.5 20.1 72.6 0l49-49H488c13.3 0 24 10.7 24 24zm-124 88c0-11-9-20-20-20s-20 9-20 20 9 20 20 20 20-9 20-20zm64 0c0-11-9-20-20-20s-20 9-20 20 9 20 20 20 20-9 20-20z"
            ></path>
          </svg>
        </button>
      </div>
    </div>
    <!-- # 부서별 필터 -->
    <Menu as="div" class="relative inline-block text-left">
      <div>
        <MenuButton
          class="inline-flex justify-center w-full px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md shadow-sm hover:bg-gray-50"
        >
          부서별
          <ChevronDownIcon class="w-5 h-5 ml-2 -mr-1" aria-hidden="true" />
        </MenuButton>
      </div>

      <transition
        enter-active-class="transition duration-100 ease-out"
        enter-from-class="transform scale-95 opacity-0"
        enter-to-class="transform scale-100 opacity-100"
        leave-active-class="transition duration-75 ease-in"
        leave-from-class="transform scale-100 opacity-100"
        leave-to-class="transform scale-95 opacity-0"
      >
        <MenuItems
          class="absolute right-0 z-10 w-56 mt-2 origin-top-right bg-white rounded-md shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none"
        >
          <div class="py-1">
            <MenuItem v-slot="{ active }">
              <a
                href="#"
                :class="[
                  active ? 'bg-gray-100 text-gray-900' : 'text-gray-700',
                  'block px-4 py-2 text-sm',
                ]"
                >Account settings</a
              >
            </MenuItem>
            <MenuItem v-slot="{ active }">
              <a
                href="#"
                :class="[
                  active ? 'bg-gray-100 text-gray-900' : 'text-gray-700',
                  'block px-4 py-2 text-sm',
                ]"
                >Support</a
              >
            </MenuItem>
            <MenuItem v-slot="{ active }">
              <a
                href="#"
                :class="[
                  active ? 'bg-gray-100 text-gray-900' : 'text-gray-700',
                  'block px-4 py-2 text-sm',
                ]"
                >License</a
              >
            </MenuItem>
            <form method="POST" action="#">
              <MenuItem v-slot="{ active }">
                <button
                  type="submit"
                  :class="[
                    active ? 'bg-gray-100 text-gray-900' : 'text-gray-700',
                    'block w-full px-4 py-2 text-left text-sm',
                  ]"
                >
                  Sign out
                </button>
              </MenuItem>
            </form>
          </div>
        </MenuItems>
      </transition>
    </Menu>
    <!-- # 버튼 -->
    <div>
      <div class="flex justify-center space-x-2">
        <button
          type="button"
          class="inline-block rounded bg-blue-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg"
        >
          Button
        </button>
      </div>
    </div>
    <!-- # 기본 레이아웃 -->
    <div class="flex justify-center space-x-2">
      <div
        class="block w-2/5 max-w-full mx-auto text-sm bg-white rounded-lg shadow-lg pointer-events-auto bg-clip-padding"
      >
        <div
          class="flex items-center justify-between px-3 py-2 bg-white border-b border-gray-200 rounded-t-lg bg-clip-padding"
        >
          <div>
            <i class="text-5xl text-blue-700 bx bxs-user-circle"></i>
            <div>
              <p>minofficial</p>
            </div>
          </div>
        </div>
        <!--         
        # 총 평가자 총 지원자
        -->
        <div
          class="p-3 text-lg text-center text-gray-700 break-words bg-white rounded-b-lg"
        >
          총 평가자 13 | 총 지원자 39
        </div>
      </div>
    </div>
    <!-- # 리스트 -->
    <div class="flex flex-col">
      <div class="flex flex-col justify-center">
        <div class="inline-block w-auto py-2 space-y-4 sm:px-6 lg:px-8">
          <div class="flex flex-wrap justify-between px-5">
            <p class="text-2xl font-medium">계정 리스트 조회</p>
            <div class="flex justify-center space-x-2">
              <button
                type="button"
                class="inline-block rounded bg-blue-600 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg"
              >
                계정 생성
              </button>
            </div>
          </div>
          <div class="overflow-hidden rounded-md shadow-lg">
            <table class="min-w-full">
              <thead class="bg-white border-b">
                <tr>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  ></th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  ></th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  >
                    기업명
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  >
                    계약기간
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  >
                    담당자
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  >
                    ID
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  >
                    연락처
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  >
                    이메일
                  </th>
                </tr>
              </thead>
              <tbody>
                <tr
                  class="transition duration-300 ease-in-out bg-white border-b hover:bg-gray-100"
                >
                  <td
                    class="px-6 py-4 text-sm font-medium text-gray-900 whitespace-nowrap"
                  >
                    1
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 488 512"
                      class="h-7 w-7"
                      style="color: #ea4335"
                    >
                      <!--! Font Awesome Pro 6.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. -->
                      <path
                        fill="currentColor"
                        d="M488 261.8C488 403.3 391.1 504 248 504 110.8 504 0 393.2 0 256S110.8 8 248 8c66.8 0 123 24.5 166.3 64.9l-67.5 64.9C258.5 52.6 94.3 116.6 94.3 256c0 86.5 69.1 156.6 153.7 156.6 98.2 0 135-70.4 140.8-106.9H248v-85.3h236.1c2.3 12.7 3.9 24.9 3.9 41.4z"
                      />
                    </svg>
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    Otto
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    2022.04.06 ~ 2022.04.04
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    김민경
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    minofficial
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    010-7337-3460
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    minofficial13@gmail.com
                  </td>
                </tr>
                <tr
                  class="transition duration-300 ease-in-out bg-white border-b hover:bg-gray-100"
                >
                  <td
                    class="px-6 py-4 text-sm font-medium text-gray-900 whitespace-nowrap"
                  >
                    2
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 488 512"
                      class="h-7 w-7"
                      style="color: #ea4335"
                    >
                      <!--! Font Awesome Pro 6.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. -->
                      <path
                        fill="currentColor"
                        d="M488 261.8C488 403.3 391.1 504 248 504 110.8 504 0 393.2 0 256S110.8 8 248 8c66.8 0 123 24.5 166.3 64.9l-67.5 64.9C258.5 52.6 94.3 116.6 94.3 256c0 86.5 69.1 156.6 153.7 156.6 98.2 0 135-70.4 140.8-106.9H248v-85.3h236.1c2.3 12.7 3.9 24.9 3.9 41.4z"
                      />
                    </svg>
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    Thornton
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    2022.04.06 ~ 2022.04.04
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    김민경
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    minofficial
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    010-7337-3460
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    minofficial13@gmail.com
                  </td>
                </tr>
                <tr
                  class="transition duration-300 ease-in-out bg-white border-b hover:bg-gray-100"
                >
                  <td
                    class="px-6 py-4 text-sm font-medium text-gray-900 whitespace-nowrap"
                  >
                    3
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      viewBox="0 0 488 512"
                      class="h-7 w-7"
                      style="color: #ea4335"
                    >
                      <!--! Font Awesome Pro 6.0.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. -->
                      <path
                        fill="currentColor"
                        d="M488 261.8C488 403.3 391.1 504 248 504 110.8 504 0 393.2 0 256S110.8 8 248 8c66.8 0 123 24.5 166.3 64.9l-67.5 64.9C258.5 52.6 94.3 116.6 94.3 256c0 86.5 69.1 156.6 153.7 156.6 98.2 0 135-70.4 140.8-106.9H248v-85.3h236.1c2.3 12.7 3.9 24.9 3.9 41.4z"
                      />
                    </svg>
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    Wild
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    2022.04.06 ~ 2022.04.04
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    김민경
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    minofficial
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    010-7337-3460
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    minofficial13@gmail.com
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>
