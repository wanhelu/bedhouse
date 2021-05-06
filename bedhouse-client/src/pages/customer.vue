<template>
  <div class="container">
    <div class="handle-box">
      <el-button type="primary" size="mini" class="search-button" @click="search">搜索</el-button>
      <el-input v-model="select_word" size="mini" placeholder="筛选关键词" class="handle-input"></el-input>
      <el-button type="primary" size="mini" class="add-button" @click="handleAdd" :disabled="this.loginStatus!=3">添加</el-button>
    </div>
    <el-table :data="data" border size="mini" style="width: 100%" height=450px ref="multipleTable">
      <el-table-column label="编号" prop="id" align="center" sortable ></el-table-column>
      <el-table-column label="姓名" prop="name" align="center" ></el-table-column>
      <el-table-column label="性别" prop="gender" align="center" ></el-table-column>
      <el-table-column label="年龄" prop="age" align="center" sortable ></el-table-column>
      <el-table-column label="操作"  align="center" >
        <template slot-scope="scope">
          <div class="optionButton">
            <el-button size="mini" class="optionButton" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="mini" class="optionButton" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <!--换页按钮-->
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
</template>

<script>
import {mapGetters} from "vuex";
import {mixin} from "@/mixin";
import {getCustomerInfo} from "@/api"

export default {
  name: "customer",
  mixins:[mixin],
  data(){
    return {
      select_word:'',
      tableData:[],
      currentPage: 1,
      pageSize:5,
      delVisible:false,
      editVisible:false,
      addVisible:false,
      form:{
       id:'',
       name:'',
       gender:'',
       age:''
      },
      delId:''
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
  methods:{
    getData(){
      this.tableData=[]
      getCustomerInfo().then(res=>{
        this.tableData=res
        this.currentPage=1
      }).catch(err=>{
        console.log(err)
      })
    },
    search(){},
    handleAdd(){},
    handleDelete(){},
    handleEdit(){},
    handleCurrentChange(val){
      this.currentPage=val
    }
  },
  mounted() {
    this.getData()
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