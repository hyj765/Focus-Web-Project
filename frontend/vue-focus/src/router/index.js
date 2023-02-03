import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '@/views/LoginView';
import ServiceHomeView from '@/views/ServiceHomeView';
import CorporateHomeView from '@/views/CorporateHomeView';
import EvaluatorHomeView from '@/views/EvaluatorHomeView';
import ApplicantHomeView from '@/views/ApplicantHomeView';

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
  },
  {
    path: '/corporate',
    name: 'Corporate',
    component: CorporateHomeView,
  },
  {
    path: '/evaluate',
    name: 'Evaluator',
    component: EvaluatorHomeView,
  },
  {
    path: '/apply',
    name: 'Applicant',
    component: ApplicantHomeView,
  },
];

const router = createRouter({
  history: createWebHistory('/'),
  routes: routes,
});

export default router;
