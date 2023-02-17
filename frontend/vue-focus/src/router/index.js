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
import CorporateHomeView from '@/views/Corporate/CorporateHomeView';
import CorporateManageEvaluator from '@/views/Corporate/CorporateManageEvaluator';
import CorporateManageEvaluatorCreate from '@/views/Corporate/CorporateManageEvaluatorCreate';
import CorporateManageProcess from '@/views/Corporate/CorporateManageProcess';
import CorporateManageProcessCreate from '@/views/Corporate/CorporateManageProcessCreate';
import CorporateManageProcessDetail from '@/views/Corporate/CorporateManageProcessDetail';
import CorporateManageApplicant from '@/views/Corporate/CorporateManageApplicant';
import CorporateManageApplicantList from '@/views/Corporate/CorporateManageApplicantList';
import CorporateManageApplicantDetail from '@/views/Corporate/CorporateManageApplicantDetail';
import CorporateManageApplicantCreate from '@/views/Corporate/CorporateManageApplicantCreate';
import CorporateManageInterview from '@/views/Corporate/CorporateManageInterview';
import CorporateManageInterviewList from '@/views/Corporate/CorporateManageInterviewList';
import CorporateManageInterviewSetting from '@/views/Corporate/CorporateManageInterviewSetting';
import CorporateManagePass from '@/views/Corporate/CorporateManagePass';
import CorporateManagePassDetail from '@/views/Corporate/CorporateManagePassDetail';
import CorporateStatistics from '@/views/Corporate/CorporateStatistics';
import CorporateStatisticsDetail from '@/views/Corporate/CorporateStatisticsDetail';
import CorporateNotice from '@/views/Corporate/CorporateNotice';
import CorporateProfile from '@/views/Corporate/CorporateProfile';
import CorporateFAQ from '@/views/Corporate/CorporateFAQ';

// 평가자
import EvaluatorHomeView from '@/views/Evaluator/EvaluatorHomeView';
import EvaluatorProfile from '@/views/Evaluator/EvaluatorProfile';
import EvaluatorTime from '@/views/Evaluator/EvaluatorTime';
import EvaluatorRecord from '@/views/Evaluator/EvaluatorRecord';
import EvaluatorFAQ from '@/views/Evaluator/EvaluatorFAQ';
// 지원자
import ApplicantHomeView from '@/views/Applicant/ApplicantHomeView';
import InterviewRoom from '@/views/InterviewDisplay/InterviewRoom';
import CorporateSheet from '@/views/Corporate/CorporateSheet';
import CorporateSheetCreate from '@/views/Corporate/CorporateSheetCreate';
import CorporateSheetDetail from '@/views/Corporate/CorporateSheetDetail';
import CorporateSheetEdit from '@/views/Corporate/CorporateSheetEdit';
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

  // 기업관리자
  {
    path: '/corporate',
    name: 'Corporate',
    component: CorporateHomeView,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/evaluator',
    name: 'CorporateManageEvaluator',
    component: CorporateManageEvaluator,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/evaluator/create',
    name: 'CorporateManageEvaluatorCreate',
    component: CorporateManageEvaluatorCreate,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/process',
    name: 'CorporateManageProcess',
    component: CorporateManageProcess,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/process/create',
    name: 'CorporateManageProcessCreate',
    component: CorporateManageProcessCreate,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/process/:id',
    name: 'CorporateManageProcessDetail',
    component: CorporateManageProcessDetail,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/applicant',
    name: 'CorporateManageApplicant',
    component: CorporateManageApplicant,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/applicant/list/:id',
    name: 'CorporateManageApplicantList',
    component: CorporateManageApplicantList,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/applicant/:id',
    name: 'CorporateManageApplicantDetail',
    component: CorporateManageApplicantDetail,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/applicant/create/:id',
    name: 'CorporateManageApplicantCreate',
    component: CorporateManageApplicantCreate,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/interview',
    name: 'CorporateManageInterview',
    component: CorporateManageInterview,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/interview/:id',
    name: 'CorporateManageInterviewList',
    component: CorporateManageInterviewList,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/interview/:processid/:id',
    name: 'CorporateManageInterviewSetting',
    component: CorporateManageInterviewSetting,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/pass',
    name: 'CorporateManagePass',
    component: CorporateManagePass,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/pass/:id',
    name: 'CorporateManagePassDetail',
    component: CorporateManagePassDetail,
  },
  {
    path: '/corporate/statistics',
    name: 'CorporateStatistics',
    component: CorporateStatistics,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/statistics/:id',
    name: 'CorporateStatisticsDetail',
    component: CorporateStatisticsDetail,
  },
  {
    path: '/corporate/notice',
    name: 'CorporateNotice',
    component: CorporateNotice,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/profile',
    name: 'CorporateProfile',
    component: CorporateProfile,
    beforeEnter: [checkLogin],
  },
  {
    path: '/corporate/faq',
    name: 'CorporateFAQ',
    component: CorporateFAQ,
    beforeEnter: [checkLogin],
  },

  // 평가자
  {
    path: '/evaluate/',
    name: 'Evaluator',
    component: EvaluatorHomeView,
    beforeEnter: [checkLogin],
  },
  {
    path: '/evaluate/profile/',
    name: 'EvaluatorProfile',
    component: EvaluatorProfile,
    beforeEnter: [checkLogin],
  },
  {
    path: '/evaluate/time/:id/',
    name: 'EvaluatorTime',
    component: EvaluatorTime,
    props: true,
    // props: route => ({ id: parseInt(route.params.id )}),
    beforeEnter: [checkLogin],
  },
  {
    path: '/evaluate/record',
    name: 'EvaluatorRecord',
    component: EvaluatorRecord,
    beforeEnter: [checkLogin],
  },
  {
    path: '/evaluate/faq',
    name: 'EvaluatorFAQ',
    component: EvaluatorFAQ,
    beforeEnter: [checkLogin],
  },
  // 지원자
  {
    path: '/Applicant/apply',
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
    path: '/corporate/sheet',
    name: 'CorporateSheet',
    component: CorporateSheet,
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
