<template>
  <div>
    <div id="title">Sign in to Note</div>
    <br />
    <div id="content">
      <div id="subTitle">账号</div>
      <input type="text" name="" id="inputDiv" v-model="username" />
      <div id="subTitle">密码</div>
      <input type="password" name="" id="inputDiv" v-model="password" />
      <br />
      <div class="btnDiv">
        <button @click="utils.userLogin(username, password)" id="login">
          登录</button
        ><button @click="utils.userRegister(username, password)" id="register">
          注册
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
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

#subTitle,
#inputDiv {
  margin: 0px auto;
  width: 70%;
  height: 30px;
  border-radius: 5px;
  -moz-outline-radius: 5px;
  border: 2px #eef1f4 solid;
  outline-color: #afcdf1;
}
#inputDiv {
  padding: 8px;
}
#subTitle {
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

#login,
#register {
  margin: 0 auto;
  border-radius: 5px;
  border: 0px;
  outline: none;
  width: 40%;
  color: white;
  font-size: 14px;
  height: 30px;
  text-align: center;
}

#login {
  background-color: green;
  float: left;
}

#register {
  background-color: gray;
  float: right;
}
p {
  margin-top: 10px;
  color: #ff9999;
  text-align: center;
}
</style>
