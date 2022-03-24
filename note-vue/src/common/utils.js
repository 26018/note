import axios from "axios";

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

  // 解析noteContent和time格式
  changeStyle(noteContent, time) {
    let changedSentence = "";
    if (noteContent !== undefined) {
      let arr = noteContent.split("\n");
      for (let index = 0; index < arr.length; index++)
        changedSentence += "<p>" + arr[index] + "</p>";
    } else console.log("组件内poem为空");
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
        console.log("err log:" + err);
      });
  },

  // 用户登录
  userLogin(username, password, callback) {
    axios({
      url: "/api/users/login",
      method: "post",
      data: {
        username: username,
        password: password
      }
    }).then(result => {
      callback(result.data);
    });
  },

  poems(callback) {
    axios({
      url: "/api/poem/poems",
      method: "get"
    }).then(res => {
      callback(res);
    });
  }
};
