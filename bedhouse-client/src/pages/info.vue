<template>
  <div style="margin: auto">
    <div style="float: top">
      <el-button type="primary" @click="buttonClick">修改信息</el-button>
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
    <el-dialog title="编辑" :visible.sync="editVisible" >
      <el-form ref="form" :model="form" label-width="40px">
        <el-form-item label="姓名" size="mini">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="性别" size="mini">
          <el-input v-model="form.gender"></el-input>
        </el-form-item>
        <el-form-item label="年龄" size="mini">
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="登录名" size="mini">
          <el-input v-model="form.loginName"></el-input>
        </el-form-item>
        <el-form-item label="密码" size="mini">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="editVisible = false">取 消</el-button>
        <el-button type="primary" size="mini" @click="saveEdit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import {getStfInfoById,updateStfInfo} from "@/api";
import {mixin} from '../mixin'

export default {
  name: "info",
  mixins: [mixin],
  data() {
    return {
      userInfo: {},
      editVisible:false,
      form:{
        name:'',
        gender:'',
        age:'',
        loginName:'',
        password:''
      }
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
    },
    buttonClick(){
      this.form.name=this.userInfo.name
      this.form.age=this.userInfo.age
      this.form.gender=this.userInfo.gender
      this.form.loginName=this.userInfo.loginName
      this.form.password=this.userInfo.password
      this.editVisible = true
    },
    saveEdit(){
      let params = new URLSearchParams()
      params.append("name",this.form.name)
      params.append("gender",this.form.gender)
      params.append("age",this.form.age)
      params.append("loginName",this.form.loginName)
      params.append("password",this.form.password)
      params.append("id",this.userInfo.id)
      console.log(this.userInfo.id)
      updateStfInfo(params).then(res=>{
        if (res.code === 1) {
          this.notify('编辑成功', 'success')
          this.getData()
        } else {
          this.notify('编辑失败', 'error')
        }
      }).catch(err=>{
        console.log(err)
      })
      this.editVisible = false
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