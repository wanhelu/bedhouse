<template>
  <div class="container">
    <div class="handle-box">
      <el-button type="primary" size="mini" class="search-button" @click="search">搜索</el-button>
      <el-input v-model="select_word" size="mini" placeholder="筛选关键词" class="handle-input"></el-input>
      <el-button type="primary" size="mini" class="add-button" @click="handleAdd" v-if="this.loginStatus>=2">添加
      </el-button>
    </div>
    <el-table :data="data" border size="mini" style="width: 100%" height=450px ref="multipleTable">
      <el-table-column label="编号" prop="id" align="center"></el-table-column>
      <el-table-column label="姓名" prop="name" align="center"></el-table-column>
      <el-table-column label="性别" prop="gender" align="center"
                       :filters="[{text:'男',value:'男'},{text:'女',value:'女'}]"
                       :filter-method="filterHandlerSimple"></el-table-column>
      <el-table-column label="年龄" prop="age" align="center"></el-table-column>
      <el-table-column label="床位" prop="bedId" align="center">
        <template slot-scope="scope">
          <popover-container :text="scope.row.bedId" :id="scope.row.bedId" :type="2"></popover-container>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" v-if="this.loginStatus>=2">
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

    <!--添加弹窗-->
    <el-dialog title="添加" :visible.sync="addVisible">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="姓名" size="mini">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="性别" size="mini">
          <el-input v-model="form.gender"></el-input>
        </el-form-item>
        <el-form-item label="年龄" size="mini">
          <el-input v-model="form.age"></el-input>
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
        <el-form-item label="姓名" size="mini">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="年龄" size="mini">
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="性别" size="mini">
          <el-input v-model="form.gender"></el-input>
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
import {mixin, mixinDriectly} from "@/mixin";
import {
  getCustomerInfo,
  getUseBed,
  searchCustomerInfo,
  addCustomer,
  delCustomer,
  editCustomer,
  getCustomerInfoById
} from "@/api"
import popoverContainer from "@/components/popoverContainer";

export default {
  name: "customer",
  mixins: [mixin, mixinDriectly],
  components: {
    popoverContainer
  },
  data() {
    return {
      form: {
        id: '',
        name: '',
        gender: '',
        age: ''
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
    getData() {
      this.tableData = []
      getCustomerInfo().then(res => {
        this.tableData = res
        this.getUse()
        this.currentPage = 1
      }).catch(err => {
        console.log(err)
      })
    },
    getDataById(id) {
      this.tableData = []
      getCustomerInfoById(id).then(res => {
        this.$set(this.tableData, 0, res)
        this.getUse()
        this.currentPage = 1
      }).catch(err => {
        console.log(err)
      })
    },
    search() {
      if (this.select_word && this.select_word != '') {
        searchCustomerInfo(this.select_word).then(res => {
          this.tableData = res
          this.getUse()
          this.currentPage = 1
        }).catch(err => {
          console.log(err)
        })
      } else {
        this.getData()
      }
    },
    getUse(){
      let i=0
      for(let item of this.tableData){
        let ii=i++
        getUseBed(item.id).then(res => {
          if (res.code === 1) {
            this.$set(this.tableData[ii], "bedId", res.bedId)
          } else {
            this.$set(this.tableData[ii], "bedId", null)
          }
        }).catch(err => {
          console.log(err)
        })
      }
    },
    saveAdd(){
      this.saveAddMix(addCustomer)
    },
    deleteRow(){
      this.deleteRowMix(delCustomer)
    },
    saveEdit(){
      this.saveEditMix(editCustomer)
    },
    filterHandlerBed(value, row, column){
      const property = column['property'];
      return (value ^ row[property]==null)
    }
  }
}
</script>

<style scoped>
@import "../assets/css/commenTable.css";
</style>