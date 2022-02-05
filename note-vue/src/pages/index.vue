<template>
  <div id="index">
    <div class="index">
      <div @click="navClick('/write')" @dblclick="writeDoubleClick()" class="block">✍️</div>
      <div
        class="block"
        id="settingIcon"
        @dblclick="settingDoubleClick('/setting')"
      >
        🎉
      </div>
      <div @click="navClick('/read')" class="block">📖</div>
    </div>
    <router-view class="routerView" ref="child"/>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {};
  },
  methods: {
    // 单次点击导航
    navClick(path) {
      this.$router.push(path);
    },
    // 双击导航
    settingDoubleClick(path) {
      this.$router.push(path);
    },
    
    savePoemData(poemString) {
      if (
        poemString === "" ||
        poemString === null ||
        poemString === undefined ||
        poemString.trim() === "" ||
        poemString.trim() === null ||
        poemString.trim() === undefined
      ) {
        this.$message({
          showClose: true,
          message: "欲买桂花同载酒\n终不似少年游",
          type: "warning"

        });
        return;
      }
      let arr = poemString.replace(/\n/g, "<br>");
      // 保存数据到数据库
      let that = this;
      this.$axios({
        url: "/note/save/" + arr,
        method: "POST"
      })
        .then(result => {
          console.log("返回消息:" + result.data.message);
          // result.data.message 的值存在三个情况 [“success” || "fail" || "err"]
          let returnMessage = result.data.message
          if (returnMessage === "success") {
            // 保存成功通知
            this.$message({
            type: "success",
            message: "已经写进数据库啦，请放心",
            showClose:true,
            duration:2000
          });
          
            that.$refs.child.$refs.poemBox.focus();
            localStorage.removeItem("unSavePoemString");
            that.$refs.child.poemString = ""

          } else if (returnMessage === "fail") {
            // 保存失败通知
            this.$message({
              showClose: true,
              message: "保存失败!",
              type: "error"
            });
          } else {
            // 后台出现错误
            this.$message({
              showClose: true,
              message: "出现未知错误!",
              type: "error"
            });
          }
        })
        .catch(err => {
          this.$message({
            showClose: true,
            message: "未知错误! " + err,
            type: "error"
          });
          console.log("err log:" + err);
        });
    },

    writeDoubleClick(){
      let that = this;
      that.$confirm("你确定写完了吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          // TODO 写入数据库操作
          let poemString = localStorage.getItem("unSavePoemString");
          that.savePoemData(poemString)
        })
        .catch(() => {
          that.$message({
            type: "fail",
            message: "期待与你相遇更多的美好！",
            showClose:true,
            duration:2000
          });
        });
    }
  },
  mounted() {}
};
</script>

<style>
:root {
  --baseline: 8vh;
}

* {
  padding: 0;
  margin: 0;
}

body {
  width: 100%;
  height: 100%;
}

.el-message {
  min-width: 90% !important;
}

.el-message-box{
  width: 90% !important;
}

#index {
  width: 100%;
  height: 100%;
  padding: 0;
  margin: 0;
  position: relative;
}

.block {
  width: 20%;
  height: 100%;
  display: inline-block;
  text-align: center;
  line-height: var(--baseline);
  font-size: 20px;
  user-select: none;
}

.index {
  /* border: 1px solid ; */
  user-select: none;
  width: 100%;
  height: var(--baseline);
  top: 0;
  position: fixed;
  text-align: center;
  text-decoration: none;
}

.routerView {
  margin-top: var(--baseline);
  width: 100%;
  position: fixed;
  height: 94vh;
  overflow: scroll;
}

.routerView::-webkit-scrollbar {
  width: 0;
}
</style>
