<template>
  <div>
    <p>名称:{{ tableData.name }}</p>
    <p>类型:{{ tableData.type }}</p>
    <p>价格:{{ tableData.price }}</p>
    <p>标签:{{ tableData.label }}</p>
    <p>清真:{{ tableData.muslim }}</p>
    <el-link type="primary" @click="click()">详细信息</el-link>
  </div>
</template>

<script>
import {getFoodInfoById} from "@/api";
import {popMixin} from "@/mixin";

export default {
  name: "foodtable",
  mixins: [popMixin],
  data() {
    return {
      routerName: 'food'
    }
  },
  methods: {
    func: getFoodInfoById
  }
}
</script>

<style scoped>

</style>