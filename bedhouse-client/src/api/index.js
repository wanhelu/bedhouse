/* eslint-disable*/
import axios from 'axios'
import {get, post} from './http'

//测试
export const getTestData=(params)=>post(`test`,params)

// =======================>登录API
//登录
export const getLoginStatus = (params) => post(`login/status`, params)

// =======================>员工API
//根据role_id获取员工所有信息
export const getStfInfoLessRoleId=(id)=>get(`stf/infoLessRole?roleId=${id}`)
//根据员工id获取所有信息
export const getStfInfoById=(id)=>get(`stf/infoById?id=${id}`)
//更新员工信息
export const updateStfInfo=(params)=>post(`stf/update`,params)
//查询员工
export const searchStfInfo=(word,roleId)=>get(`stf/search?word=${word}&roleId=${roleId}`)
//新增员工
export const addStf=(params)=>post(`stf/add`,params)

