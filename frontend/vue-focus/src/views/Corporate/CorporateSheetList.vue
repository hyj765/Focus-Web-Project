<template>
  <div>
    <div v-for="sheet in sheets" :key="sheet.id">
      <CorporateSheetListItem
        @click="goSheetDetail(sheet.id)"
        :title="sheet.title"
      ></CorporateSheetListItem>
      <RouterView></RouterView>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { getSheets } from '@/api/sheets';
import CorporateSheetListItem from './CorporateSheetListItem.vue';
const router = useRouter();
const sheets = ref([]);

const fetchSheets = () => {
  sheets.value = getSheets();
};
fetchSheets();

const goSheetDetail = id => {
  // router.push(`/evaluation/sheets/${id}`);
  router.push({
    name: 'SheetDetail',
    params: {
      id,
    },
  });
};
</script>

<style lang="scss" scoped></style>
