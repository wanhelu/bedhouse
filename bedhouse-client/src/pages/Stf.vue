<template>
  <div class="table">
    <div class="container">
      StfPage
      <div class="handle-box">
        <el-button type="primary" size="mini" class="search-button" @click="delAll">搜索</el-button>
        <el-input v-model="select_word" size="mini" placeholder="筛选关键词" class="handle-input"></el-input>
        <el-button type="primary" size="mini" class="add-button" @click="centerDialogVisible = true">添加</el-button>
      </div>
      <el-table :data="data" border size="mini" style="width: 100%" height="450px" ref="multipleTable">

      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "Stf",
  data(){
    return{
      select_word:'',
      centerDialogVisible:false
    }
  },
  computed:{
    data(){
      return[]
    }
  },
  methods:{
    delAll(){}
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
}
.add-button{
  margin-left: 10px;
  float: right;
}
</style>