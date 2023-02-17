<template>
  <div>
    <div class="flex justify-center">
      <div class="overflow-hidden rounded-md"></div>
    </div>
    <!-- component -->
    <div class="flex items-center justify-center p-5">
      <div class="mx-auto w-full max-w-[550px]">
        <form v-on:submit.prevent="submitSheet" method="POST">
          <!-- ---------------------------------- 문항 --------------------------------- -->
          <div
            v-for="(evaluationSheet, index) in evaluationSheets"
            :key="index"
            class="mb-5"
          >
            <label
              for="name"
              class="mb-3 block text-base font-medium text-[#07074D]"
            >
              {{ evaluationSheet.id }}
              {{ evaluationSheet.content }}
              ({{ evaluationSheet.score }}점)
            </label>
            <textarea
              type="text"
              name="record"
              :id="'record' + evaluationSheet.id"
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
                name="score"
                :id="'score' + evaluationSheet.id"
                placeholder="점수 입력"
                class="w-1/4 indent-3 rounded-md border ring-gray-500 bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
              />
            </div>
          </div>
          <!-- ---------------------------------- 문항 ------------------------------- -->
          <div>
            <label
              for="subject"
              class="mb-3 block text-base font-medium text-[#07074D]"
            >
              메모
            </label>
            <textarea
              rows="4"
              name="memo"
              id="memo"
              placeholder="추가 의견을 자유롭게 작성해주십시오."
              class="w-full resize-none rounded-md border ring-gray-500 bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
            ></textarea>
          </div>
          <div class="text-end">
            <button
              @click="sendEvaluationRecords"
              type="submit"
              class="hover:shadow-form rounded-md bg-indigo-600 py-3 px-8 text-base font-semibold text-white outline-none"
            >
              제출
            </button>
          </div>
        </form>
        <button @click="extractEvaluation()">값보기</button>
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
  interviewRoomId: {
    Type: String,
    required: true,
  },
  applicantId: {
    Type: String,
    required: true,
  },
});
// const idlist = ref([1, 2, 3, 4, 5]); // 진우형이 추출가능
const idlist = ref([]);

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
      idlist.value = evaluationSheets.value.map(
        evaluationSheet => evaluationSheet.id,
      );
      console.log('idlist: ', idlist.value);
    });
};
const evaluationcontent = ref([]);
// 1 ==> iteminfolist push
const content = ref('');
// const evaluationItemId = ???
const score = ref(0);

const extractEvaluation = () => {
  idlist.value.forEach(element => {
    let content = document.getElementById('record1').value;
    let score = document.getElementById('score1').value;
    console.log('content : ', content);
    console.log('score : ', score);
    let frame = {
      content: content,
      score: score,
      id: element,
    };
    evaluationcontent.value.push(frame);
    console.log('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>frame : ', frame);
  });
};
const evaluationItemInfoList = ref([]);
const memo = ref('');

// 2
const getInfo = () => {
  const info = {
    applicantId: props.applicantId,
    evaluationItemInfoList: evaluationItemInfoList.value,
    interviewRoomId: props.interviewRoomId,
    memo: memo.value,
  };
  return info;
};

// 3
// const sendEvaluation = () => {
//   const evaluationInfo = getInfo();
//   const evaluationInfoJson = JSON.stringify(evaluationInfo)
//   const user = JSON.parse(localStorage.getItem('user'))
//   axios.post('http://127.0.0.1:8082/api/interview/evaluation', evaluationInfoJson, headers: {
//     Authorization: `Bearer ${user.accessToken}`
//   }).then((res)=>{
//     console.log(res)
//   }).catch((err)=>{
//     console.log(err)
//   })
// };

const sendEvaluationRecords = () => {
  const fd = new FormData();
  const user = JSON.parse(localStorage.getItem('user'));
  const memo = document.getElementById('memo');
  fd.append('applicantId', props.applicantId); // 1992
  fd.append('evaluationItemInfoList', idlist);
  fd.append('interviewRoomId', props.interviewRoomId); //1
  fd.append('memo', memo); // 잘함여
  axios.post(`${BASE_URL}/interview/evaluation`, {
    headers: {
      Authorization: `Bearer ${user.accessToken}`,
      'Content-Type': 'multipart/form-data',
    },
  });
};
const loggingvalue = () => {
  console.log(document.getElementById('content1').value);
};
onMounted(() => {
  getEvaluationSheets();
});
</script>

<style lang="scss" scoped></style>
