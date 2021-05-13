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
    </div>
    <el-table :data="data" border size="mini" style="width: 100%" height=450px ref="multipleTable"
              @sort-change="sortChange">
      <el-table-column label="编号" prop="id" align="center" width="70px" sortable="custom"></el-table-column>
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
      <el-table-column label="操作" align="center" width="250px">
        <template slot-scope="scope">
          <el-button size="mini" class="optionButton" type="primary" @click="handleAccept(scope.row)">通过</el-button>
          <el-button size="mini" class="optionButton" type="primary" @click="handleRefuse(scope.row)">拒绝</el-button>
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
import {mixin, mixinDriectly} from "@/mixin";
import {mapGetters} from "vuex";
import {checkUpd, getOutInfoNoChecked, searchOutInfoNoChecked} from "@/api";
import popoverContainer from "@/components/popoverContainer";

export default {
  name: "check",
  mixins: [mixin, mixinDriectly],
  components: {
    popoverContainer
  },
  data() {
    return {
      select_date: '',
      form: {
        id: '',
        state: '',
        checkerId: ''
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
      getOutInfoNoChecked().then(res => {
        this.tableData = res
        this.currentPage = 1
      }).catch(err => {
        console.log(err)
      })
    },
    search() {
      if ((this.select_word && this.select_word != '') || (this.select_date && this.select_date != '')) {
        searchOutInfoNoChecked(this.select_word, this.select_date).then(res => {
          this.tableData = res
          this.currentPage = 1
        }).catch(err => {
          console.log(err)
        })
      } else {
        this.getData()
      }
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
    handleAccept(row) {
      this.form.id = row.id
      this.form.state = 3
      this.form.checkerId = this.id
      let params = this.paramsGenerator(this.form)
      checkUpd(params).then(res => {
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
    handleRefuse(row) {
      this.form.id = row.id
      this.form.state = 2
      this.form.checkerId = this.id
      let params = this.paramsGenerator(this.form)
      checkUpd(params).then(res => {
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