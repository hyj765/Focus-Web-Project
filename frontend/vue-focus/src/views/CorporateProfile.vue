<template>
  <div class="flex flex-col space-y-10">
    <!-- 
    # 네이버님 안녕하세요
    -->
    <nav class="flex flex-wrap justify-between p-8 text-gray-800">
      <h1 class="font-bold">네이버 님, 안녕하세요</h1>
      <h3 class="font-bold text-gray-500">Profile</h3>
    </nav>
    <div class="flex justify-center space-x-2">
      <div
        class="block w-2/5 max-w-full mx-auto text-sm bg-white rounded-lg shadow-lg pointer-events-auto bg-clip-padding"
      >
        <div
          class="flex items-center justify-between px-3 py-2 bg-white border-b border-gray-200 rounded-t-lg bg-clip-padding"
        >
          <div class="flex flex-row p-4 space-x-5">
            <i class="text-5xl text-blue-700 bx bxs-user-circle"></i>
            <div class="space-y-2 text-lg">
              <p>ID : minofficial</p>
              <p>계약기간 : 2022.02.04 ~ 2022.03.03</p>
            </div>
          </div>
        </div>
        <!--         
        # 총 평가자 총 지원자
        -->
        <div
          class="relative flex p-4 text-lg text-gray-700 break-words bg-white rounded-b-lg"
        >
          <div class="space-y-3">
            <p>기업명 : 네이버</p>
            <p>대표 전화번호 : 010-7337-3460</p>
            <p>대표 이메일 : minofficial@naver.com</p>
            <p>담당자 : 김민경</p>
            <p>업종 : IT기업</p>
            <p>규모 : 대기업</p>
            <p>기업로고 :</p>
            <p>면접방 스킨색 :</p>
          </div>
          <button class="absolute top-0 right-0 p-5">
            <i class="text-2xl bx bx-edit-alt"></i>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>
