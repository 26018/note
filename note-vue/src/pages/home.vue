<template>
    <div id="home">
        <div class="index">
            <div @click="nav('/write')" @dblclick="save()" class="block">
                ✍️
            </div>
            <div class="block" id="settingIcon" @dblclick="nav('/setting')">
                🎉
            </div>
            <div @click="nav('/read')" class="block">📖</div>
        </div>
    </div>
</template>

<script>
import { getEditingNote, noteSave, removeEditingNote } from '../common/note';
import { navClick } from '../common/util';
import { MessageBox, Message } from 'element-ui';
export default {
    name: 'index',
    data() {
        return {};
    },
    methods: {
        save() {
            this.noteSaveConfirm();
        },
        noteSaveConfirm() {
            let editNote = getEditingNote();
            if (editNote == null || editNote.trim().length == 0) {
                return;
            }
            MessageBox.confirm('保存note, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            })
                .then(async () => {
                    await noteSave(getEditingNote()).then((res) => {
                        if (res.code == 200) {
                            Message({
                                message: '保存成功',
                                type: 'success',
                                showClose: true,
                                duration: 2000,
                            });
                            this.$parent.$refs.child.note = '';
                            removeEditingNote();
                        }
                        return;
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

        nav(path) {
            navClick(path);
        },
    },
};
</script>

<style scoped>
::root {
    --baseline: 8vh;
}

#home {
    width: 100%;
    height: 50px;
    padding: 0;
    margin: 0;
}

.block {
    width: 20%;
    display: inline-block;
    text-align: center;
    line-height: 50px;
    font-size: 20px;
    user-select: none;
}

.index {
    user-select: none;
    width: 100%;
    text-align: center;
    text-decoration: none;
}
</style>

<style>
.el-message,
.el-message-box {
    min-width: 90% !important;
}
</style>
