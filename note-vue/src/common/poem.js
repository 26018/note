import axios from 'axios';
// 请求所有的poem,
export function poems(index) {
    return axios({
        url: '/api/poem/poems',
        method: 'post',
        data: {
            index: index,
        },
    });
}

export function setPoems(poems) {
    localStorage.setItem('poems', poems);
}

export function getPoems() {
    return localStorage.getItem('poems');
}

export function getIndex() {
    let index = localStorage.getItem('index');
    return index == null ? 0 : index;
}

export function setIndex(index) {
    localStorage.setItem('index', index);
}
