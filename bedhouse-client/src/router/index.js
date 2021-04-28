const router = {
  routes: [
    {
      path: '/Home',
      component: () => import('@/pages/Home.vue'),
      meta: { title: '自述文件' },
    },
    {
      path: '/About',
      component: ()=>import('@/pages/About')
    },
    {
      path: '/',
      component:()=>import('@/pages/login')
    }
  ]
}

export default router