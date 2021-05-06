<template>
  <div>
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" size="mini" class="search-button" @click="search">搜索</el-button>
        <el-input v-model="select_word" size="mini" placeholder="筛选关键词" class="handle-input"></el-input>
        <el-button type="primary" size="mini" class="add-button" @click="handleAdd" :disabled="this.loginStatus!=3">
          添加
        </el-button>
      </div>
      <el-table :data="data" border size="mini" style="width: 100%" height=450px ref="multipleTable">
        <el-table-column label="编号" prop="id" sortable align="center"></el-table-column>
        <el-table-column label="姓名" prop="name" align="center"></el-table-column>
        <el-table-column label="性别" prop="gender" align="center"></el-table-column>
        <el-table-column label="年龄" prop="age" sortable align="center"></el-table-column>
        <el-table-column label="入职日期" prop="entryTime" align="center"></el-table-column>
        <el-table-column label="电话号" prop="phone" align="center"></el-table-column>
        <el-table-column label="登录名" prop="loginName" align="center"></el-table-column>
        <el-table-column label="密码" prop="password" align="center"></el-table-column>
        <el-table-column label="上次登录时间" prop="lastTime" align="center"></el-table-column>
        <el-table-column label="上次登录地址" prop="lastAddress" align="center"></el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <div class="optionButton">
              <el-button size="mini" class="optionButton" @click="handleEdit(scope.row)">编辑</el-button>
              <el-button size="mini" class="optionButton" type="danger" @click="handleDelete(scope.row.id)">删除
              </el-button>
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
          <el-form-item label="姓名" size="mini">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="性别" size="mini">
            <el-input v-model="form.gender"></el-input>
          </el-form-item>
          <el-form-item label="年龄" size="mini">
            <el-input v-model="form.age"></el-input>
          </el-form-item>
          <el-form-item label="电话号" size="mini">
            <el-input v-model="form.phone"></el-input>
          </el-form-item>
          <el-form-item label="入职日期" size="mini">
            <el-date-picker
                v-model=form.entryTime
                type="date"
                placeholder="选择日期"
                value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="登录名" size="mini">
            <el-input v-model="form.loginName"></el-input>
          </el-form-item>
          <el-form-item label="密码" size="mini">
            <el-input v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item label="权限" size="mini">
            <el-input v-model="form.roleId"></el-input>
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
          <el-form-item label="性别" size="mini">
            <el-input v-model="form.gender"></el-input>
          </el-form-item>
          <el-form-item label="年龄" size="mini">
            <el-input v-model="form.age"></el-input>
          </el-form-item>
          <el-form-item label="电话号" size="mini">
            <el-input v-model="form.phone"></el-input>
          </el-form-item>
          <el-form-item label="入职日期" size="mini">
            <el-date-picker
                v-model=form.entryTime
                type="date"
                placeholder="选择日期"
                value-format="yyyy-MM-dd">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="登录名" size="mini">
            <el-input v-model="form.loginName"></el-input>
          </el-form-item>
          <el-form-item label="密码" size="mini">
            <el-input v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item label="权限" size="mini">
            <el-input v-model="form.roleId"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button size="mini" @click="editVisible = false">取 消</el-button>
          <el-button type="primary" size="mini" @click="saveEdit">确 定</el-button>
        </span>
      </el-dialog>

    </div>
  </div>
</template>

<script>
import {getStfInfoLessRoleId, searchStfInfo, addStf, delStf, editStf} from "@/api";
import {mapGetters} from "vuex"
import {mixin} from '../mixin'

export default {
  name: "Stf",
  mixins: [mixin],
  data() {
    return {
      select_word: '',
      tableData: [],
      currentPage: 1,
      pageSize: 5,
      addVisible: false,
      delVisible: false,
      editVisible: false,
      delId: '',
      form: {
        id: '',
        name: '',
        gender: '',
        age: '',
        loginName: '',
        password: '',
        phone: '',
        entryTime: '',
        roleId: ''
      },
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
  mounted() {
    this.getData()
  },
  methods: {
    search() {
      if (this.select_word && this.select_word != '') {
        searchStfInfo(this.select_word, this.loginStatus).then(res => {
          this.tableData = res
          this.currentPage=1
        }).catch(err => {
          console.log(err)
        })
      } else {
        this.getData()
      }
    },
    cleanForm() {
      this.form = {
        id: '',
        name: '',
        gender: '',
        age: '',
        loginName: '',
        password: '',
        phone: '',
        entryTime: '',
        roleId: ''
      }
    },
    handleEdit(row) {
      this.form = {
        id: row.id,
        name: row.name,
        gender: row.gender,
        age: row.age,
        loginName: row.loginName,
        password: row.password,
        phone: row.phone,
        entryTime: row.entryTime,
        roleId: row.roleId
      }
      this.editVisible = true
    },
    handleDelete(rowId) {
      this.delId = rowId
      this.delVisible = true
    },
    handleAdd() {
      this.cleanForm()
      this.addVisible = true
    },
    deleteRow() {
      this.delVisible = false
      delStf(this.delId).then(res => {
        if(res.code===1){
          this.getData()
          this.notify("删除成功","success")
        }else{
          this.notify("删除失败","error")
        }
      }).catch(err => {
        console.log(err)
      })
    },
    getData() {
      this.tableData = []
      getStfInfoLessRoleId(this.loginStatus).then(res => {
        this.tableData = res
        this.currentPage = 1
      }).catch(err => {
        console.log(err)
      })
    },
    handleCurrentChange(val) {
      this.currentPage = val
    },
    saveAdd() {
      let params = new URLSearchParams()
      params.append("name", this.form.name)
      params.append("gender", this.form.gender)
      params.append("age", this.form.age)
      params.append("loginName", this.form.loginName)
      params.append("password", this.form.password)
      params.append("phone", this.form.phone)
      params.append("entryTime", this.form.entryTime)
      params.append("roleId", this.form.roleId)

      addStf(params).then(res => {
        if (res.code) {
          this.notify("添加成功", "success")
          this.addVisible = false
        } else {
          this.notify("添加失败", "error")
          console.log(res.msg)
          this.addVisible = false
        }
      }).catch(err => {
        console.log(err)
      })
      this.getData();
    },
    saveEdit() {
      let params = new URLSearchParams()
      params.append("id", this.form.id)
      params.append("name", this.form.name)
      params.append("gender", this.form.gender)
      params.append("age", this.form.age)
      params.append("loginName", this.form.loginName)
      params.append("password", this.form.password)
      params.append("phone", this.form.phone)
      params.append("entryTime", this.form.entryTime)
      params.append("roleId", this.form.roleId)

      editStf(params).then(res => {
        if (res.code === 1) {
          this.getData()
          this.editVisible = false
          this.notify("修改成功", "success")
        } else {
          this.getData()
          this.editVisible = false
          this.notify("修改失败", "error")
          console.log(res)
        }
      }).catch(err => {
        console.log(err)
      })
    }
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

.search-button {
  float: left;
  margin: 10px;
}

.add-button {
  margin: 10px;
  float: right;
}

.optionButton {
  margin: 2px;
}

.pagination {
  display: flex;
  justify-content: center;
  float: bottom;
}
</style>