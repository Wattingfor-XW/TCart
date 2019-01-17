axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.common['Authorization'] = localStorage['token'];
// axios.interceptors.request.use(function (config) {
//     // Do something before request is sent
//     config.headers.token = 'aaa';
//     return config;
// }, function (error) {
//     // Do something with request error
//     return Promise.reject(error);
// });
// if(!localStorage.getItem('token')){
//     location.href = 'UserLogin.html';
// }

if (!localStorage['token']) {
    location.href = 'login.html';
}