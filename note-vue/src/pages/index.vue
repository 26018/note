<template>
  <div>
    <!-- <div id="title">Sign in to Note</div>
    <br />
    <div id="content">
      <div id="subTitle">账号</div>
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
          @click="utils.userRegister(username, password)"
          size="mini"
          type="success"
          >注册</el-button
        >
      </div>
    </div> -->

    <!-- <div class="navbar">
      <router-link to="/index/signin" class="block" style="float:left">
        Sign in
      </router-link>
      <router-link to="/index/signup" class="block" style="float:right">
        Sign up</router-link
      >
    </div>
    <router-view class="abc" /> -->
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {};
  }
};
</script>

<style scoped>
.abc {
  /* border: 1px saddlebrown solid; */
  height: 90%;
  /* background-color: red; */
  width: 100%;
}
.navbar {
  margin: 0 auto;
  height: 40px;
  width: 80%;
  background-color: #f6f8fa;
  /* border: 1px salmon solid; */
}

.block {
  display: inline-block;
  width: 30%;
  padding: 0;
  margin: 0;
  height: 100%;
  line-height: 200%;
  /* border: 1px saddlebrown solid; */
  text-align: center;
  text-decoration: none;
  color: brown;

  border-radius: 10%;
}
</style>
