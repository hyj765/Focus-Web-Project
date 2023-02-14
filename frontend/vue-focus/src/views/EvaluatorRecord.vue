<template>
  <div class="flex flex-col space-y-10">
    <!-- 
    # 네이버님 안녕하세요
    -->
    <nav class="flex flex-wrap justify-between px-8 py-4 text-gray-800">
      <h1 class="font-bold">네이버 님, 안녕하세요</h1>
      <h3 class="font-bold text-gray-500">Records</h3>
    </nav>
    <!-- # 리스트 하나 -->
    <div class="rounded-lg shadow-lg bg-white/50">
      <button @click="show = !show" class="px-10 py-3">
        <div class="space-x-10 text-gray-700 flexitems-center">
          <span class="text-xl font-bold text-black">두나무 개발자 FE</span>
          <span class="text-lg font-regular">02/25</span>
          <span class="text-lg font-regular">13:40 ~ 15:50 (50분)</span>
          <span class="text-lg font-regular">3차 진행중</span>
          <i class="text-2xl" :class="iconClass()"></i>
        </div>
      </button>
      <div v-if="show" class="py-5">
        <!-- 나중에 데이터 받는 처리하면 고칠게 -->
        <div>
          <div
            class="max-w-2xl px-4 mx-auto sm:py-10sm:px-6 lg:max-w-7xl lg:px-8"
          >
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
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
const show = ref(false);
const iconClass = () => {
  if (show.value === true) {
    return 'bx bx-chevron-up';
  } else {
    return 'bx bx-chevron-down';
  }
};
onMounted(() => {
  console.log(show.value);
  console.log(iconClass());
});
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
</script>

<style lang="scss" scoped></style>
