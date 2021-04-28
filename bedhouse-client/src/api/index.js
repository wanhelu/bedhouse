/* eslint-disable*/
import axios from 'axios'
import {get, post} from './http'

//测试
export const getTestData=(params)=>post(`test`,params)

// =======================>登录API
//登录
export const getLoginStatus = (params) => post(`login/status`, params)

