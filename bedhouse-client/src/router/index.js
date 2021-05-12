import VueRouter from 'vue-router'
import store from '../store'

const router = new VueRouter({
    routes: [
        {
            path: '/Home',
            name: 'home',
            component: () => import('@/components/Home'),
            children: [
                {
                    path: "/stf",
                    name: 'stf',
                    component: () => import('@/pages/Stf')
                },
                {
                  path:"/info",
                  name:'info',
                  component:()=>import('@/pages/info')
                },
                {
                    path:"/bed",
                    name:"bed",
                    component:()=>import('@/pages/bed')
                },
                {
                    path:"/customer",
                    name:"customer",
                    component:()=>import('@/pages/customer')
                },
                {
                    path:"/live",
                    name:"live",
                    component:()=>import('@/pages/live')
                },
                {
                    path: "/food",
                    name: "food",
                    component: () => import('@/pages/food')
                },
                {
                    path: "/recipes",
                    name: "recipes",
                    component: () => import('@/pages/recipes')
                },
                {
                    path: "/out",
                    name: "out",
                    component: () => import('@/pages/out')
                },
                {
                    path: '/check',
                    name: "check",
                    component: () => import("@/pages/check")
                },
                {
                    path: '/test',
                    name: 'test',
                    component: () => import("@/pages/test")
                }
            ]
        },
        {
            path: '/',
            name: 'login',
            component: () => import('@/pages/login')
        }
    ]
})

router.beforeEach((to, from, next) => {
    if (to.name !== 'login' && store.getters.loginStatus(store.state) == 0) {
        next({name: "login"})
    } else {
        next()
    }
})

export default router