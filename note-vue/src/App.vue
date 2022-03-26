<template>
  <div id="app">
    <index />
    <router-view class="routerView" ref="child" />
  </div>
</template>

<script>
import index from "./pages/index.vue";
export default {
  name: "App",
  components: { index },
  data() {
    return {};
  }
};
</script>

<style></style>
