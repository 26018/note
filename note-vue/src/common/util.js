import router from '../router';

// 时间戳转换日期工具
export function dataFormate(jsondate) {
    var timeStamp = parseInt(jsondate);
    timeStamp = timeStamp - 8 * 60 * 60 * 1000; //（本地时间）东八区减去8小时
    var date = new Date(timeStamp);
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    // var second = date.getSeconds();
    return (
        year + '-' + (month < 10 ? '0' + month : month) + '-' + (day < 10 ? '0' + day : day) + ' ' + (hour < 10 ? '0' + hour : hour) + ':' + (minute < 10 ? '0' + minute : minute)
        // +
        // ":" +
        // (second < 10 ? "0" + second : second)
    );
}

// 单次点击导航
export function navClick(path) {
    if (!window.location.href.includes(path)) {
        router.replace(path);
    }
}

// 保存数据到浏览器
export function updatePoemData(poemString) {
    localStorage.setItem('unSavePoemString', poemString);
}
