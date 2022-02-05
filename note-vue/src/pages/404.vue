<template>
  <div id="NotFound">
    <h2 @click="goToIndex()">走错了,别乱搞!点我跳回去</h2>
  </div>
</template>

<script>
export default {
  name: "NotFound",
  data() {
    return {};
  },

  components: {},

  computed: {},

  methods: {
    goToIndex() {
      this.$router.go(-1);
    }
  }
};
</script>
<style lang="css" scoped>
#NotFound {
  
}
h2 {
  text-align: center;
  margin: 0 auto;
  user-select: none;
}
</style>
