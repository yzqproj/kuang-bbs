<template>
  <div>
    111 这是第一个页面

    <div>
      <router-link to="/home">主页</router-link>
      <router-link to="/about">关于</router-link>
    </div>
    <router-view />
  </div>
</template>
<script>
export default {
  name:'App'
};
</script>
<style></style>
