b
<template>
  <div>
    <p>{{ name }}</p>
    <p>{{ major }}</p>
    <p>{{ credit }} : {{ totalCredit }}</p>
    <p>{{ email }}</p>
    <p>{{ tel }}</p>
    <p>수험번호: {{ code }}</p>

    <input type="file" accept="image/*" id="applicantPicture" />
    <button @click="uploadApplicantPicture()" type="button">
      지원자 사진 업로드
    </button>
    <input type="file" accept="image/*" id="applicantIntroduce" />
    <button @click="uploadApplicantIntroduce()" type="button">
      사용자 자기소개서 업로드
    </button>
    <button @click="loadApplicantImage()">이미지 가져오기</button>
    <img id="image" src="" />
    <img id="introduce" src="" />
  </div>
</template>

<script setup>
import { watch, ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';
import { faWindows } from '@fortawesome/free-brands-svg-icons';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const activityCount = ref(0);
const birth = ref('');
const code = ref('');
const credit = ref(0);
const converteddegree = ref('');
const email = ref('');
const expireDate = ref('');
const gender = ref('');
const major = ref('');
const name = ref('');
const tel = ref('');
const totalCredit = ref(0);

const getdegree = degree => {
  if (degree.value === 'j') {
    converteddegree.value = '전문학사';
  } else if (degree.value === 'b') {
    converteddegree.value = '학사';
  } else if (degree.value === 'm') {
    converteddegree.value = '석사';
  } else if (degree.value === 'd') {
    converteddegree.value = '박사';
  }
};

const route = useRoute();
const router = useRouter();
const faceimage = new Image();
onMounted(() => {
  loadApplicantInfoDetail();
  console.log(route.params.id);
});
const applicantId = ref(0);
const uploadApplicantPicture = () => {
  const fm = new FormData();
  const user = JSON.parse(localStorage.getItem('user'));
  const image = document.getElementById('applicantPicture');
  fm.append('applicantId', applicantId.value);
  fm.append('file', image.files[0]);
  axios
    .post(`${BASE_URL}/data/upload/applicantface/image`, fm, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
        'Content-Type': 'multipart/form-data',
      },
    })
    .then(data => {
      console.log(data);
    })
    .then(err => {
      console.log(err);
    });
};
const uploadApplicantIntroduce = () => {
  const fm = new FormData();
  const user = JSON.parse(localStorage.getItem('user'));
  const image = document.getElementById('applicantIntroduce');
  console.log(applicantId);
  fm.append('applicantId', applicantId.value);
  fm.append('file', image.files[0]);
  axios
    .post(`${BASE_URL}/data/upload/introduce/image`, fm, {
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
        'Content-Type': 'multipart/form-data',
      },
    })
    .then(data => {
      console.log(data);
    })
    .then(err => {
      console.log(err);
    });
};

const loadApplicantInfoDetail = () => {
  axios
    .get(`${BASE_URL}/companyusers/applicants/${route.params.id}`)
    .then(data => {
      // applicantInfo.value = data.data;
      console.log(data.data);
      applicantId.value = data.data.id;
      code.value = data.data.code;
      activityCount.value = data.data.activityCount;
      birth.value = data.data.birth;
      credit.value = data.data.credit;
      email.value = data.data.email;
      expireDate.value = data.data.expireDate;
      gender.value = data.data.gender;
      major.value = data.data.major;
      name.value = data.data.name;
      tel.value = data.data.tel;
      totalCredit.value = data.data.totalCredit;
      getdegree(data.data.degree);
    })
    .then(err => {
      console.log(err);
    });
};

const loadApplicantImage = () => {
  axios
    .get(`${BASE_URL}/data/introduce/${applicantId.value}`)
    .then(data => {
      var image = new Image();
      image.src = `data:image/png;base64,${data.data}`;
      document.getElementById('image').src = image.src;
    })
    .catch(err => {
      console.log(err);
    });
};
const loadIntroduceImage = () => {
  axios
    .get(`${BASE_URL}/data/applicant/image/${applicantId.value}`)
    .then(data => {
      var image = new Image();
      image.src = `data:image/png;base64,${data.data}`;
      document.getElementById('introduce').src = image.src;
    });
};
</script>

<style lang="scss" scoped></style>
