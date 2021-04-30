const store = {
  state: {
    loginStatus:0,
    name:''
  },
  getters: {
    loginStatus: state=> {
      let loginStatus=state.loginStatus
      if(!loginStatus){
        loginStatus=JSON.parse(window.localStorage.getItem('loginStatus') || null)
      }
      return loginStatus
    },
    name:state=> {
      let name=state.name
      if(!name){
        name=JSON.parse(window.localStorage.getItem('name') || null)
      }
      return name
    }
  },
  mutations: {
    setLoginStatus:(state,loginStatus)=>{
      state.loginStatus=loginStatus
      window.localStorage.setItem("loginStatus",loginStatus)
    },
    setName:(state,name)=>{
      state.name=name
      window.localStorage.setItem("name",name)
    }
  }
}

export default store