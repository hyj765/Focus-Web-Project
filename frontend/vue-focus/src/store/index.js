import { createStore } from 'vuex';
import axios from 'axios';

const BASE_URL = 'http://i8a106.p.ssafy.io/api';

export default createStore({
  state: {
    user: null,
  },
  getters: {
    loggedIn(state) {
      return !!state.user;
    },
  },
  mutations: {
    SET_USER_DATA(state, userData) {
      state.user = userData;
      localStorage.setItem('user', JSON.stringify(userData));
      axios.defaults.headers.common[
        'Authorization'
      ] = `Bearer ${userData.accessToken}`;
      console.log(
        'axios headers: ',
        axios.defaults.headers.common['Authorization'],
      );
    },
    LOGOUT(state) {
      state.user = null;
      localStorage.removeItem('user');
      axios.defaults.headers.common['Authorization'] = null;
    },
  },
  actions: {
    login({ commit }, credentials) {
      return axios
        .post(`${BASE_URL}/auth/login`, credentials)
        .then(({ data }) => {
          commit('SET_USER_DATA', data);
          console.log('user: ', this.state.user);
        })
        .catch(err => {
          console.log('login request failed! ', err.message);
        });
    },
    logout({ commit }) {
      commit('LOGOUT');
    },
  },
});
