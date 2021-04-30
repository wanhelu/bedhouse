<template>
  <div style="margin: auto">
    <div style="float: top">
      <el-button type="primary">修改信息</el-button>
    </div>
    <div class="container1">
      <el-card shadow="hover" class="card">
        <span class="info-span">姓名:{{ userInfo.name }}</span>
        <el-divider></el-divider>
        <span class="info-span">性别:{{ userInfo.gender }}</span>
        <el-divider></el-divider>
        <span class="info-span">年龄:{{ userInfo.age }}</span>
        <el-divider></el-divider>
        <span class="info-span">入职时间:{{ userInfo.entryTime }}</span>
        <el-divider></el-divider>
      </el-card>
      <el-card shadow="hover" class="card">
        <span class="info-span">员工编号:{{ userInfo.id }}</span>
        <el-divider></el-divider>
        <span class="info-span">登录名:{{ userInfo.loginName }}</span>
        <el-divider></el-divider>
        <span class="info-span">密码:{{ userInfo.password }}</span>
        <el-divider></el-divider>
        <span class="info-span">权限等级:{{ userInfo.roleId }}</span>
        <el-divider></el-divider>
      </el-card>
      <!--    <el-card shadow="hover" class="card">
            <span class="info-span">上次登录时间:<br/>{{userInfo.lastTime}}</span>
            <el-divider></el-divider>
            <span class="info-span">上次登录地址:<br/>{{userInfo.lastAddress}}</span>
            <el-divider></el-divider>
          </el-card>-->
    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import {getStfInfoById} from "@/api";

export default {
  name: "info",
  data() {
    return {
      userInfo: {}
    }
  },
  computed: {
    ...mapGetters([
      'id'
    ])
  },
  mounted() {
    this.getData()
  },
  methods: {
    getData() {
      getStfInfoById(this.id).then(res => {
        console.log(res)
        this.userInfo = res
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style scoped>
.container1 {
  text-align: center;
  width: 684px;
  margin: auto;
}

.card {
  width: 300px;
  height: 300px;
  margin: 20px;
  float: left;
}
</style>