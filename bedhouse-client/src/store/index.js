const store = {
  state: {
    loginStatus:0,
    name:''
  },
  getters: {
    loginStatus: state=> state.loginStatus,
    name:state=>state.name
  },
  mutations: {
    setLoginStatus:(state,loginStatus)=>{state.loginStatus=loginStatus},
    setName:(state,name)=>{state.name=name}
  }
}

export default store