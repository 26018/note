<template>
    <div id="write">
        <div class="writeBoxOBJ">
            <div class="poembox">
                <p>{{ poem }}</p>
            </div>
            <textarea :placeholder="sentence" class="writeBox" @keyup="update" v-model="note" @keydown.s="CTRLAndS($event)">
      {{ note }}
    </textarea
            >
        </div>
    </div>
</template>

<script>
import { getEditingNote, noteSave, removeEditingNote, updateByKeyDown } from '../common/note';
import { getIndex, getPoems, poems, setIndex, setPoems } from '../common/poem';
import { MessageBox, Message } from 'element-ui';
export default {
    name: 'write',
    data() {
        return {
            note: '',
            poem: '火树银花合，星桥铁锁开。',
            poemBox: {
                currentIdx: 0,
                data: [],
                createTime: 0,
                expiredTime: 86400000,
            },
            sentence:
                'You should write the title on the first line where the cursor are，' +
                'then you can write the sentence on other lines where you want. ' +
                'when you finish the work，you can press [CTRL+s] or double click write icon to save it\n\n\n' +
                'Follow the notes upon the journey\n' +
                "at first sight marks one's destiny\n" +
                'once the voyage comes to an end\n' +
                'return lies within hasty keys',
        };
    },

    methods: {
        CTRLAndS(e) {
            if (e.ctrlKey == true) {
                // CTRL + S 的操作
                this.noteSaveConfirm();
            }
        },
        update() {
            updateByKeyDown(this.note);
        },

        noteSaveConfirm() {
            if (this.note == null || this.note.trim().length == 0) {
                return;
            }
            MessageBox.confirm('保存note, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            })
                .then(async () => {
                    await noteSave(this.note).then((res) => {
                        if (res.code == 200) {
                            Message({
                                message: '保存成功',
                                type: 'success',
                                showClose: true,
                                duration: 2000,
                            });
                            this.note = '';
                            removeEditingNote();
                        }
                    });
                })
                .catch(() => {
                    Message({
                        type: 'info',
                        message: '已取消删除',
                        showClose: true,
                        duration: 2000,
                    });
                });
        },
    },
    mounted() {
        window.addEventListener('keydown', function(e) {
            if (e.keyCode == 83 && (navigator.platform.match('Mac') ? e.metaKey : e.ctrlKey)) {
                e.preventDefault();
            }
        });

        // 获取未保存的输入
        this.note = getEditingNote();
        var pb = JSON.parse(getPoems());
        var currentIdx = localStorage.getItem('currentIdx');
        // 本地无存储或存储过期则向服务器请求（过期时间为一天）
        if (pb == null || Date.parse(new Date()) - pb['expiredTime'] > pb['createTime']) {
            poems(getIndex()).then((res) => {
                pb = new Object();
                currentIdx = 0;
                let obj = JSON.parse(res.data);
                let index = obj[0]['index'];
                console.log('return Index:' + index);
                pb['createTime'] = Date.parse(new Date());
                pb['data'] = obj[1];
                pb['expiredTime'] = 86400000;
                setIndex(index);
                localStorage.setItem('currentIdx', currentIdx);
                setPoems(JSON.stringify(pb));
            });
        }

        let len = pb['data'].length;
        if (currentIdx + 1 >= len) {
            currentIdx = currentIdx % len;
        }
        this.poem = pb['data'][currentIdx]['sentence'];
        setInterval(() => {
            if (currentIdx >= len) {
                currentIdx = 0;
            }
            this.poem = pb['data'][currentIdx++]['sentence'];
            localStorage.setItem('currentIdx', currentIdx);
        }, 7000);
    },
};
</script>

<style>
.el-message,
.el-message-box {
    max-width: 90% !important;
}
</style>

<style lang="css" scoped>
@font-face {
    font-family: 'Github';
    src: url('https://fonts.gstatic.com/s/lato/v17/S6u8w4BMUTPHjxsAXC-q.woff2');
}

/* 第一个框 */
@media only screen and (max-device-width: 768px) {
    .poembox {
        height: 30px;
    }
    .writeBox {
        font-family: Github;
        font-size: 16px;
        width: 100%;
        height: 90%;
        resize: none;
        border: 0px;
        outline: none;
    }

    .writeBoxOBJ {
        margin: 0 auto;
        width: 90%;
        height: 100%;
    }

    p {
        padding: 0;
        margin: 0;
        text-align: center;
        width: 100%;
        border-radius: 3px;
        background-color: gainsboro;
        color: gray;
    }

    .saveBtn {
        border: 1px solid red;
        float: left;
        width: 10%;
        position: absolute;
    }

    .writeBox::-webkit-scrollbar {
        width: 0;
    }
}
</style>
