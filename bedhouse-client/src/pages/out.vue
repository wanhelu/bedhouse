<template>
  <div class="container">
    <div class="handle-box">
      <el-button type="primary" size="mini" class="search-button" @click="search">搜索</el-button>
      <el-input v-model="select_word" size="mini" placeholder="筛选关键词" class="handle-input"></el-input>
      <el-date-picker
          class="datePicker"
          size="mini"
          v-model="select_date"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择日期">
      </el-date-picker>
      <el-button type="primary" size="mini" class="add-button" @click="handleAdd">添加
      </el-button>
    </div>
    <el-table :data="data" border size="mini" style="width: 100%" height=450px ref="multipleTable"
              @sort-change="sortChange">
      <el-table-column label="编号" prop="id" align="center" width="60px" sortable="custom"></el-table-column>
      <el-table-column label="提交人员编号" prop="stfId" align="center" width="70px" sortable="custom">
        <template slot-scope="scope">
          <popover-container :text="scope.row.stfId" :id="scope.row.stfId" :type="1"></popover-container>
        </template>
      </el-table-column>
      <el-table-column label="外出人员编号" prop="customerId" align="center" width="70px" sortable="custom">
        <template slot-scope="scope">
          <popover-container :text="scope.row.customerId" :id="scope.row.customerId" :type="4"></popover-container>
        </template>
      </el-table-column>
      <el-table-column label="提交时间" prop="submitTime" align="center" sortable="customOfDate"></el-table-column>
      <el-table-column label="外出时间" prop="outTime" align="center" sortable="customOfDate"></el-table-column>
      <el-table-column label="预计归来时间" prop="forcastBac" align="center" sortable="customOfDate"></el-table-column>
      <el-table-column label="实际归来时间" prop="backTime" align="center" sortable="customOfDate"></el-table-column>
      <el-table-column label="备注" prop="text" align="center"></el-table-column>
      <el-table-column label="状态" prop="stateString" align="center"
                       :filters="[{text:'未审核',value:1},{text:'审核不通过',value:2},{text:'审核通过-未外出',value:3},{text:'审核通过-已外出',value:4},{text:'审核通过-已归来',value:5}]"
                       :filter-method="filterHandler"></el-table-column>
      <el-table-column label="审核员编号" prop="checkerId" align="center" width="70px" sortable="custom">
        <template slot-scope="scope">
          <popover-container :text="scope.row.checkerId" :id="scope.row.checkerId" :type="1"></popover-container>
        </template>
      </el-table-column>
      <el-table-column label="审核时间" prop="checkTime" align="center" sortable="customOfDate"></el-table-column>
      <el-table-column label="操作" align="center" width="200px">
        <template slot-scope="scope">
          <el-button size="mini" class="optionButton" type="primary" @click="handleBack(scope.row)"
                     v-if="scope.row.state==4">归来
          </el-button>
          <el-button size="mini" class="optionButton" type="primary" @click="handleOut(scope.row)"
                     v-if="scope.row.state==3">外出
          </el-button>
          <el-button size="mini" class="optionButton" @click="handleEdit(scope.row)" v-if="loginStatus>=2">编辑
          </el-button>
          <el-button size="mini" class="optionButton" type="danger" @click="handleDelete(scope.row.id)"
                       v-if="loginStatus>=2">删除
            </el-button>
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
      <el-form ref="form" :model="form" label-width="100px">
        <el-form-item label="外出客户编号" size="mini">
          <span slot="label">
            <popover-icon-container :type="4" :id="form.customerId" style="float: right"></popover-icon-container>
            <p>客户编号</p>
          </span>
          <el-input v-model="form.customerId"></el-input>
        </el-form-item>
        <el-form-item label="外出时间" size="mini">
          <el-date-picker
              v-model=form.outTime
              type="datetime"
              placeholder="选择日期"
              style="float: left"
              value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预计归来时间" size="mini">
          <el-date-picker
              v-model=form.forcastBac
              type="datetime"
              placeholder="选择日期"
              style="float: left"
              value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" size="mini">
          <el-input v-model="form.text" type="textarea" :rows="2"></el-input>
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
      <el-form ref="form" :model="form" label-width="100px">
        <el-form-item label="提交人员编号" size="mini">
          <span slot="label">
            <popover-icon-container :type="1" :id="form.stfId" style="float: right"></popover-icon-container>
            <p>员工编号</p>
          </span>
          <el-input v-model="form.stfId"></el-input>
        </el-form-item>
        <el-form-item label="外出客户编号" size="mini">
          <span slot="label">
            <popover-icon-container :type="4" :id="form.customerId" style="float: right"></popover-icon-container>
            <p>客户编号</p>
          </span>
          <el-input v-model="form.customerId"></el-input>
        </el-form-item>
        <el-form-item label="提交时间" size="mini">
          <el-date-picker
              v-model=form.submitTime
              type="datetime"
              placeholder="选择日期"
              style="float: left"
              value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="外出时间" size="mini">
          <el-date-picker
              v-model=form.outTime
              type="datetime"
              placeholder="选择日期"
              style="float: left"
              value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预计归来时间" size="mini">
          <el-date-picker
              v-model=form.forcastBac
              type="datetime"
              placeholder="选择日期"
              style="float: left"
              value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="归来时间" size="mini">
          <el-date-picker
              v-model=form.backTime
              type="datetime"
              placeholder="选择日期"
              style="float: left"
              value-format="yyyy-MM-dd HH:mm:ss">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" size="mini">
          <el-input v-model="form.text" type="textarea" :rows="2"></el-input>
        </el-form-item>
        <el-form-item label="状态" size="mini">
          <el-input v-model="form.state"></el-input>
        </el-form-item>
        <el-form-item label="审核员编号" size="mini">
          <span slot="label">
            <popover-icon-container :type="1" :id="form.checkerId" style="float: right"></popover-icon-container>
            <p>审核员编号</p>
          </span>
          <el-input v-model="form.checkerId"></el-input>
        </el-form-item>
        <el-form-item label="审核时间" size="mini">
          <el-date-picker
              v-model=form.checkTime
              type="datetime"
              placeholder="选择日期"
              style="float: left"
              value-format="yyyy-MM-dd HH:mm:ss">
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
import {addOut, delOut, editOut, getOutInfo, goBack, goOut, searchOutInfo} from "@/api";
import popoverContainer from "@/components/popoverContainer";
import popoverIconContainer from "@/components/popoverIconContainer";

export default {
  name: "out",
  mixins: [mixin, mixinDriectly],
  components: {
    popoverContainer,
    popoverIconContainer
  },
  data() {
    return {
      select_date: '',
      form: {
        id: '',
        stfId: '',
        customerId: '',
        submitTime: '',
        outTime: '',
        forcastBac: '',
        backTime: '',
        text: '',
        state: '',
        stateString: '',
        checkerId: '',
        checkTime: ''
      }
    }
  },
  computed: {
    ...mapGetters([
      'loginStatus',
      'id'
    ]),
    data() {
      let temp = this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
      for (let item of temp) {
        this.$set(item, "stateString", this.tranState(item.state))
      }
      return temp
    }
  },
  methods: {
    getData() {
      this.tableData = []
      getOutInfo().then(res => {
        this.tableData = res
        this.currentPage = 1
      }).catch(err => {
        console.log(err)
      })
    },
    search() {
      if ((this.select_word && this.select_word != '') || (this.select_date && this.select_date != '')) {
        searchOutInfo(this.select_word, this.select_date).then(res => {
          this.tableData = res
          this.currentPage = 1
        }).catch(err => {
          console.log(err)
        })
      } else {
        this.getData()
      }
    },
    saveAdd() {
      this.form.stfId = this.id
      this.form.state = 1
      this.saveAddMix(addOut)
    },
    deleteRow() {
      this.deleteRowMix(delOut)
    },
    saveEdit() {
      this.saveEditMix(editOut)
    },
    tranState(id) {
      if (id == 1) return "未审核"
      else if (id == 2) return "审核不通过"
      else if (id == 3) return "审核通过-未外出"
      else if (id == 4) return "审核通过-已外出"
      else if (id == 5) return "审核通过-已归来"
      else return null
    },
    filterHandler(value, row, column) {
      if (row.state == value) return true
      else return false
    },
    handleOut(row) {
      goOut(row.id).then(res => {
        if (res.code == 1) {
          this.notify("操作成功", "success")
          this.getData()
        } else {
          this.notify("操作失败", "error")
        }
      }).catch(err => {
        console.log(err)
      })
    },
    handleBack(row) {
      goBack(row.id).then(res => {
        if (res.code == 1) {
          this.notify("操作成功", "success")
          this.getData()
        } else {
          this.notify("操作失败", "error")
        }
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style scoped>
@import "../assets/css/commenTable.css";

.datePicker {
  float: left;
  margin: 10px;
}
</style>