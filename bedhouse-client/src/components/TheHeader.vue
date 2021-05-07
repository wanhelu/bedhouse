<template>
  <div class="header">
    <div class="logo">养老院管理系统</div>
    <div class="header-right">
      <div class="header-user-con">
        <!-- 用户名下拉菜单 -->
        <el-dropdown class="user-name" trigger="click" @command="handleCommand">
          <span class="el-dropdown-link">
            {{name}}
            <i class="el-icon-caret-bottom"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="info">详细信息</el-dropdown-item>
            <el-dropdown-item command="loginout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>
<script>
import _ctrEvent from '../assets/js/ctr-event'
import {mapGetters} from "vuex";

export default {
  data () {
    return {
      collapse: true,
      fullscreen: false
    }
  },
  computed:{
    ...mapGetters([
      'name'
    ])
  },
  mounted () {
    if (document.body.clientWidth < 1500) {
      this.collapseChage()
    }
  },
  methods: {
    // 用户名下拉菜单选择事件
    handleCommand (command) {
      if (command === 'loginout') {
        this.$store.commit('setLoginStatus',0)
        this.$store.commit('setName','')
        this.$router.push('/')
      }else if(command==='info') {
        this.$router.push('/info')
      }
    },
    // 侧边栏折叠
    collapseChage () {
      this.collapse = !this.collapse
      _ctrEvent.$emit('collapse', this.collapse)
    }
  }
}
</script>
<style scoped>
.header {
  position: absolute;
  z-index: 100;
  box-sizing: border-box;
  width: 100%;
  height: 70px;
  font-size: 22px;
  color: #B0B3B2;
  background: #1A1B1C;
}

.collapse-btn {
  float: left;
  padding: 0 21px;
  cursor: pointer;
  line-height: 70px;
}

.header .logo {
  float: left;
  width: 250px;
  line-height: 70px;
}

.header-right {
  float: right;
  padding-right: 50px;
}

.header-user-con {
  display: flex;
  height: 70px;
  align-items: center;
}

.user-name {
  margin-left: 10px;
}

.user-avator {
  margin-left: 20px;
}

.user-avator img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

.el-dropdown-link {
  color: #B0B3B2;
  cursor: pointer;
}

.el-dropdown-menu__item {
  text-align: center;
}
</style>
