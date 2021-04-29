import VueRouter from 'vue-router'

const router = new VueRouter({
  routes: [
    {
      path: '/Home',
      component:()=>import('@/components/Home'),
      children: [{
        path:"/stf",
        component:()=>import('@/pages/Stf')
      }]
    },
    {
      path: '/',
      component:()=>import('@/pages/login')
    }
  ]
})

router.beforeEach((to, from, next) => {
  console.log("123321")
  next()
})

export default router