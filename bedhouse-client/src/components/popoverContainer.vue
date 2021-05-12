<template>
  <el-popover
      placement="top"
      :title="title"
      width="200"
      trigger="click"
      @show="isShow=true"
      @hide="isShow=false">
    <component :is="this.componentName" :id="this.id" v-if="isShow"></component>
    <el-button slot="reference" type="text">{{ text }}</el-button>
  </el-popover>
</template>

<script>
import stftable from "@/components/popoverSon/stftable";

export default {
  name: "popoverContainer",
  components: {
    stftable
  },
  props: {
    type: '',
    id: '',
    text: ''
  },
  data() {
    return {
      componentName: '',
      title: '',
      isShow: false
    }
  },
  mounted() {
    if (this.type == 1) {
      this.title = "员工信息"
      this.componentName = "stftable"
    }
  }
}
</script>

<style scoped>

</style>