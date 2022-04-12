// 解析noteContent和time格式供vue显示
export function noteParse(noteContent, time) {
    let changedSentence = '';
    if (noteContent != null) {
        let arr = noteContent.split('\n');
        for (let index = 0; index < arr.length; index++) changedSentence += '<p>' + arr[index] + '</p>';
    }
    return changedSentence + '<p><i>' + this.dataFormate(time) + '</i></p>';
}

export function notes() {
    return axios({
        url: '/api/notes',
        method: 'get',
    });
}
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

export function noteSave(note) {
    return axios.post('/api/notes', {
        noteString: note,
    });
}
