import axios from "axios";
import router from "../router";
import { Message, MessageBox } from "element-ui";

export default {
  // 时间戳转换日期工具
  dataFormate(jsondate) {
    var timeStamp = parseInt(jsondate);
    timeStamp = timeStamp - 8 * 60 * 60 * 1000; //（本地时间）东八区减去8小时
    var date = new Date(timeStamp);
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();
    // var second = date.getSeconds();
    return (
      year +
      "-" +
      (month < 10 ? "0" + month : month) +
      "-" +
      (day < 10 ? "0" + day : day) +
      " " +
      (hour < 10 ? "0" + hour : hour) +
      ":" +
      (minute < 10 ? "0" + minute : minute)
      // +
      // ":" +
      // (second < 10 ? "0" + second : second)
    );
  },

  // 解析noteContent和time格式供vue显示
  noteParse(noteContent, time) {
    let changedSentence = "";
    if (noteContent != null) {
      let arr = noteContent.split("\n");
      for (let index = 0; index < arr.length; index++)
        changedSentence += "<p>" + arr[index] + "</p>";
    } else {
      this.floatMessage("noteContent is null", "fail", 2000);
    }
    return changedSentence + "<p><i>" + this.dataFormate(time) + "</i></p>";
  },

  // 请求所有的note,返回解析后的note, callback回调函数
  notes(callback) {
    axios({
      url: "/api/notes",
      method: "get"
    })
      .then(result => {
        callback(JSON.parse(result.data.data));
      })
      .catch(err => {
        this.floatMessage(err, "error", 2000, true);
      });
  },
  // 请求所有的poem,
  poems(callback) {
    axios({
      url: "/api/poem/poems",
      method: "get"
    }).then(res => {
      callback(res);
    });
  },
  // 用户登录
  userLogin(username, password) {
    if (
      username == null ||
      username.trim() == "" ||
      password == null ||
      password.trim() == ""
    ) {
      this.floatMessage("账号密码不能为空", "error", 2000, true);
      return;
    }

    axios({
      url: "/api/users/login",
      method: "post",
      data: {
        username: username,
        password: password
      }
    }).then(result => {
      if (result.data.code != 200) {
        this.floatMessage(
          result.data.message.toLowerCase(),
          "error",
          2000,
          true
        );
      } else {
        localStorage.setItem("token", result.data.data);
        router.push("/");
      }
    });
  },
  // 用户注册
  userRegister(username, password, randomsalt) {
    if (
      username == null ||
      username.trim() == "" ||
      password == null ||
      password.trim() == "" ||
      randomsalt == null ||
      randomsalt.trim() == ""
    ) {
      this.floatMessage("账号、验证码、密码不能为空", "error", 2000, true);
      return;
    }
    axios({
      url: "/api/users/register",
      method: "post",
      data: {
        username: username,
        password: password,
        randomsalt: randomsalt
      }
    }).then(result => {
      if (result.data.code != 200) {
        this.floatMessage(result.data.message.toLowerCase(), "error", 2000);
      } else {
        this.floatMessage("注册成功，请返回登录", "success", 2000);
      }
    });
  },

  randomSalt(username) {
    if (username == null || username.trim() == "") {
      this.floatMessage("账号不能为空", "error", 2000, true);
      return;
    }
    let that = this;
    axios({
      url: "/api/users/register/randomsalt",
      method: "post",
      data: {
        username: username
      }
    })
      .then(res => {
        if (res.data.code == 200) {
          that.floatMessage("验证码获取成功", "success", 2000, true);
        }
      })
      .catch(res => {
        that.floatMessage(res, "error", 2000, true);
      });
  },

  // 自定义提示信息
  floatMessage(msg, type, du, close) {
    Message({
      message: msg,
      type: type,
      duration: du,
      showClose: close
    });
  },

  noteSave: async function(callback) {
    let note = localStorage.getItem("unSavePoemString");
    let token = localStorage.getItem("token");
    if (note == null || token == null) {
      return;
    }
    let that = this;
    MessageBox.confirm("要保存到数据库吗?", "提示", {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
      type: "warning"
    })
      .then(async function() {
        // 请求后端写入数据库操作
        let poemString = localStorage.getItem("unSavePoemString");
        if (poemString == null || poemString.trim() == null) {
          that.floatMessage("至少得有题目和内容吧？", "warning", 2000);
          return;
        }
        let arr = poemString.replace(/\n/g, "<br>");
        // 保存数据到数据库
        await axios
          .post("/api/notes", {
            noteString: arr
          })
          .then(result => {
            if (result.data.code == 200) {
              that.floatMessage(
                "已经写进数据库啦，请放心",
                "success",
                2000,
                true
              );
              // 保存成功后的一系列操作
              // 例如：write的重新聚焦，unSavePoemString重置为空等
              callback();
            } else {
              that.floatMessage(result.data.message, "error", 2000, true);
            }
          })
          .catch(err => {
            that.floatMessage(err, "error", 2000, true);
          });
      })
      .catch(err => {
        that.floatMessage(err, "fail", 2000, true);
      });
  },
  // 单次点击导航
  navClick(path) {
    router.push(path);
  },

  // 保存数据到浏览器
  updatePoemData(poemString) {
    localStorage.setItem("unSavePoemString", poemString);
  }
};
