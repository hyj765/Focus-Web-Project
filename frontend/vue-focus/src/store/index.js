import { createStore } from 'vuex';
import axios from 'axios';

const BASE_URL = 'https://i8a106.p.ssafy.io/api';

export default createStore({
  state: {
    user: null,
    // corporates: null,
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
    CREATE(state) {},
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
    createcompanyaccount({ commit }, companyinfo) {
      console.log('store user data: ', this.state.user);
      console.log('companyinfo: ', companyinfo);
      axios
        .post(
          `${BASE_URL}/serviceusers/accounts`,
          { body: companyinfo },
          {
            headers: {
              Authorization: `Bearer ${this.state.user.accessToken}`,
            },
          },
        )
        .then(({ data }) => {
          console.log(data);
          commit('CREATE');
        })
        .catch(err => {
          console.log(err.message);
        });
    },
    // getCorporateInfos() {
    //   const user = JSON.parse(localStorage.getItem('user'));
    //   console.log(user.accessToken);
    //   axios
    //     .get(`${BASE_URL}/serviceusers/accounts`, {
    //       Authorization: `Bearer ${user.accessToken}`,
    //     })
    //     .then(res => {
    //       this.state.corporates = res.data;
    //       console.log('ACTIONS state: ', this.state.corporates);
    //     });
    // },
  },
});
