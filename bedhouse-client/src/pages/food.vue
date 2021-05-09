<template>
  <div class="container">
    <div class="handle-box">
      <el-button type="primary" size="mini" class="search-button" @click="search">搜索</el-button>
      <el-input v-model="select_word" size="mini" placeholder="筛选关键词" class="handle-input"></el-input>
      <el-button type="primary" size="mini" class="add-button" @click="handleAdd" :disabled="this.loginStatus!=3">添加</el-button>
    </div>
    <el-table :data="data" border size="mini" style="width: 100%" height=450px ref="multipleTable">
      <el-table-column label="编号" prop="id" align="center" sortable ></el-table-column>
      <el-table-column label="名称" prop="name" align="center"></el-table-column>
      <el-table-column label="类型" prop="type" align="center"></el-table-column>
      <el-table-column label="标签" align="center">
        <template slot-scope="scope">
          <el-tag v-for="item in scope.row.labelArray" class="tag">
            {{item}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="价格" prop="price" align="center" sortable ></el-table-column>
      <el-table-column label="图片" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.picurl"/>
        </template>
      </el-table-column>
      <el-table-column label="清真" prop="muslim" align="center"></el-table-column>
      <el-table-column label="操作"  align="center">
        <template slot-scope="scope">
          <div class="optionButton">
            <el-button size="mini" class="optionButton" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="mini" class="optionButton" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>

    <!--翻页按钮-->
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
import {mixin, mixinDriectly} from "@/mixin";
import {mapGetters} from "vuex";
import {getFoodInfo} from "@/api";

export default {
  name: "food",
  mixins:[mixin,mixinDriectly],
  data(){
    return {
      form: {
        id:'',
        name:'',
        type:'',
        label:'',
        price:'',
        picurl:'',
        muslim:'',
        labelArray:[]
      }
    }
  },
  computed:{
    ...mapGetters([
      'loginStatus'
    ]),
    data(){
      let temp=this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
      for(let item of temp){
        this.$set(item,"labelArray",item.label.split("-"))
      }
      return temp
    }
  },
  methods:{
    getData(){
      this.tableData=[]
      getFoodInfo().then(res=>{
        this.tableData=res
        this.createLabelArray()
        this.currentPage=1
      }).catch(err=>{
        console.log(err)
      })
    },
    search(){

    },
    saveAdd(){},
    deleteRow(){},
    saveEdit(){},
    createLabelArray(){
      for(let item of this.tableData){
        this.$set(item,"labelArray",item.label.split("-"))
      }
    }
  },
  mounted() {
    this.getData()
  }
}
</script>

<style scoped>
@import "../assets/css/commenTable.css";
.tag{
  margin: 1px;
}
</style>