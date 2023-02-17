<template>
  <div>
    <div class="flex justify-center">
      <div class="overflow-hidden rounded-md"></div>
    </div>
    <!-- component -->
    <div class="flex items-center justify-center p-5">
      <div class="mx-auto w-full max-w-[550px]">
        <form action="https://formbold.com/s/FORM_ID" method="POST">
          <div class="mb-5">
            <label
              for="name"
              class="mb-3 block text-base font-medium text-[#07074D]"
            >
              [직무적합성] 지원한 직무 관련 본인이 갖고 있는
              전문지식/경험(심화전공, 프로젝트, 논문, 공모전 등)을 작성하고,
              이를 바탕으로 본인이 지원 직무에 적합한 사유를 구체적으로 서술해
              주시기 바랍니다. (20점)
            </label>
            <textarea
              type="text"
              name="issue"
              id="issue"
              rows="5"
              placeholder="근거 사항"
              class="resize-none w-full rounded-md border ring-gray-500 bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
            />
            <div class="mb-5 text-end">
              <label
                for="name"
                class="mb-3 inline my-2 mr-3 block text-base font-medium text-[#07074D]"
              >
                점수
              </label>
              <input
                type="text"
                name="name"
                id="name"
                placeholder="/ 20점"
                class="w-1/4 indent-9 rounded-md border ring-gray-500 bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
              />
            </div>
          </div>
          <div class="mb-5">
            <label
              for="subject"
              class="mb-3 block text-base font-medium text-[#07074D]"
            >
              [사회이슈] 최근 사회이슈 중 중요하다고 생각되는 한가지를 선택하고
              이에 관한 자신의 견해를 기술해 주시기 바랍니다. (20점)
            </label>
            <textarea
              type="text"
              name="issue"
              id="issue"
              rows="5"
              placeholder="근거 사항"
              class="resize-none w-full rounded-md border ring-gray-500 bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
            />
            <div class="mb-5 text-end">
              <label
                for="name"
                class="mb-3 my-2 mr-3 inline block text-base font-medium text-[#07074D]"
              >
                점수
              </label>
              <input
                type="text"
                name="name"
                id="name"
                placeholder="/ 20점"
                class="w-1/4 indent-9 rounded-md border ring-gray-500 bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
              />
            </div>
          </div>
          <div class="mb-5">
            <label
              for="subject"
              class="mb-3 block text-base font-medium text-[#07074D]"
            >
              [지원동기] 삼성물산을 지원한 이유와 입사 후 회사에서 이루고 싶은
              꿈을 기술하십시오. (20점)
            </label>
            <textarea
              type="text"
              name="issue"
              id="issue"
              rows="5"
              placeholder="근거 사항"
              class="resize-none w-full rounded-md border ring-gray-500 bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
            />
            <div class="mb-5 text-end">
              <label
                for="name"
                class="mb-3 my-2 mr-3 inline block text-base font-medium text-[#07074D]"
              >
                점수
              </label>
              <input
                type="text"
                name="name"
                id="name"
                placeholder="/ 20점"
                class="w-1/4 indent-9 rounded-md border ring-gray-500 bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
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

<script setup></script>

<style lang="scss" scoped></style>
a
