import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '@/views/LoginView';
import ServiceHomeView from '@/views/ServiceHomeView';
import CorporateHomeView from '@/views/CorporateHomeView';
import EvaluatorHomeView from '@/views/EvaluatorHomeView';
import ApplicantHomeView from '@/views/ApplicantHomeView';
import InterviewRoom from '@/views/InterviewDisplay/InterviewRoom';
import CorporateSheetDetail from '@/views/CorporateSheetDetail';
import CorporateSheetCreate from '@/views/CorporateSheetCreate';
import CorporateSheetEdit from '@/views/CorporateSheetEdit';
import BoardDetail from '@/views/BoardDetail/BoardDetail';

const routes = [
  {
    path: '/',
    name: 'Login',
    component: LoginView,
  },
  {
    path: '/service',
    name: 'Service',
    component: ServiceHomeView,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate',
    name: 'Corporate',
    component: CorporateHomeView,
    beforeEnter: [checkLogin],
  },
  {
    path: '/evaluate',
    name: 'Evaluator',
    component: EvaluatorHomeView,
    beforeEnter: [checkLogin],
  },
  {
    path: '/apply',
    name: 'Applicant',
    component: ApplicantHomeView,
    beforeEnter: [checkLogin],
  },
  {
    path: '/interviewroom',
    name: 'InterviewRoom',
    component: InterviewRoom,
  },
  {
    path: '/evaluation/sheets/:id',
    name: 'SheetDetail',
    component: CorporateSheetDetail,
  },
  {
    path: '/evaluation/sheets',
    name: 'SheetCreate',
    component: CorporateSheetCreate,
  },
  {
    path: '/evaluation/sheets/modifysheet/:id',
    name: 'SheetEdit',
    component: CorporateSheetEdit,
  },
  {
    path: '/service/notice/:id',
    name: 'BoardDetail',
    component: BoardDetail,
  },
];

const router = createRouter({
  history: createWebHistory('/'),
  routes: routes,
});

function checkLogin() {
  const loggedIn = localStorage.getItem('user');
  if (!loggedIn) {
    router.push({ name: 'Login' });
  }
}

export default router;
