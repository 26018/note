<template>
  <div id="signin">
    <!-- <div id="title">Sign in to Note</div> -->
    <br />
    <div id="content">
      <div id="subTitle">邮箱</div>
      <el-input
        style="width:70%;margin: 0px auto;"
        size="small"
        v-model="username"
        placeholder="请输入账号"
      ></el-input>
      <div id="subTitle">密码</div>
      <el-input
        style="width:70%;margin: 0px auto;"
        size="small"
        placeholder="请输入密码"
        v-model="password"
        show-password
      ></el-input>
      <br />
      <div class="btnDiv">
        <el-button
          style="float: left;width:40%;font-size: 12px;"
          @click="utils.userLogin(username, password)"
          size="mini"
          type="primary"
          >登录</el-button
        >
        <el-button
          style="float: right;width:40%;font-size: 12px;"
          @click="utils.navClick('/signup')"
          size="mini"
          type="success"
          >注册</el-button
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "signin",
  data() {
    return {
      username: "",
      password: ""
    };
  }
};
</script>

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
  flex: 1;
  padding-top: 30px;
  margin: 0 auto;
  width: 80vw;
  height: 200px;
  display: flex;
  flex-direction: column;
  background-color: #f6f8fa;
  border-radius: 10px;
}

#subTitle {
  margin: 0px auto;
  width: 70%;
  height: 30px;
  -moz-outline-radius: 5px;
  outline: none;
  font-weight: 300;
  font-size: 12px;
  border: 0px;
  line-height: 30px;
}

.btnDiv {
  width: 70%;
  margin: 0 auto;
  border: 0px;
}
</style>
