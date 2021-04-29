<template>
  <div class="login-wrap">
    <div class="ms-title">养老院管理系统</div>
    <div class="ms-login">
      <el-form
          ref="ruleForm"
          class="demo-ruleForm"
          :model="ruleForm"
          :rules="rules"
      >
        <el-form-item prop="username">
          <el-input v-model="ruleForm.username" placeholder="username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              type="password"
              placeholder="password"
              v-model="ruleForm.password"
              @keyup.enter.native="submitForm('ruleForm')"
          ></el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="submitForm">登录</el-button>
        </div>
        <p style="font-size:12px;line-height:30px;color:#999;">Tips : 用户名和密码要写数据库里的。</p>
      </el-form>
    </div>
  </div>
</template>

<script>
import {getLoginStatus} from "@/api";
import {mixin} from '../mixin/index'

export default {
  mixins: [mixin],
  data: function () {
    return {
      ruleForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      }
    }
  },
  methods: {
    submitForm () {
      let params = new URLSearchParams()
      if (!(this.ruleForm.username && this.ruleForm.password)) {
        this.notify('用户名和密码不能为空', 'error')
      } else {
        params.append('name', this.ruleForm.username)
        params.append('password', this.ruleForm.password)
        getLoginStatus(params)
            .then(res => {
              if (res.status >=1) {
                this.notify("登录成功","success")
                this.$store.commit("setLoginStatus",res.status)
                this.$store.commit('setName',res.username)
                this.$router.push('/home')
              } else {
                this.notify("用户名或密码错误","error")
              }
            })
            .catch(err => {
              console.log(err)
            })
      }
    }
  }
}
</script>

<style scoped>
.login-wrap {
  position: relative;
  background: #2c3e50;
  background-size: cover;
  width: 100%;
  height: 100%;
}

.ms-title {
  position: absolute;
  top: 50%;
  width: 100%;
  margin-top: -230px;
  text-align: center;
  font-size: 30px;
  font-weight: 600;
  color: #fff;
}

.ms-login {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 300px;
  height: 160px;
  margin: -150px 0 0 -190px;
  padding: 40px;
  border-radius: 5px;
  background: #fff;
}

.login-btn {
  text-align: center;
}

.login-btn button {
  width: 100%;
  height: 36px;
}
</style>
