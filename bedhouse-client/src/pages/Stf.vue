<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" size="mini" class="search-button" @click="delAll">搜索</el-button>
        <el-input v-model="select_word" size="mini" placeholder="筛选关键词" class="handle-input"></el-input>
        <el-button type="primary" size="mini" class="add-button" @click="centerDialogVisible = true">添加</el-button>
      </div>
      <el-table :data="data" border size="mini" style="width: 100%" height=441px ref="multipleTable">
        <el-table-column label="编号" prop="id" align="center" ></el-table-column>
        <el-table-column label="姓名" prop="name" align="center"></el-table-column>
        <el-table-column label="性别" prop="gender" align="center"></el-table-column>
        <el-table-column label="年龄" prop="age" align="center"></el-table-column>
        <el-table-column label="入职日期" prop="entryTime" align="center"></el-table-column>
        <el-table-column label="电话号" prop="phone" align="center"></el-table-column>
        <el-table-column label="登录名" prop="loginName" align="center"></el-table-column>
        <el-table-column label="密码" prop="password" align="center"></el-table-column>
        <el-table-column label="上次登录时间" prop="lastTime" align="center"></el-table-column>
        <el-table-column label="上次登录地址" prop="lastAddress" align="center"></el-table-column>
        <el-table-column label="操作"  align="center">
          <template slot-scope="scope">
            <div class="optionButton">
            <el-button size="mini" class="optionButton" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="mini" class="optionButton" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
            @current-change="handleCurrentChange"
            background
            layout="total, prev, pager, next"
            :current-page="currentPage"
            :page-size="pageSize"
            :total="tableData.length">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import {getStfInfoLessRoleId} from "@/api";
import {mapGetters} from "vuex"

export default {
  name: "Stf",
  data(){
    return{
      select_word:'',
      centerDialogVisible:false,
      tableData: [],
      currentPage: 1,
      pageSize:5
    }
  },
  computed:{
    ...mapGetters([
      'loginStatus'
    ]),
    data(){
      return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },
  mounted() {
    this.getData()
  },
  methods:{
    delAll(){},
    handleEdit(row){},
    handleDelete(rowId){},
    getData(){
      this.tableData=[]
      console.log(1)
      getStfInfoLessRoleId(this.loginStatus).then(res=>{
        this.tableData=res
        this.currentPage=1
      }).catch(err=>{
        console.log(err)
      })
    },
    handleCurrentChange (val) {
      this.currentPage = val
    }
  }
}
</script>

<style scoped>
.handle-box {
  margin-bottom: 20px;
}
.handle-input {
  width: 300px;
  display: inline-block;
  float: left;
  margin: 10px;
}
.search-button{
  float: left;
  margin: 10px;
}
.add-button{
  margin: 10px;
  float: right;
}
.optionButton{
  margin: 2px;
}
.pagination {
  display: flex;
  justify-content: center;
  float: bottom;
}
</style>