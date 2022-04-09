<template>
    <div id="poemEditCard" v-show="showTable">
        <div>
            <h3>
                <li>{{ poem.title }}</li>
            </h3>

            <button class="clickButton" @click="deletePoemData(poem.id)">
                delete
            </button>

            <button class="clickButton" @click="editPoemData(poem)">
                edit
            </button>
        </div>

        <blockquote class="poem" v-html="utils.noteParse(poem.content, poem.time)" />
    </div>
</template>

<script>
export default {
    name: 'PoemEditCard',
    props: {
        poem: {
            title: String,
            content: String,
            time: String,
        },
    },
    data() {
        return {
            poems: [],
            showTable: true,
        };
    },

    methods: {
        deletePoemOPT(poemID) {
            // 请求后台删除poem
            this.$axios({
                url: '/api/notes/' + poemID,
                method: 'delete',
            })
                .then((result) => {
                    console.log(result.data);
                    if (result.data.code === 200) {
                        // 保存成功通知
                        this.$message({
                            showClose: true,
                            message: '删除成功!',
                            type: 'success',
                            duration: 1000,
                        });
                    } else if (result.data === 'fail') {
                        // 保存失败通知
                        this.$message({
                            showClose: true,
                            message: '删除失败!',
                            type: 'error',
                        });
                    }
                })
                .catch((err) => {
                    this.$message({
                        showClose: true,
                        message: '未知错误! ' + err,
                        type: 'error',
                    });
                    console.log('err log:' + err);
                });
        },

        deletePoemData(poemID) {
            let that = this;
            this.$confirm('此操作将永久删除该选项, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            })
                .then(() => {
                    that.deletePoemOPT(poemID);
                    // 删除成功后 立即关闭盒子
                    that.showTable = false;
                })
                .catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除',
                        showClose: true,
                        duration: 1000,
                    });
                });
        },

        editPoemData(poem) {
            let poemString = poem.title + '\n' + poem.poem + poem.time;
            console.log(poemString);
            this.$message({
                showClose: true,
                message: '功能还没写!先等等..',
                type: 'error',
            });
        },
    },
};
</script>

<style lang="css" scoped>
* {
    padding: 0;
    margin: 0;
}

#poemEditCard {
    margin: 0 auto;
    width: 100%;
    margin: 28px auto;
}

h3 {
    text-align: left;
    width: 70%;
    display: inline-block;
}

li {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.clickButton {
    margin: 4px;
    display: block;
    float: right;
    border: 0;
    color: gray;
    padding: 4px;
    cursor: pointer;
    border-radius: 4px;
}

.clickButton:hover {
    float: right;
    border: 0;
    color: white;
    padding: 4px;
    cursor: pointer;
    background-color: seagreen;
    border-radius: 4px;
    outline: none;
}

blockquote {
    border-left: 4px gainsboro solid;
    font-size: 14px;
    width: 100%;
    max-height: 130px;
    color: grey;
    overflow: auto;
}

blockquote::-webkit-scrollbar {
    width: 3px;
    margin-right: 5px;
}

blockquote::-webkit-scrollbar-thumb {
    border-radius: 10px;
    background-color: goldenrod;
    width: 1px;
    -webkit-box-shadow: inset1px1px0rgba(0, 0, 0, 0.1);
}

.poem >>> p {
    text-align: left;
    margin: 14px;
}
</style>
