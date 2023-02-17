import axios from 'axios';

const sheets = [
  { id: 1, title: '평가지A', content: '평가문항', score: 20 },
  { id: 2, title: '평가지B', content: '평가문항', score: 20 },
  { id: 3, title: '평가지C', content: '평가문항', score: 20 },
  { id: 4, title: '평가지D', content: '평가문항', score: 20 },
  { id: 4, title: '평가지E', content: '평가문항', score: 20 },
];

export function getSheets() {
  return sheets;
}

// 평가지 생성
export function createSheets(data) {
  return axios.post('https://i8a106.p.ssafy.io/api/evaluation/sheets', data);
}

// 평가지 항목 생성
export function createSheetsItem(id) {
  return axios.post(
    `https://i8a106.p.ssafy.io/api/evaluation/sheets/items/${id}`,
  );
}

// 해당 평가지 조회
export function getSheetsById(id) {
  return axios.get(`https://i8a106.p.ssafy.io/api/evaluation/sheets/${id}`);
}

// 평가지 수정
export function updateSheets(id, data) {
  return axios.put(
    `https://i8a106.p.ssafy.io/api/evaluation/sheets/modifysheet/${id}`,
    data,
  );
}

// 평가지 항목 수정
export function updateSheetsItem(sheetId, sheetitemId, data) {
  return axios.put(
    `https://i8a106.p.ssafy.io/api/evaluation/sheets/modifysheet/${sheetId}/${sheetitemId}`,
    data,
  );
}

// 평가지 삭제
export function deleteSheets(id) {
  return axios.delete(`https://i8a106.p.ssafy.io/api/evaluation/sheets/${id}`);
}

// 평가지 항목 삭제
export function deleteSheetsItem(id) {
  return axios.delete(
    `https://i8a106.p.ssafy.io/api/evaluation/sheets/deleteitem/${id}`,
  );
}
