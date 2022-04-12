import axios from 'axios';
// 用户登录
export function login(username, password) {
    return axios({
        url: '/api/users/login',
        method: 'post',
        data: {
            username: username,
            password: password,
        },
    });
}

// 用户注册
export function register(username, password, randomsalt) {
    return axios({
        url: '/api/users/register',
        method: 'post',
        data: {
            username: username,
            password: password,
            randomsalt: randomsalt,
        },
    });
}

export function randomSalt(username) {
    return axios({
        url: '/api/users/register/randomsalt',
        method: 'post',
        data: {
            username: username,
        },
    });
}

export function setToken(token) {
    localStorage.setItem('token', token);
}

export function removeToken() {
    localStorage.removeItem('token');
}
