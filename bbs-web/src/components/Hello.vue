<template>
  <div>
    hello wold {{ user }}

    <section>
      <article>
        age :{{ $store.state.age }}
        <el-button @click="addAge(3)">添加年龄</el-button>
      </article>
    </section>
  </div>
</template>

<script>
export default {
  name: "Hello",

  created() {
    this.user = this.$store.state.username;
  },
  methods: {
    addAge(n) {
      this.$store.commit("increaseUserAge", n);
    },
  },
};
</script>

<style scoped></style>
