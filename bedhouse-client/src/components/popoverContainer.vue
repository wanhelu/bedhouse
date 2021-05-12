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
import bedtable from "@/components/popoverSon/bedtable";
import foodtable from "@/components/popoverSon/foodtable";
import customertable from "@/components/popoverSon/customertable";

export default {
  name: "popoverContainer",
  components: {
    stftable,
    bedtable,
    foodtable,
    customertable
  },
  props: {
    type: '', //数据类型
    id: '',   //数据编号
    text: ''  //显示的文本
  },
  data() {
    return {
      componentName: '',
      title: '',
      isShow: false
    }
  },
  mounted() {
    switch (this.type) {
      case 1:
        this.title = "员工信息"
        this.componentName = "stftable"
        break
      case 2:
        this.title = "床位信息"
        this.componentName = "bedtable"
        break
      case 3:
        this.title = "食品项信息";
        this.componentName = "foodtable";
        break;
      case 4:
        this.title = "客户信息";
        this.componentName = "customertable";
        break;
        4
    }
  }
}
</script>

<style scoped>

</style>