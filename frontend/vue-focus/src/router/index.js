import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '@/views/LoginView';
// 서비스관리자
import ServiceHomeView from '@/views/Service/ServiceHomeView';
import ServiceFAQ from '@/views/Service/ServiceFAQ';
import ServiceNotice from '@/views/Service/ServiceNotice';
import ServiceNoticeCreate from '@/views/Service/ServiceNoticeCreate';
import ServiceAccount from '@/views/Service/ServiceAccount';
import ServiceAccountCreate from '@/views/Service/ServiceAccountCreate';
// 기업관리자
import CorporateHomeView from '@/views/CorporateHomeView';
// 평가자
import EvaluatorHomeView from '@/views/EvaluatorHomeView';
// 지원자
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
  //서비스관리자
  {
    path: '/service',
    name: 'Service',
    component: ServiceHomeView,
    beforeEnter: [checkLogin],
  },
  {
    path: '/service/faq',
    name: 'ServiceFAQ',
    component: ServiceFAQ,
    beforeEnter: [checkLogin],
  },
  {
    path: '/service/notice',
    name: 'ServiceNotice',
    component: ServiceNotice,
    beforeEnter: [checkLogin],
  },
  {
    path: '/service/notice/create',
    name: 'ServiceNoticeCreate',
    component: ServiceNoticeCreate,
    beforeEnter: [checkLogin],
  },
  {
    path: '/service/account',
    name: 'ServiceAccount',
    component: ServiceAccount,
    beforeEnter: [checkLogin],
  },
  {
    path: '/service/account/create',
    name: 'ServiceAccountCreate',
    component: ServiceAccountCreate,
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
