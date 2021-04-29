<template>
  <div>
    <the-header></the-header>
    <the-aside></the-aside>
    <div class="content-box" :class="{'content-collapse':collapse}">
      <div class="content">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import TheHeader from "@/components/TheHeader";
import TheAside from "@/components/TheAside";
import _ctrEvent from "@/assets/js/ctr-event";

export default {
  name: "Home",
  components:{
    TheAside,
    TheHeader
  },
  data () {
    return {
      collapse: false
    }
  },
  created () {
    _ctrEvent.$on('collapse', msg => {
      this.collapse = msg
    })
  }
}
</script>

<style scoped>

</style>