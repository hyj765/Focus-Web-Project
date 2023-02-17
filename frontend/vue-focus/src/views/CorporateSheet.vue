<template>
  <div>
    <!-- 
    # 네이버님 안녕하세요
    -->
    <nav class="flex flex-wrap justify-between p-8 text-gray-800">
      <h1 class="font-bold">네이버 님, 안녕하세요</h1>
      <h3 class="font-bold text-gray-500">Pass</h3>
    </nav>
    <!-- # 탭 -->
    <div class="max-w-2xl mx-auto">
      <div class="border-b border-gray-200 mb-4">
        <ul
          class="flex flex-wrap -mb-px"
          id="myTab"
          data-tabs-toggle="#myTabContent"
          role="tablist"
        >
          <li class="mr-2" role="presentation">
            <button
              class="inline-block text-gray-500 hover:text-gray-600 hover:border-gray-300 rounded-t-lg py-4 px-4 text-sm font-medium text-center border-transparent border-b-2"
              id="profile-tab"
              data-tabs-target="#profile"
              type="button"
              role="tab"
              aria-controls="profile"
              aria-selected="false"
            >
              평가지 1
            </button>
          </li>
          <li class="mr-2" role="presentation">
            <button
              class="inline-block text-gray-500 hover:text-gray-600 hover:border-gray-300 rounded-t-lg py-4 px-4 text-sm font-medium text-center border-transparent border-b-2 active"
              id="dashboard-tab"
              data-tabs-target="#dashboard"
              type="button"
              role="tab"
              aria-controls="dashboard"
              aria-selected="true"
            >
              Dashboard
            </button>
          </li>
          <li class="mr-2" role="presentation">
            <button
              class="inline-block text-gray-500 hover:text-gray-600 hover:border-gray-300 rounded-t-lg py-4 px-4 text-sm font-medium text-center border-transparent border-b-2"
              id="settings-tab"
              data-tabs-target="#settings"
              type="button"
              role="tab"
              aria-controls="settings"
              aria-selected="false"
            >
              Settings
            </button>
          </li>
          <li role="presentation">
            <button
              class="inline-block text-gray-500 hover:text-gray-600 hover:border-gray-300 rounded-t-lg py-4 px-4 text-sm font-medium text-center border-transparent border-b-2"
              id="contacts-tab"
              data-tabs-target="#contacts"
              type="button"
              role="tab"
              aria-controls="contacts"
              aria-selected="false"
            >
              Contacts
            </button>
          </li>
        </ul>
      </div>
      <div id="myTabContent">
        <div
          class="bg-gray-50 p-4 rounded-lg hidden"
          id="profile"
          role="tabpanel"
          aria-labelledby="profile-tab"
        >
          <p class="text-gray-500 text-sm">
            This is some placeholder content the
            <strong class="font-medium text-gray-800"
              >Profile tab's associated content</strong
            >. Clicking another tab will toggle the visibility of this one for
            the next. The tab JavaScript swaps classes to control the content
            visibility and styling.
          </p>
        </div>
        <div
          class="bg-gray-50 p-4 rounded-lg"
          id="dashboard"
          role="tabpanel"
          aria-labelledby="dashboard-tab"
        >
          <p class="text-gray-500 text-sm">
            This is some placeholder content the
            <strong class="font-medium text-gray-800"
              >Dashboard tab's associated content</strong
            >. Clicking another tab will toggle the visibility of this one for
            the next. The tab JavaScript swaps classes to control the content
            visibility and styling.
          </p>
        </div>
        <div
          class="bg-gray-50 p-4 rounded-lg hidden"
          id="settings"
          role="tabpanel"
          aria-labelledby="settings-tab"
        >
          <p class="text-gray-500 text-sm">
            This is some placeholder content the
            <strong class="font-medium text-gray-800"
              >Settings tab's associated content</strong
            >. Clicking another tab will toggle the visibility of this one for
            the next. The tab JavaScript swaps classes to control the content
            visibility and styling.
          </p>
        </div>
        <div
          class="bg-gray-50 p-4 rounded-lg hidden"
          id="contacts"
          role="tabpanel"
          aria-labelledby="contacts-tab"
        >
          <p class="text-gray-500 text-sm">
            This is some placeholder content the
            <strong class="font-medium text-gray-800"
              >Contacts tab's associated content</strong
            >. Clicking another tab will toggle the visibility of this one for
            the next. The tab JavaScript swaps classes to control the content
            visibility and styling.
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>
