<template>
  <div>
    <div class="flex justify-center">
      <div class="overflow-hidden rounded-md"></div>
    </div>
    <!-- component -->
    <div class="flex items-center justify-center p-5">
      <div class="mx-auto w-full max-w-[550px]">
        <form action="https://formbold.com/s/FORM_ID" method="POST">
          <div
            v-for="(evaluationSheet, index) in evaluationSheets"
            :key="index"
            class="mb-5"
          >
            <label
              for="name"
              class="mb-3 block text-base font-medium text-[#07074D]"
            >
              {{ evaluationSheet.content }}
              ({{ evaluationSheet.score }}점)
            </label>
            <textarea
              type="text"
              name="issue"
              id="issue"
              rows="5"
              class="resize-none w-full rounded-md border ring-gray-500 bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
            />
            <div class="mb-5 text-end">
              <label
                for="name"
                class="mb-3 inline my-2 mr-3 block text-base font-medium text-[#07074D]"
              >
                평가 점수
              </label>
              <input
                type="text"
                name="name"
                id="name"
                placeholder="점수 입력"
                class="w-1/4 indent-3 rounded-md border ring-gray-500 bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
              />
            </div>
          </div>
          <div>
            <label
              for="subject"
              class="mb-3 block text-base font-medium text-[#07074D]"
            >
              메모
            </label>
            <textarea
              rows="4"
              name="message"
              id="message"
              placeholder="추가 의견을 자유롭게 작성해주십시오."
              class="w-full resize-none rounded-md border ring-gray-500 bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
            ></textarea>
          </div>
          <div class="text-end">
            <button
              class="hover:shadow-form rounded-md bg-indigo-600 py-3 px-8 text-base font-semibold text-white outline-none"
            >
              제출
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const evaluationSheets = ref([]);

const props = defineProps({
  roomid: {
    Type: String,
    required: true,
  },
});

const getEvaluationSheets = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  axios
    .get(`${BASE_URL}/evaluation/interview/1`, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('evaluationSheets.data : ', res.data);
      evaluationSheets.value = res.data;
    });
};

onMounted(() => {
  getEvaluationSheets();
});
</script>

<style lang="scss" scoped></style>
