<template>
  <div class="container">
    <div class="handle-box">
      <el-button type="primary" size="mini" class="search-button" @click="search">搜索</el-button>
      <el-input v-model="select_word" size="mini" placeholder="筛选关键词" class="handle-input"></el-input>
      <el-button type="primary" size="mini" class="add-button" @click="handleAdd" v-if="this.loginStatus>=2">添加
      </el-button>
    </div>
    <el-table :data="data" border size="mini" style="width: 100%" height=450px ref="multipleTable"
              @sort-change="sortChange" @filter-change="filterChange">
      <el-table-column label="编号" prop="id" align="center" sortable="custom"></el-table-column>
      <el-table-column label="名称" prop="name" align="center"></el-table-column>
      <el-table-column label="类型" prop="type" align="center"></el-table-column>
      <el-table-column label="标签" align="center" v-if="this.loginStatus>=2">
        <template slot-scope="scope">
          <el-tag v-for="item in scope.row.labelArray" class="tag">
            {{ item }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="价格" prop="price" align="center"></el-table-column>
      <el-table-column label="图片" align="center" width="250px">
        <template slot-scope="scope">
          <img :src="scope.row.picurl" width="200px" height="200px" v-show="scope.row.picurl!=null"/>
        </template>
      </el-table-column>
      <el-table-column label="清真" prop="muslim" align="center"
                       :filters="[{text:'是',value:'是'},{text:'否',value:'否'}]"
                       column-key="muslim"></el-table-column>
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
          :total="displayData.length">
      </el-pagination>
    </div>

    <!--添加弹窗-->
    <el-dialog title="添加" :visible.sync="addVisible">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="名称" size="mini">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="类型" size="mini">
          <el-input v-model="form.type"></el-input>
        </el-form-item>
        <el-form-item label="标签" size="mini">
          <el-input v-model="form.label"></el-input>
        </el-form-item>
        <el-form-item label="价格" size="mini">
          <el-input v-model="form.price"></el-input>
        </el-form-item>
        <el-form-item label="图片链接" size="mini">
          <el-input v-model="form.picurl"></el-input>
        </el-form-item>
        <el-form-item label="是否清真" size="mini">
          <el-input v-model="form.muslim"></el-input>
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
        <el-form-item label="名称" size="mini">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="类型" size="mini">
          <el-input v-model="form.type"></el-input>
        </el-form-item>
        <el-form-item label="标签" size="mini">
          <el-input v-model="form.label"></el-input>
        </el-form-item>
        <el-form-item label="价格" size="mini">
          <el-input v-model="form.price"></el-input>
        </el-form-item>
        <el-form-item label="图片链接" size="mini">
          <el-input v-model="form.picurl"></el-input>
        </el-form-item>
        <el-form-item label="是否清真" size="mini">
          <el-input v-model="form.muslim"></el-input>
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
import {addFood, delFood, editFood, getFoodInfo, getFoodInfoById, searchFoodInfo} from "@/api";

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
      let temp = this.displayData.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize)
      for(let item of temp){
        this.$set(item, "labelArray", item.label != null ? item.label.split("-") : null)
      }
      return temp
    }
  },
  methods: {
    getData() {
      this.tableData = []
      this.displayData = []
      getFoodInfo().then(res => {
        this.tableData = res
        this.displayData = this.tableData
        this.currentPage = 1
      }).catch(err => {
        console.log(err)
      })
    },
    getDataById(id) {
      this.tableData = []
      this.displayData = []
      getFoodInfoById(id).then(res => {
        this.$set(this.tableData, 0, res)
        this.displayData = this.tableData
        this.currentPage = 1
      }).catch(err => {
        console.log(err)
      })
    },
    search() {
      if (this.select_word && this.select_word != '') {
        searchFoodInfo(this.select_word).then(res => {
          this.tableData = res
          this.displayData = this.tableData
          this.currentPage = 1
        }).catch(err => {
          console.log(err)
        })
      } else {
        this.getData()
      }
    },
    saveAdd(){
      this.saveAddMix(addFood)
    },
    deleteRow(){
      this.deleteRowMix(delFood)
    },
    saveEdit(){
      this.saveEditMix(editFood)
    }
  }
}
</script>

<style scoped>
@import "../assets/css/commenTable.css";
.tag{
  margin: 1px;
}
</style>