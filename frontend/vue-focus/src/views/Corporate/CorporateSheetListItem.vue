<template>
  <div class="max-w-2xl mx-auto">
    <div class="mb-4 border-b border-gray-200">
      <ul
        class="flex flex-wrap -mb-px"
        id="myTab"
        data-tabs-toggle="#myTabContent"
        role="tablist"
      >
        <li class="mr-2" role="presentation">
          <button
            class="inline-block px-4 py-4 text-sm font-medium text-center text-gray-500 border-b-2 border-transparent rounded-t-lg hover:text-gray-600 hover:border-gray-300"
            id="profile-tab"
            data-tabs-target="#profile"
            type="button"
            role="tab"
            aria-controls="profile"
            aria-selected="false"
          >
            {{ title }}
          </button>
          <!-- <p>{{ content }}</p> -->
          <!-- <p>{{ score }}</p> -->
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
defineProps({
  title: {
    type: String,
    required: true,
  },
  // content: {
  //   type: String,
  //   required: true,
  // },
  // score: {
  //   type: Number,
  //   required: true,
  // },
  createdAt: {
    type: [String, Date, Number],
  },
});
</script>

<style lang="scss" scoped></style>
