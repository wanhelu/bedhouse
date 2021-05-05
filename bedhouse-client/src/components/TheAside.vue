<template>
  <div class="sidebar">
    <el-menu
        class="sidebar-el-menu"
        :default-active="onRoutes"
        :collapse="collapse"
        background-color="#242526"
        text-color="#B0B3B2"
        active-text-color="#ffffff"
        unique-opened
        router
    >
      <el-menu-item index="bed">
        床位管理
      </el-menu-item>
      <el-submenu index="2">
        <template slot="title">
          护理管理
        </template>
        <el-menu-item>
          护理内容项
        </el-menu-item>
        <el-menu-item>
          护理级别
        </el-menu-item>
        <el-menu-item>
          护理记录
        </el-menu-item>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">膳食管理</template>
        <el-menu-item>
          食品项
        </el-menu-item>
        <el-menu-item>
          食谱
        </el-menu-item>
      </el-submenu>
      <el-menu-item>
        服务管理
      </el-menu-item>
      <el-menu-item>
        客户管理
      </el-menu-item>
      <el-menu-item index="stf">
        员工管理
      </el-menu-item>
      <el-submenu index="4">
        <template slot="title">
          外出管理
        </template>
        <el-menu-item>
          外出登记
        </el-menu-item>
        <el-menu-item>
          历史记录
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
import _ctrEvent from '../assets/js/ctr-event'

export default {
  data () {
    return {
      collapse: false
    }
  },
  computed: {
    onRoutes () {
      return this.$route.path.replace('/', '')
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
.sidebar {
  display: block;
  position: absolute;
  background-color: #334256;
  left: 0;
  top: 70px;
  bottom: 0;
  overflow-y: auto;
  overflow-x:hidden ;
  text-align: left;
  width: 155px;
}
.sidebar > ul {
  height: 100%;
}

</style>
