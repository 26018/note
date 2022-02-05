<template>
  <div class="some">
    <h3>
      <li>{{ poem.title }}</li>
    </h3>
    <blockquote class="poem" v-html="changeStyle(poem.poem, poem.time)" />
  </div>
</template>

<script>
export default {
  name: "poem",
  props: {
    poem: {
      title: String,
      poem: String,
      time: String
    }
  },
  data() {
    return {};
  },
  methods: {
    changeStyle(Ypoem, time) {
      let changedSentence = "";
      if (Ypoem !== undefined) {
        let arr = Ypoem.split("\n");
        for (let index = 0; index < arr.length; index++) {
          changedSentence += "<p>" + arr[index] + "</p>";
        }
      } else {
        console.log("组件内poem为空:"+Ypoem);
      }
      return changedSentence + "<p><i>" + time + "</i></p>";
    }
  }
};
</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
}
.some {
  margin: 0 auto;
  width: 100%;
  margin: 28px auto;
}
h3 {
  text-align: left;
}

blockquote {
  border-radius: 10px;
  font-size: 14px;
  width: 100%;
  color: grey;
}
.poem >>> p {
  text-align: left;
  margin: 14px;
}
</style>
