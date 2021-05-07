<template>
  <div class="container">
    <div class="handle-box">
      <el-button type="primary" size="mini" class="search-button" @click="search">搜索</el-button>
      <el-input v-model="select_word" size="mini" placeholder="筛选关键词" class="handle-input"></el-input>
      <el-button type="primary" size="mini" class="add-button" @click="handleAdd" :disabled="this.loginStatus!=3">添加</el-button>
    </div>
    <el-table :data="data" border size="mini" style="width: 100%" height=450px ref="multipleTable">
      <el-table-column label="编号" prop="id" align="center" sortable ></el-table-column>
      <el-table-column label="用户编号" prop="customerId" align="center" sortable ></el-table-column>
      <el-table-column label="用户姓名" prop="customerName" align="center"></el-table-column>
      <el-table-column label="床位编号" prop="bedId" align="center" sortable ></el-table-column>
      <el-table-column label="入住时间" prop="beginDate" align="center" ></el-table-column>
      <el-table-column label="退住时间" prop="beginDate" align="center"></el-table-column>
      <el-table-column label="操作"  align="center" >
        <template slot-scope="scope">
          <div class="optionButton">
            <el-button size="mini" class="optionButton" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="mini" class="optionButton" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {mixin, mixinDriectly} from "@/mixin";
import {mapGetters} from "vuex";
import {getLiveInfo} from "@/api";

export default {
  name: "live",
  mixins:[mixin,mixinDriectly],
  data(){
    return{
      select_word:'',
      tableData:[],
      currentPage: 1,
      pageSize:5,
      delVisible:false,
      editVisible:false,
      addVisible:false,
      form:{
        id:'',
        customerId:'',
        bedId:'',
        beginDate:'',
        leaveDate:'',
        customerName:''
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
      getLiveInfo().then(res=>{
        console.log(res)
        this.tableData=res
        this.currentPage=1
      }).catch(err=>{
        console.log(err)
      })
    },
    search(){},
  },
  mounted(){
    this.getData()
  }
}
</script>

<style scoped>
@import "../assets/css/commenTable.css";
</style>