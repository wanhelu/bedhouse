<template>
  <div class="about">
    <h1>This is an about page</h1>
    <h1>{{testData}}</h1>
  </div>
</template>
<script>
import {getTestData} from "@/api";

export default {
  name:'About',
  data(){
    return{
      testData:''
    }
  },
  mounted() {
    getTestData().then((res)=>{
      this.testData=res.msg
    }).catch((err)=>{
      console.log(err)
    })
  }
}
</script>
