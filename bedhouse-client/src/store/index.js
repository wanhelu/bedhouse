const store = {
  state: {
    loginStatus:0,//登录状态及权限
    name:'',//用户名
    id:'',//用户id
  },
  getters: {
    loginStatus: state=> {
      let loginStatus=state.loginStatus
      if(!loginStatus){
        loginStatus=window.sessionStorage.getItem('loginStatus')
        if(loginStatus==null)loginStatus=0
      }
      return loginStatus
    },
    name:state=> {
      let name=state.name
      if(!name){
        name=window.sessionStorage.getItem('name')
      }
      return name
    },
    id:state=>{
      let id=state.id
      if(!id){
        id=window.sessionStorage.getItem('id')
      }
      return id
    }
  },
  mutations: {
    setLoginStatus:(state,loginStatus)=>{
      state.loginStatus=loginStatus
      window.sessionStorage.setItem("loginStatus",loginStatus)
    },
    setName:(state,name)=>{
      state.name=name
      window.sessionStorage.setItem("name",name)
    },
    setId:(state,id)=>{
      state.id=id
      window.sessionStorage.setItem("id",id)
    }
  }
}

export default store