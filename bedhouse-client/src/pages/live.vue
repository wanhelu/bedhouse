<template>
  <div class="container">
    <div class="handle-box">
      <el-button type="primary" size="mini" class="search-button" @click="search">搜索</el-button>
      <el-input v-model="select_word" size="mini" placeholder="筛选关键词" class="handle-input"></el-input>
      <el-date-picker
          class="datePicker"
          v-model="select_date"
          unlink-panels
          type="daterange"
          size="mini"
          value-format="yyyy-MM-dd"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
      </el-date-picker>
      <el-button type="primary" size="mini" class="add-button" @click="handleAdd" :disabled="this.loginStatus!=3">添加</el-button>
    </div>
    <el-table :data="data" border size="mini" style="width: 100%" height=450px ref="multipleTable">
      <el-table-column label="编号" prop="id" align="center" sortable ></el-table-column>
      <el-table-column label="用户编号" prop="customerId" align="center" sortable ></el-table-column>
      <el-table-column label="用户姓名" prop="customerName" align="center"></el-table-column>
      <el-table-column label="床位编号" prop="bedId" align="center" sortable></el-table-column>
      <el-table-column label="入住时间" prop="beginDate" align="center" :formatter="dateFormat"></el-table-column>
      <el-table-column label="退住时间" prop="leaveDate" align="center" :formatter="dateFormat"></el-table-column>
      <el-table-column label="操作"  align="center" width="250px">
        <template slot-scope="scope">
          <div class="optionButton">
            <el-button size="mini" class="optionButton" type="primary" @click="leaveRow(scope.row)">退住</el-button>
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

    <!--添加弹窗-->
    <el-dialog title="添加" :visible.sync="addVisible">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户编号" size="mini">
          <el-input v-model="form.customerId"></el-input>
        </el-form-item>
        <el-form-item label="床位编号" size="mini">
          <el-input v-model="form.bedId"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="addVisible = false">取 消</el-button>
        <el-button type="primary" size="mini" @click="saveAdd">确 定</el-button>
      </span>
    </el-dialog>

    <!--删除提示框-->
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
      <div class="del-dialog-cnt" align="center">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="delVisible = false">取 消</el-button>
        <el-button type="primary" size="mini" @click="deleteRow">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑提示框 -->
    <el-dialog title="编辑" :visible.sync="editVisible" width="400px">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="用户编号" size="mini">
          <el-input v-model="form.customerId"></el-input>
        </el-form-item>
        <el-form-item label="床位编号" size="mini">
          <el-input v-model="form.bedId"></el-input>
        </el-form-item>
        <el-form-item label="入住日期" size="mini">
          <el-date-picker
              v-model=form.beginDate
              type="date"
              placeholder="选择日期"
              value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="退住日期" size="mini">
          <el-date-picker
              v-model=form.leaveDate
              type="date"
              placeholder="选择日期"
              value-format="yyyy-MM-dd">
          </el-date-picker>
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
import {mixin, mixinDriectly} from "@/mixin";
import {mapGetters} from "vuex";
import {addLive, delLive, editLive, getLiveInfo, leave, searchCustomerInfo, searchLiveInfo} from "@/api";

export default {
  name: "live",
  mixins:[mixin,mixinDriectly],
  data(){
    return{
      select_date:'',
      form:{
        id:'',
        customerId:'',
        bedId:'',
        beginDate:'',
        leaveDate:''
      }
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
        this.tableData=res
        this.currentPage=1
      }).catch(err=>{
        console.log(err)
      })
    },
    search() {
      if ((this.select_word && this.select_word != '')||(this.select_date&&this.select_date!='')) {
        let params=new URLSearchParams()
        params.append("word",this.select_word)
        params.append("fromDate",this.select_date[0])
        params.append("toDate",this.select_date[1])
        searchLiveInfo(params).then(res => {
          this.tableData = res
          this.currentPage = 1
        }).catch(err => {
          console.log(err)
        })
      } else {
        this.getData()
      }
    },
    saveAdd(){
      this.saveAddMix(addLive)
    },
    deleteRow(){
      this.deleteRowMix(delLive)
    },
    saveEdit(){
      this.saveEditMix(editLive)
    },
    dateFormat(row,column){
      let data=row[column.property]
      if(data===null)return null;
      return data.split(" ")[0]
    },
    leaveRow(row){
      leave(row.id).then(res=>{
        if(res.code===1){
          this.notify("退住操作成功","success")
          this.getData()
        }else{
          this.notify("退住操作失败","error")
        }
      })
    }
  },
  mounted(){
    this.getData()
  }
}
</script>

<style scoped>
@import "../assets/css/commenTable.css";
.datePicker{
  float: left;
  margin: 10px;
}
</style>