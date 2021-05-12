<template>
  <div>
    <p>姓名:{{ tableData.name }}</p>
    <p>性别:{{ tableData.gender }}</p>
    <p>年龄:{{ tableData.age }}</p>
    <el-link type="primary" @click="click()">详细信息</el-link>
  </div>
</template>

<script>
import {getCustomerInfoById} from "@/api";
import {popMixin} from "@/mixin";

export default {
  name: "customertable",
  mixins: [popMixin],
  data() {
    return {
      routerName: 'customer'
    }
  },
  methods: {
    func: getCustomerInfoById
  }
}
</script>

<style scoped>

</style>