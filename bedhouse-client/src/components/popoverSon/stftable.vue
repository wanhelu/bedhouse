<template>
  <div>
    <p>id:{{ tableData.id }}</p>
    <p>姓名:{{ tableData.name }}</p>
    <p>权限:{{ tableData.roleId }}</p>
    <el-link type="primary" @click="click()">详细信息</el-link>
  </div>
</template>

<script>
import {getStfInfoById} from "@/api";
import {popMixin} from "@/mixin";

export default {
  name: "stftable",
  mixins: [popMixin],
  data() {
    return {
      routerName: 'stf'
    }
  },
  methods: {
    func: getStfInfoById
  }
}
</script>

<style scoped>

</style>