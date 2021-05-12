<template>
  <div>
    <p>id:{{ tableData.id }}</p>
    <p>房间号:{{ tableData.roomId }}</p>
    <p>备注:{{ tableData.detail }}</p>
    <el-link type="primary" @click="click()">详细信息</el-link>
  </div>
</template>

<script>
import {getBedInfoById} from "@/api";
import {popMixin} from "@/mixin";

export default {
  name: "bedtable",
  mixins: [popMixin],
  data() {
    return {
      routerName: 'bed'
    }
  },
  methods: {
    func: getBedInfoById
  }
}
</script>

<style scoped>

</style>