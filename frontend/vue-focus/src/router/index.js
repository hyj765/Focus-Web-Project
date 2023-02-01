import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '@/views/LoginView';
import ServiceManagerView from '@/views/ServiceManagerView';
import CorporateManagerView from '@/views/CorporateManagerView';
import EvaluatorView from '@/views/EvaluatorView';
import ApplicantView from '@/views/ApplicantView';

const routes = [
  {
    path: '/',
    name: 'Login',
    component: LoginView,
  },
  {
    path: '/service',
    name: 'Service',
    component: ServiceManagerView,
  },
  {
    path: '/corporate',
    name: 'Corporate',
    component: CorporateManagerView,
  },
  {
    path: '/evaluate',
    name: 'Evaluator',
    component: EvaluatorView,
  },
  {
    path: '/apply',
    name: 'Applicant',
    component: ApplicantView,
  },
];

const router = createRouter({
  history: createWebHistory('/'),
  routes: routes,
});

export default router;
