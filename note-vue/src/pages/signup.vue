<template>
    <div id="signup">
        <br />
        <div id="content">
            <div class="mid">
                <div id="subTitle">邮箱</div>
                <el-input style="width:100%;margin: 0px auto;" size="small" v-model="username" placeholder="请输入账号"></el-input>

                <div id="subTitle">验证码</div>
                <el-input style="width:65%;" size="small" placeholder="请输入验证码" v-model="randomsalt" show-password></el-input>
                <el-button style="float:right;width:30%;font-size: 12px;" @click="randomSalt" size="small">验证码</el-button>

                <div id="subTitle">密码</div>
                <el-input style="width:100%;margin: 0px auto;" size="small" placeholder="请输入密码" v-model="password" show-password></el-input>
                <br />
                <div class="btnDiv">
                    <el-button style="float: left;width:40%;font-size: 12px;" @click="navClick('/signin')" size="mini" type="primary">返回登录</el-button>

                    <el-button style="float: right;width:40%;font-size: 12px;" @click="register" size="mini" type="success">注册</el-button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { Message } from 'element-ui';
import { randomSalt, register } from '../common/user';
import { navClick } from '../common/util';
export default {
    name: 'signin',
    data() {
        return {
            username: '',
            password: '',
            randomsalt: '',
        };
    },
    methods: {
        register() {
            register(this.username, this.password, this.randomsalt).then((res) => {
                Message({
                    message: res.message,
                    type: 'success',
                    showClose: true,
                    duration: 5 * 1000,
                });
            });
        },
        randomSalt() {
            randomSalt(this.username, this.password, this.randomsalt).then((res) => {
                Message({
                    message: res.message,
                    type: 'success',
                    showClose: true,
                    duration: 5 * 1000,
                });
            });
        },
        navClick(path) {
            navClick(path);
        },
    },
};
</script>

<style>
.el-message,
.el-message-box {
    max-width: 90% !important;
}
</style>
<style scoped>
#title {
    line-height: 40px;
    height: 40px;
    font-size: 24px;
    font-weight: 300;
    color: #24292f;
    width: 100%;
    text-align: center;
}

#content {
    /* border: 1px saddlebrown solid; */
    flex: 1;
    padding-top: 10px;
    margin: 0 auto;
    width: 80vw;
    height: 250px;
    display: flex;
    flex-direction: column;
    background-color: #f6f8fa;
    border-radius: 10px;
}

.mid {
    /* border: 1px salmon solid; */
    margin: 0 auto;
    width: 80%;
    box-sizing: border-box;
    height: 100%;
    /* background-color: red; */
}

#subTitle {
    margin: 0px auto;
    width: 100%;
    height: 30px;
    -moz-outline-radius: 5px;
    outline: none;
    font-weight: 300;
    font-size: 12px;
    border: 0px;
    line-height: 30px;
}

.btnDiv {
    width: 100%;
    margin: 6% auto;
    border: 0px;
}
</style>
