<template>
  <div class="red">这是about

    <p>

    </p>
    <mymine ></mymine>
  </div>
</template>

<script>
import mymine from './a.md'

export default {
  name: "About",
  components: {
   mymine
  }
}
</script>

<style lang="scss" scoped>
.red {
  color: red;
}
</style>
