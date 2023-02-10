<template>
  <div>
    <!-- 
    # 네이버님 안녕하세요
    -->
    <nav class="flex flex-wrap justify-between p-8 text-gray-800">
      <h1 class="font-bold">네이버 님, 안녕하세요</h1>
      <h3 class="font-bold text-gray-500">Evaluator</h3>
    </nav>
    <p class="px-10 text-2xl font-medium">평가자 리스트 조회</p>
    <div class="flex flex-col py-5">
      <div class="flex flex-col justify-center">
        <div class="inline-block w-auto space-y-4 sm:px-6 lg:px-8">
          <div class="flex flex-wrap justify-between px-5">
            <div class="flex justify-center space-x-2">
              <!-- # 할당별 필터 -->
              <Menu as="div" class="relative inline-block text-left">
                <div>
                  <MenuButton
                    class="inline-flex justify-center w-full px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md shadow-sm hover:bg-gray-50"
                  >
                    {{ userIdFilterLabel }}
                    <ChevronDownIcon
                      class="w-5 h-5 ml-2 -mr-1"
                      aria-hidden="true"
                    />
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
                        <p
                          @click="deactivateFilters()"
                          :class="[
                            active
                              ? 'bg-gray-100 text-gray-900'
                              : 'text-gray-700',
                            'block px-4 py-2 text-sm',
                          ]"
                        >
                          전체 (Show All)
                        </p>
                      </MenuItem>
                      <MenuItem v-slot="{ active }">
                        <p
                          @click="filterEvaluatorByUserId(false)"
                          :class="[
                            active
                              ? 'bg-gray-100 text-gray-900'
                              : 'text-gray-700',
                            'block px-4 py-2 text-sm',
                          ]"
                        >
                          ID 할당 계정
                        </p>
                      </MenuItem>
                      <MenuItem v-slot="{ active }">
                        <p
                          @click="filterEvaluatorByUserId(true)"
                          :class="[
                            active
                              ? 'bg-gray-100 text-gray-900'
                              : 'text-gray-700',
                            'block px-4 py-2 text-sm',
                          ]"
                        >
                          ID 미할당 계정
                        </p>
                      </MenuItem>
                    </div>
                  </MenuItems>
                </transition>
              </Menu>

              <!-- # 부서별 필터 -->
              <Menu as="div" class="relative inline-block text-left">
                <div>
                  <MenuButton
                    class="inline-flex justify-center w-full px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md shadow-sm hover:bg-gray-50"
                  >
                    {{ departmentFilterLabel }}
                    <ChevronDownIcon
                      class="w-5 h-5 ml-2 -mr-1"
                      aria-hidden="true"
                    />
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
                        <p
                          @click="deactivateFilters()"
                          :class="[
                            active
                              ? 'bg-gray-100 text-gray-900'
                              : 'text-gray-700',
                            'block px-4 py-2 text-sm',
                          ]"
                        >
                          전체 (Show All)
                        </p>
                      </MenuItem>
                      <MenuItem
                        v-slot="{ active }"
                        v-for="department in departments"
                        :key="department.id"
                      >
                        <p
                          @click="filterEvaluatorsByDepartment(department)"
                          :class="[
                            active
                              ? 'bg-gray-100 text-gray-900'
                              : 'text-gray-700',
                            'block px-4 py-2 text-sm',
                          ]"
                        >
                          {{ department }}
                        </p>
                      </MenuItem>
                    </div>
                  </MenuItems>
                </transition>
              </Menu>
              <!-- # 이름 검색 -->
              <div class="flex justify-center">
                <div
                  class="relative flex flex-row items-stretch w-auto px-4 rounded input-group"
                >
                  <input
                    v-model="searchName"
                    @keyup.enter="filterEvaluatorsByName(searchName)"
                    type="search"
                    class="relative flex-auto block w-full min-w-0 px-3 m-0 text-base font-normal text-gray-700 transition ease-in-out bg-white border border-gray-300 border-solid rounded form-control bg-clip-padding focus:text-gray-700 focus:bg-white focus:border-blue-600 focus:outline-none"
                    placeholder="이름 검색"
                    aria-label="Search"
                    aria-describedby="button-addon2"
                  />
                  <span
                    @click="filterEvaluatorsByName(searchName)"
                    class="input-group-text flex items-center px-3 py-1.5 text-base font-normal text-gray-700 text-center whitespace-nowrap rounded"
                    id="basic-addon2"
                  >
                    <i class="w-4 text-lg bx bx-search"></i>
                  </span>
                </div>
              </div>
            </div>
            <div class="flex justify-center space-x-2">
              <!-- <button
                type="button"
                class="inline-block h-10 px-6 font-medium leading-tight text-gray-700 uppercase transition duration-150 ease-in-out bg-white rounded shadow-md text-md hover:bg-gray-100 hover:shadow-lg focus:bg-gray-100 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-gray-200 active:shadow-lg"
              >
                리스트 편집
              </button> -->
              <button
                type="button"
                class="inline-block h-10 px-6 font-medium leading-tight text-white uppercase transition duration-150 ease-in-out bg-blue-700 rounded shadow-md text-md hover:bg-blue-800 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg"
                @click="$emit('update:comp')"
              >
                평가자 생성
              </button>
            </div>
          </div>
          <div class="overflow-hidden rounded-md shadow-lg">
            <table class="min-w-full">
              <thead class="bg-white border-b">
                <tr>
                  <!-- <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  >
                    사진
                  </th> -->
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  >
                    이름
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  >
                    사번
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  >
                    부서
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  >
                    직급
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  >
                    전화번호
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  >
                    이메일
                  </th>
                  <th
                    scope="col"
                    class="px-6 py-4 text-sm font-medium text-left text-gray-900"
                  >
                    ID
                  </th>
                </tr>
              </thead>
              <tbody
                v-for="(evaluator, index) in currentEvaluators"
                :key="index"
              >
                <tr
                  class="transition duration-300 ease-in-out bg-white border-b hover:bg-gray-100"
                >
                  <!-- <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    image url: {{ evaluator.image }}
                  </td> -->
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    {{ evaluator.name }}
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    {{ evaluator.code }}
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    {{ evaluator.department }}
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    {{ evaluator.position }}
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    {{ evaluator.tel }}
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    {{ evaluator.email }}
                  </td>
                  <td
                    class="px-6 py-4 text-sm font-light text-gray-900 whitespace-nowrap"
                  >
                    <div>
                      <div class="flex justify-center space-x-2">
                        <button
                          type="button"
                          class="inline-block rounded bg-indigo-900 px-6 py-2.5 text-md font-medium uppercase leading-tight text-white shadow-md transition duration-150 ease-in-out hover:bg-indigo-700 hover:shadow-lg focus:bg-indigo-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-indigo-800 active:shadow-lg"
                        >
                          계정 할당
                        </button>
                      </div>
                    </div>
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

<script setup>
import { Menu, MenuButton, MenuItem, MenuItems } from '@headlessui/vue';
import { ChevronDownIcon } from '@heroicons/vue/20/solid';
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useStore } from 'vuex';

defineEmits(['update:comp']);
const BASE_URL = 'https://i8a106.p.ssafy.io/api';

const userIdFilterLabel = ref('계정 할당 여부');
const filterEvaluatorByUserId = needId => {
  currentEvaluators.value = evaluators.value;
  if (departmentFilterLabel.value !== '부서별') {
    currentEvaluators.value = currentEvaluators.value.filter(
      evaluator => evaluator.department === departmentFilterLabel.value,
    );
  }
  if (needId === true) {
    currentEvaluators.value = currentEvaluators.value.filter(
      evaluator => evaluator.userId === null,
    );
    userIdFilterLabel.value = 'ID 미할당 인원';
  } else {
    currentEvaluators.value = currentEvaluators.value.filter(
      evaluator => evaluator.userId !== null,
    );
    userIdFilterLabel.value = 'ID 할당 인원';
  }
  console.log('filterByUserId currentEvaluators: ', currentEvaluators.value);
};

const evaluators = ref(null);
const currentEvaluators = ref(null);
const departments = ref([]);
const departmentFilterLabel = ref('부서별');
const filterEvaluatorsByDepartment = department => {
  departmentFilterLabel.value = department;
  currentEvaluators.value = evaluators.value;
  currentEvaluators.value = currentEvaluators.value.filter(
    evaluator => evaluator.department === department,
  );
  console.log(
    'filterByDepartment currentEvaluators: ',
    currentEvaluators.value,
  );
  userIdFilterLabel.value = '계정 할당 여부';
  searchName.value = null;
};
const deactivateFilters = () => {
  userIdFilterLabel.value = '계정 할당 여부';
  departmentFilterLabel.value = '부서별';
  currentEvaluators.value = evaluators.value;
  searchName.value = null;
};

const searchName = ref('');
const filterEvaluatorsByName = name => {
  if (
    departmentFilterLabel.value === '부서별' &&
    userIdFilterLabel.value === '계정 할당 여부'
  ) {
    currentEvaluators.value = evaluators.value;
  } else if (userIdFilterLabel.value === '계정 할당 여부') {
    currentEvaluators.value = evaluators.value.filter(
      evaluator => evaluator.department === departmentFilterLabel.value,
    );
  } else if (
    departmentFilterLabel.value === '부서별' &&
    userIdFilterLabel.value === 'ID 할당 인원'
  ) {
    currentEvaluators.value = evaluators.value.filter(
      evaluator => evaluator.userId !== null,
    );
  } else if (
    departmentFilterLabel.value === '부서별' &&
    userIdFilterLabel.value === 'ID 미할당 인원'
  ) {
    currentEvaluators.value = evaluators.value.filter(
      evaluator => evaluator.userId === null,
    );
  }
  console.log('filterByName currentEvaluators: ', currentEvaluators.value);
  currentEvaluators.value = currentEvaluators.value.filter(
    evaluator => evaluator.name === name,
  );
};

// 총 페이지 값 (페이징 구현할 때 필요할 것으로 예상)
let totalPageCount = 0;
const pageSize = 4;
// 페이징 컴포넌트에 따라 달라지는 반응형으로 교체
const pageNumber = 1;

const getEvaluatorsInfo = () => {
  const user = JSON.parse(localStorage.getItem('user'));
  let evaluatorCount = 0;
  let remainder = 0;
  axios
    .get(`${BASE_URL}/companyusers/evaluators/list`, {
      // params: {
      //   size: pageSize,
      //   page: pageNumber,
      // },
      headers: {
        Authorization: `Bearer ${user.accessToken}`,
      },
    })
    .then(res => {
      console.log('res.data: ', res.data);
      // 페이징 기능 구현 시 필요한 totalPageCount 값 계산
      evaluatorCount = res.data.totalElements;
      remainder = evaluatorCount % pageSize;
      if (remainder === 0) {
        totalPageCount = parseInt(evaluatorCount / pageSize);
      } else {
        totalPageCount = parseInt(evaluatorCount / pageSize) + 1;
      }
      evaluators.value = res.data.content;
      currentEvaluators.value = res.data.content;
      // console.log('evaluators: ', evaluators.value);
      // console.log('currentEvaluators: ', currentEvaluators.value);

      // Departments Filter
      const tempDepartments = evaluators.value.map(
        evaluator => evaluator.department,
      );
      for (const department of tempDepartments) {
        if (!departments.value.includes(department)) {
          departments.value.push(department);
        }
      }
      // console.log('departments: ', departments.value);
    });
};

const store = useStore();
const saveDepartments = () => {
  store.dispatch('saveDepartments', departments);
};

onMounted(() => {
  getEvaluatorsInfo();
  saveDepartments();
});
</script>

<style lang="scss" scoped></style>
