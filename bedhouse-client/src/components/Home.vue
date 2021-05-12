<template>
  <div>
    <the-header></the-header>
    <the-aside></the-aside>
    <div class="content-box" :class="{'content-collapse':collapse}">
      <div class="content">
        <transition name="fade" mode="out-in" :duration="{enter:200,leave:200}">
          <router-view></router-view>
        </transition>
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
.fade-enter-active, .fade-leave-active {
  transition: opacity .5s
}

.fade-enter, .fade-leave-active {
  opacity: 0
}
</style>