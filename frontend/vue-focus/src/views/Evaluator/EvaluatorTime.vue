<template>
  <div class="flex flex-col space-y-10">
    <!-- 
    # 네이버님 안녕하세요
    -->
    <nav class="flex flex-wrap justify-between px-8 py-4 text-gray-800">
      <h1 class="font-bold">네이버 님, 안녕하세요</h1>
      <h3 class="font-bold text-gray-500">Time</h3>
    </nav>
    <div class="flex">
      <div class="px-4 py-2 ml-10 text-white bg-indigo-900 rounded-lg">
        12:30 ~ 12:30 | 면접대기실
      </div>
    </div>
    <div class="flex flex-col items-center justify-center">
      <div
        class="flex flex-row w-auto h-auto pb-1 pl-2 pr-4 bg-white rounded-lg shadow-lg"
      >
        <div class="flex flex-col items-center w-auto h-auto p-4 text-gray-900">
          <p
            class="flex items-center justify-center px-4 py-1 text-white bg-indigo-900 rounded-xl"
          >
            13:40 ~ 14:40 (50분)
          </p>
          <p class="flex items-center justify-center p-2 text-2xl font-bold">
            두나무 증권 개발자
          </p>
          <p>평가자 3 | 지원자 3</p>
        </div>
        <ul class="flex flex-col w-auto pt-4 space-y-4">
          <!-- 
              # 리스트 component
              -->
          <li class="flex flex-row items-center">
            <div
              class="flex items-center justify-center w-5 h-5 bg-indigo-500 rounded-full shadow-lg ring-1 ring-slate-900/5"
            >
              <i class="text-white bx bx-right-arrow-alt"></i>
            </div>
            <div class="px-2">
              <p>2022.02.04 ~ 2022.02.05</p>
            </div>
          </li>
          <li class="flex flex-row items-center">
            <div
              class="flex items-center justify-center w-5 h-5 bg-indigo-500 rounded-full shadow-lg animate-bounce ring-1 ring-slate-900/5"
            >
              <i class="text-white bx bx-right-arrow-alt"></i>
            </div>
            <div class="px-2">
              <p>2022.02.04 ~ 2022.02.05</p>
            </div>
          </li>
          <!-- <li class="flex flex-row items-center">
            <div
              class="flex items-center justify-center w-5 h-5 bg-indigo-500 rounded-full shadow-lg ring-1 ring-slate-900/5"
            >
              <i class="text-white bx bx-right-arrow-alt"></i>
            </div>
            <div class="px-2">
              <p>2022.02.04 ~ 2022.02.05</p>
            </div>
          </li> -->
          <!-- 
              #리스트 component end
              -->
        </ul>
        <div class="flex items-center justify-center p-2">
          <button
            type="button"
            class="place-content-center inline-block rounded bg-indigo-500 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
          >
            입장
          </button>
        </div>
      </div>
    </div>
    <!-- 나중에 데이터 받는 처리하면 고칠게 -->
    <div>
      <div class="max-w-2xl px-4 mx-auto sm:py-10sm:px-6 lg:max-w-7xl lg:px-8">
        <p class="pb-5 text-2xl font-medium">평가 예정 지원자</p>

        <div
          class="grid grid-cols-1 gap-y-10 gap-x-6 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 xl:gap-x-8"
        >
          <a
            v-for="product in products"
            :key="product.id"
            :href="product.href"
            class="group"
          >
            <div class="w-full overflow-hidden bg-gray-200 rounded-lg">
              <img
                :src="product.imageSrc"
                :alt="product.imageAlt"
                class="object-cover object-center w-full h-full group-hover:opacity-75"
              />
            </div>
            <p class="mt-1 text-lg font-medium text-gray-900">
              {{ product.price }}
            </p>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';
const scheduledList = ref(null);

const products = [
  {
    id: 1,
    name: 'Earthen Bottle',
    href: '#',
    price: '$48',
    imageSrc:
      'https://tailwindui.com/img/ecommerce-images/category-page-04-image-card-01.jpg',
    imageAlt:
      'Tall slender porcelain bottle with natural clay textured body and cork stopper.',
  },
  {
    id: 2,
    name: 'Nomad Tumbler',
    href: '#',
    price: '$35',
    imageSrc:
      'https://tailwindui.com/img/ecommerce-images/category-page-04-image-card-02.jpg',
    imageAlt:
      'Olive drab green insulated bottle with flared screw lid and flat top.',
  },
  {
    id: 3,
    name: 'Focus Paper Refill',
    href: '#',
    price: '$89',
    imageSrc:
      'https://tailwindui.com/img/ecommerce-images/category-page-04-image-card-03.jpg',
    imageAlt:
      'Person using a pen to cross a task off a productivity paper card.',
  },
  {
    id: 4,
    name: 'Machined Mechanical Pencil',
    href: '#',
    price: '$35',
    imageSrc:
      'https://tailwindui.com/img/ecommerce-images/category-page-04-image-card-04.jpg',
    imageAlt:
      'Hand holding black machined steel mechanical pencil with brass tip and top.',
  },
  // More products...
];

const getScheduleList = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/evaluators/list`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('res.data: ', res.data);
      scheduledList.value = res.data['2023-02-17'];
      console.log(scheduledList.value);
    });
};

onMounted(() => {
  getScheduleList();
});
</script>

<style lang="scss" scoped></style>
