import axios from 'axios';
import { dataFormate } from './util';
// 解析noteContent和time格式供vue显示
export function noteParse(noteContent, time) {
    let changedSentence = '';
    if (noteContent != null) {
        let arr = noteContent.split('\n');
        for (let index = 0; index < arr.length; index++) changedSentence += '<p>' + arr[index] + '</p>';
    }

    return changedSentence + '<p><i>' + dataFormate(time) + '</i></p>';
}

export function notes() {
    return axios({
        url: '/api/notes',
        method: 'get',
    });
}

export function noteSave(note) {
    return axios({
        url: '/api/notes',
        method: 'post',
        data: {
            noteString: note.replace(/\n/g, '<br>'),
        },
    });
}

export function updateByKeyDown(note) {
    localStorage.setItem('note-editing', note);
}

export function getEditingNote() {
    return localStorage.getItem('note-editing');
}

export function removeEditingNote() {
    localStorage.removeItem('note-editing');
}
