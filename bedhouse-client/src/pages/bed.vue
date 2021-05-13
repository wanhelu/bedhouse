<template>
  <div class="container">
    <div class="handle-box">
      <el-button type="primary" size="mini" class="search-button" @click="search">搜索</el-button>
      <el-input v-model="select_word" size="mini" placeholder="筛选关键词" class="handle-input"></el-input>
      <el-button type="primary" size="mini" class="add-button" @click="handleAdd" v-if="this.loginStatus==3">添加
      </el-button>
    </div>
    <el-table :data="data" border size="mini" style="width: 100%" height=450px ref="multipleTable"
              @sort-change="sortChange" @filter-change="filterChange">
      <el-table-column label="编号" prop="id" align="center" width="100px" sortable="custom"></el-table-column>
      <el-table-column label="房间号" prop="roomId" align="center" width="100px" sortable="custom"></el-table-column>
      <el-table-column label="是否占用" prop="used" align="center" width="100px"
                       :filters="[{text:'是',value:'是'},{text:'否',value:'否'}]"
                       column-key="used"></el-table-column>
      <el-table-column label="详情" prop="detail" align="center"></el-table-column>
      <el-table-column label="操作" align="center" width="100px" v-if="this.loginStatus==3">
        <template slot-scope="scope">
          <div class="optionButton">
            <el-button size="mini" class="optionButton" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="mini" class="optionButton" style="margin: 2px" type="danger"
                       @click="handleDelete(scope.row.id)">删除
            </el-button>
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
          :total="displayData.length">
      </el-pagination>
    </div>

    <!--添加弹窗-->
    <el-dialog title="添加" :visible.sync="addVisible">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="房间号" size="mini">
          <el-input v-model="form.roomId"></el-input>
        </el-form-item>
        <el-form-item label="详情" size="mini">
          <el-input v-model="form.detail" type="textarea" :rows="5"></el-input>
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
    <el-dialog title="编辑" :visible.sync="editVisible">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="房间号" size="mini">
          <el-input v-model="form.roomId"></el-input>
        </el-form-item>
        <el-form-item label="详情" size="mini">
          <el-input v-model="form.detail" type="textarea" :rows="5"></el-input>
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
import {getBedInfo, getBedUsedInfo, searchBedInfo, addBed, editBed, delBed, getBedInfoById} from "@/api";
import {mixin, mixinDriectly} from '../mixin'

export default {
  name: "bed",
  mixins: [mixin,mixinDriectly],
  data(){
    return{
      form:{
        id:'',
        roomId:'',
        detail:''
      }
    }
  },
  computed:{
    ...mapGetters([
      'loginStatus'
    ]),
    data(){
      return this.displayData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },
  methods:{
    search(){
      if(this.select_word && this.select_word != ''){
        searchBedInfo(this.select_word).then((res=>{
          this.tableData=res
          this.getUsed()
          this.displayData = this.tableData
          this.currentPage = 1
        })).catch(err=>{
          console.log(err)
        })
      }else{
        this.getData()
      }
    },
    deleteRow(){
      this.deleteRowMix(delBed)
    },
    saveEdit(){
      this.saveEditMix(editBed)
    },
    saveAdd(){
      this.saveAddMix(addBed)
    },
    getData() {
      this.tableData = []
      this.displayData = []
      getBedInfo().then(res => {
        this.tableData = res
        this.getUsed()
        this.displayData = this.tableData
        this.currentPage = 1
      }).catch(err => {
        console.log(err)
      })
    },
    getDataById(id) {
      this.tableData = []
      this.displayData = []
      getBedInfoById(id).then(res => {
        this.$set(this.tableData, 0, res)
        this.getUsed()
        this.displayData = this.tableData
        this.currentPage = 1
      }).catch(err => {
        console.log(err)
      })
    },
    getUsed() {
      let i = 0
      for (let item of this.tableData) {
        let ii = i++
        getBedUsedInfo(item.id).then(res => {
          if (res.code === 1) {
            if (res.used >= 1) {
              this.$set(this.tableData[ii], "used", "是")
            } else {
              this.$set(this.tableData[ii],"used","否")
            }
          }
        })
      }
    }
  }
}
</script>

<style scoped>
@import "../assets/css/commenTable.css";
</style>