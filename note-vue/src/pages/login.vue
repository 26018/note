<template>
  <div>
    <div id="title">Sign in to Note</div>
    <br />
    <div id="content">
      <div id="subTitle">Username</div>
      <input
        type="text"
        @focus="focusEvent()"
        name=""
        id="inputDiv"
        v-model="username"
      />

      <div id="subTitle">Password</div>
      <input
        type="password"
        @focus="focusEvent()"
        name=""
        id="inputDiv"
        v-model="password"
      />
      <br />
      <button
        @click="utils.userLogin(username, password, loginCallBack)"
        id="btn"
      >
        登录
      </button>
      <p v-show="vshow">{{ errMsg }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      username: "",
      password: "",
      vshow: false,
      errMsg: ""
    };
  },
  created() {},
  methods: {
    // 登录回调事件
    loginCallBack(data) {
      if (data.code != 200) {
        this.vshow = true;
        this.errMsg = data.message.toLowerCase();
      } else {
        localStorage.setItem("token", data.data);
        this.$router.push("/");
      }
    },
    // 用户输入时隐藏错误信息
    focusEvent() {
      if (this.vshow) {
        this.vshow = !this.vshow;
      }
    }
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
  height: 30vh;
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

#btn {
  margin: 0 auto;
  border-radius: 5px;
  border: 0px;
  outline: none;
  width: 70%;
  background-color: green;
  color: white;
  font-size: 14px;
  height: 30px;
  text-align: center;
}

p {
  margin-top: 10px;
  color: #ff9999;
  text-align: center;
}
</style>
