export const mixin = {
  methods: {
    // 提示信息
    notify(title, type) {
      this.$notify({
        title: title,
        type: type
      })
    },
    paramsGenerator(obj){
      let property=Object.getOwnPropertyNames(obj)
      let params=new URLSearchParams()
      for(let i of property){
        params.append(i,obj[i])
      }
      return params
    },
  }
}

export const mixinDriectly={
  methods: {
    handleAdd(){
      this.cleanForm()
      this.addVisible=true
    },
    handleDelete(rowId){
      this.delId=rowId
      this.delVisible=true
    },
    handleCurrentChange(val){
      this.currentPage=val
    },
    handleEdit(row){
      let property=Object.getOwnPropertyNames(this.form)
      for (let i of property){
        this.form[i]=row[i]
      }
      this.editVisible=true
    },
    cleanForm(){
      let property=Object.getOwnPropertyNames(this.form)
      for (let i of property){
        this.form[i]=''
      }
    },
    saveAddMix(func){
      let params=this.paramsGenerator(this.form)
      func(params).then(res=>{
        if(res.code===1){
          this.notify("添加成功", "success")
          this.addVisible = false
          this.getData()
        }else{
          this.notify("添加失败", "error")
          console.log(res.msg)
          console.log(res.code)
          this.addVisible = false
        }
      }).catch(err=>{
        console.log(err)
      })
    },
    deleteRowMix(func){
      this.delVisible=false
      func(this.delId).then(res=>{
        if(res.code===1){
          this.getData()
          this.notify("删除成功","success")
        }else{
          this.notify("删除失败","error")
        }
      })
    },
    saveEditMix(func){
      let params=this.paramsGenerator(this.form)

      func(params).then(res=>{
        if(res.code===1){
          this.getData()
          this.editVisible=false
          this.notify("修改成功","success")
        } else {
          this.getData()
          this.editVisible = false
          this.notify("修改失败", "error")
          console.log(res)
        }
      }).catch(err => {
        console.log(err)
      })
    },
    filterChange(filters) {
      let propertyName = Object.getOwnPropertyNames(filters)[0]
      let array = filters[propertyName]
      this.displayData = this.tableData.filter((item, index, arr) => {
        return array.indexOf(item[propertyName]) >= 0
      })
    },
    dateFormat(row, column) {
      let data = row[column.property]
      if (data === null) return null;
      return data.split(" ")[0]
    },
    sortChange({column, prop, order}) {
      if (column.sortable == "custom")
        this.sortByNum(prop, order)
      else if (column.sortable == "customOfDate")
        this.sortByDate(prop, order)
    },
    sortByNum(prop, order) {
      let orderB = order == "ascending" ? 1 : -1
      this.tableData.sort(function (a, b) {
        return (a[prop] - b[prop]) * orderB
      })
    },
    sortByDate(prop, order) {
      let orderB = order == "ascending" ? 1 : -1
      this.tableData.sort(function (a, b) {
        return (Date.parse(a[prop]) - Date.parse(b[prop])) * orderB
      })
    }
  },
  data() {
    return {
      select_word: '',
      tableData: [],
      displayData: [],
      currentPage: 1,
      pageSize: 5,
      delVisible: false,
      editVisible: false,
      addVisible: false,
      delId: '',
      paramsId: ''
    }
  },
  mounted() {
    this.paramsId = this.$route.params.id
    if (this.paramsId == undefined)
      this.getData();
    else
      this.getDataById(this.paramsId)
  }
}

export const popMixin = {
  props: {
    id: ''
  },
  data() {
    return {
      tableData: "",
      show: false
    }
  },
  watch: {
    id: function (newQuestion, oldQuestion) {
      this.getData()
    }
  },
  mounted() {
    this.getData()
  },
  methods: {
    getData() {
        if (this.id != null && this.id != '')
            this.func(this.id).then(res => {
                this.tableData = res
                this.show = true
            }).catch(err => {
                console.log(err)
            })
    },
    click() {
      this.$router.push({name: this.routerName, params: {id: this.tableData.id}})
    }
  }
}