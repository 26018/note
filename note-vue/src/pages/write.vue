<template>
  <div id="write" ref="pronbit">
    <div class="writeBoxOBJ">
      <textarea
        :placeholder="sentence"
        class="writeBox"
        @keyup="updatePoemData(poemString)"
        v-model="poemString"
        ref="poemBox"
        @keydown.s="show2($event)"
      >
      {{ poemString }}
    </textarea
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "write",
  data() {
    return {
      poemString: "",
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
    show2(e) {
      if (e.ctrlKey == true) {
        this.savePoemData(this.poemString);
      }
    },
    updatePoemData(poemString) {
      // 保存数据到浏览器
      localStorage.setItem("unSavePoemString", poemString);
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
    this.$refs.poemBox.focus();
    // 检测是否存在未保存的输入
    this.poemString = localStorage.getItem("unSavePoemString");
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
  .writeBoxOBJ {
    /* border: 1px purple solid; */
    margin: 0 auto;
    width: 90%;
    height: 80%;
  }

  .saveBtn {
    border: 1px solid red;
    float: left;
    width: 10%;
    position: absolute;
  }

  .writeBox {
    font-family: Github;
    font-size: 16px;
    width: 100%;
    height: 95%;
    resize: none;
    border: 0px;
    outline: none;
    /* border: 1px solid red; */
  }
  .writeBox::-webkit-scrollbar {
    width: 0;
  }
}

</style>
