<template>
  <div id="write" ref="pronbit">
    <div class="writeBoxOBJ">
      <div class="poembox">
        <p>{{ poems }}</p>
      </div>
      <textarea
        :placeholder="sentence"
        class="writeBox"
        @keyup="utils.updatePoemData(poemString)"
        v-model="poemString"
        ref="poemBox"
        @keydown.s="CTRLAndS($event)"
      >
      {{ poemString }}
    </textarea
      >
    </div>
  </div>
</template>

<script>
import utils from "../common/utils";
export default {
  name: "write",
  data() {
    return {
      poemString: "",
      poems: "火树银花合，星桥铁锁开。",
      poemBox: {
        currentIdx: 0,
        data: [],
        createTime: 0,
        expiredTime: 86400000
      },
      sentence:
        "You should write the title on the first line where the cursor are，" +
        "then you can write the sentence on other lines where you want. " +
        "when you finish the work，you can press [CTRL+s] or double click write icon to save it\n\n\n" +
        "Follow the notes upon the journey\n" +
        "at first sight marks one's destiny\n" +
        "once the voyage comes to an end\n" +
        "return lies within hasty keys"
    };
  },

  methods: {
    CTRLAndS(e) {
      if (e.ctrlKey == true) {
        // CTRL + S 的操作
        let that = this;
        utils.noteSave(() => {
          localStorage.removeItem("unSavePoemString");
          that.poemString = "";
        });
      }
    }
  },
  mounted() {
    window.addEventListener("keydown", function(e) {
      if (
        e.keyCode == 83 &&
        (navigator.platform.match("Mac") ? e.metaKey : e.ctrlKey)
      ) {
        e.preventDefault();
      }
    });

    // 文本框自动获取焦点
    // this.$refs.poemBox.focus();
    // 检测是否存在未保存的输入
    this.poemString = localStorage.getItem("unSavePoemString");

    var pb = JSON.parse(localStorage.getItem("poemBox"));
    var currentIdx = localStorage.getItem("currentIdx");
    // 本地无存储或存储过期则向服务器请求（过期时间为一天）
    if (
      pb == null ||
      Date.parse(new Date()) - pb["expiredTime"] > pb["createTime"]
    ) {
      utils.poems(res => {
        pb = new Object();
        currentIdx = 0;
        pb["createTime"] = Date.parse(new Date());
        pb["data"] = JSON.parse(res.data.data);
        pb["expiredTime"] = 86400000;
        localStorage.setItem("currentIdx", currentIdx);
        localStorage.setItem("poemBox", JSON.stringify(pb));
      });
    }

    this.poems = pb == null ? this.poems : pb["data"][++currentIdx]["sentence"];
    setInterval(() => {
      if (currentIdx >= pb["data"].length - 1) {
        currentIdx = 0;
      }
      this.poems = pb["data"][++currentIdx]["sentence"];
      localStorage.setItem("currentIdx", currentIdx);
    }, 8000);
  }
};
</script>

<style lang="css" scoped>
@font-face {
  font-family: "Github";
  src: url("https://fonts.gstatic.com/s/lato/v17/S6u8w4BMUTPHjxsAXC-q.woff2");
}

/* 第一个框 */
@media only screen and (max-device-width: 768px) {
  .poembox {
    height: 30px;
    /* border: 1px saddlebrown solid; */
  }
  .writeBox {
    font-family: Github;
    font-size: 16px;
    width: 100%;
    height: 90%;
    resize: none;
    border: 0px;
    outline: none;
    /* border: 1px slateblue solid; */
  }

  .writeBoxOBJ {
    /* border: 1px purple solid; */
    margin: 0 auto;
    width: 90%;
    height: 100%;
  }

  p {
    padding: 0;
    margin: 0;
    text-align: center;
    width: 100%;
    border-radius: 3px;
    background-color: gainsboro;
    color: gray;
  }

  .saveBtn {
    border: 1px solid red;
    float: left;
    width: 10%;
    position: absolute;
  }

  .writeBox::-webkit-scrollbar {
    width: 0;
  }
}
</style>
