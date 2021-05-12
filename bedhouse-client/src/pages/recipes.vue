<template>
  <div class="container">
    <div class="handle-box">
      <el-button type="primary" size="mini" class="search-button" @click="search">搜索</el-button>
      <el-input v-model="select_word" size="mini" placeholder="筛选关键词" class="handle-input"></el-input>
      <el-button type="primary" size="mini" class="add-button" @click="handleAdd">添加
      </el-button>
    </div>
    <el-table :data="data" border size="mini" style="width: 100%" height=450px ref="multipleTable">
      <el-table-column label="编号" prop="id" align="center"></el-table-column>
      <el-table-column label="客户编号" prop="customerId" align="center">
        <template slot-scope="scope">
          <popover-container :text="scope.row.customerId" :id="scope.row.customerId" :type="4"></popover-container>
        </template>
      </el-table-column>
      <el-table-column label="食品项编号" prop="foodId" align="center">
        <template slot-scope="scope">
          <popover-container :text="scope.row.foodId" :id="scope.row.foodId" :type="3"></popover-container>
        </template>
      </el-table-column>
      <el-table-column label="类型" prop="type" align="center"></el-table-column>
      <el-table-column label="提供日期" prop="provideDat" align="center" :formatter="dateFormat"></el-table-column>
      <el-table-column label="提供星期" prop="week" align="center"></el-table-column>
      <el-table-column label="操作" align="center">
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

    <!--添加弹窗-->
    <el-dialog title="添加" :visible.sync="addVisible">
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="客户编号" size="mini">
          <el-input v-model="form.customerId"></el-input>
        </el-form-item>
        <el-form-item label="食品编号" size="mini">
          <el-input v-model="form.foodId"></el-input>
        </el-form-item>
        <el-form-item label="类型(早、中、晚)" size="mini">
          <el-input v-model="form.type"></el-input>
        </el-form-item>
        <el-form-item label="提供日期" size="mini">
          <el-date-picker
              v-model=form.provideDat
              type="date"
              placeholder="选择日期"
              value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="提供星期" size="mini">
          <el-input v-model="form.week"></el-input>
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
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="客户编号" size="mini">
          <el-input v-model="form.customerId"></el-input>
        </el-form-item>
        <el-form-item label="食品编号" size="mini">
          <el-input v-model="form.foodId"></el-input>
        </el-form-item>
        <el-form-item label="类型(早、中、晚)" size="mini">
          <el-input v-model="form.type"></el-input>
        </el-form-item>
        <el-form-item label="提供日期" size="mini">
          <el-date-picker
              v-model=form.provideDat
              type="date"
              placeholder="选择日期"
              value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="提供星期" size="mini">
          <el-input v-model="form.week"></el-input>
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
import {addRecipes, delRecipes, editRecipes, getRecipesInfo, searchRecipesInfo} from "@/api";
import popoverContainer from "@/components/popoverContainer";

export default {
  name: "recipes",
  mixins: [mixin, mixinDriectly],
  components: {
    popoverContainer
  },
  data() {
    return {
      form: {
        id: '',
        customerId: '',
        foodId: '',
        type: '',
        provideDat: '',
        week: ''
      }
    }
  },
  computed: {
    ...mapGetters([
      'loginStatus'
    ]),
    data() {
      return this.tableData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
    }
  },
  methods: {
    getData() {
      this.tableData = []
      getRecipesInfo().then(res => {
        this.tableData = res
        this.currentPage = 1
      }).catch(err => {
        console.log(err)
      })
    },
    search() {
      if (this.select_word && this.select_word != '') {
        searchRecipesInfo(this.select_word).then(res => {
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
      this.saveAddMix(addRecipes)
    },
    deleteRow() {
      this.deleteRowMix(delRecipes)
    },
    saveEdit() {
      this.saveEditMix(editRecipes)
    }
  },
  mounted() {
    this.getData()
  }
}
</script>

<style scoped>
@import "../assets/css/commenTable.css";
</style>