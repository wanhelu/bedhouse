<template>
  <div class="sidebar">
    <el-menu
        class="sidebar-el-menu"
        :default-active="onRoutes"
        :collapse="collapse"
        background-color="#e7e8e8"
        text-color="#273849"
        active-text-color="#409eff"
        router
    >
      <el-menu-item index="bed">
        床位管理
      </el-menu-item>
      <!--      <el-submenu index="2">
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
            </el-submenu>-->
      <el-submenu index="3">
        <template slot="title">膳食管理</template>
        <el-menu-item index="food">
          食品项
        </el-menu-item>
        <el-menu-item index="recipes">
          食谱
        </el-menu-item>
      </el-submenu>
      <!--      <el-menu-item>
              服务管理
            </el-menu-item>-->
      <el-menu-item index="customer">
        客户管理
      </el-menu-item>
      <el-menu-item index="live">
        入住管理
      </el-menu-item>
      <el-menu-item index="stf">
        员工管理
      </el-menu-item>
      <el-submenu index="4">
        <template slot="title">
          外出管理
        </template>
        <el-menu-item index="out">
          外出登记
        </el-menu-item>
        <el-menu-item index="check" v-if="this.loginStatus>=2">
          <el-badge :value="checkNum" :hidden="checkNum==0" class="item">
            审核
          </el-badge>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
import _ctrEvent from '../assets/js/ctr-event'
import {mapGetters} from "vuex";
import {NoCheckedCount} from "@/api";

export default {
  data () {
    return {
      collapse: false,
      checkNum: 0,
      timer: ''
    }
  },
  computed: {
    onRoutes() {
      return this.$route.path.replace('/', '')
    },
    ...mapGetters([
      'loginStatus'
    ])
  },
  methods: {
    getNoCheckedCount() {
      NoCheckedCount().then(res => {
        if (res.code == 1) this.checkNum = res.count
        else this.checkNum = 0
      }).catch(err => {
        console.log(err)
      })
    }
  },
  created() {
    _ctrEvent.$on('collapse', msg => {
      this.collapse = msg
    });
    this.getNoCheckedCount();
    this.timer = setInterval(this.getNoCheckedCount, 10000);
  },
  beforeDestroy() {
    clearInterval(this.timer);
  }
}
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  background-color: #e7e8e8;
  left: 0;
  top: 70px;
  bottom: 0;
  overflow-y: auto;
  overflow-x: hidden;
  text-align: left;
  width: 155px;
}
.sidebar > ul {
  height: 100%;
}
</style>
