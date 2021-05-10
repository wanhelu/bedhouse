import {addCustomer, delCustomer, editCustomer} from "@/api";

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
        }else{
          this.getData()
          this.editVisible=false
            this.notify("修改失败", "error")
            console.log(res)
        }
      }).catch(err => {
          console.log(err)
      })
    },
      filterHandlerSimple(value, row, column) {
          const property = column['property'];
          return row[property] === value;
      },
      dateFormat(row, column) {
          let data = row[column.property]
          if (data === null) return null;
          return data.split(" ")[0]
      },
  },
  data(){
    return{
      select_word:'',
      tableData:[],
      currentPage: 1,
      pageSize:5,
      delVisible:false,
      editVisible:false,
      addVisible:false,
      delId:'',
      query:''
    }
  },
  mounted() {
    this.query=this.$route.query
  }
}
