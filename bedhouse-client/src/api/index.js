/* eslint-disable*/
import axios from 'axios'
import {get, post,deletes} from './http'

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
//删除员工
export const delStf=(id)=>deletes(`stf/delete?id=${id}`)
//修改员工信息
export const editStf=(params)=>post(`stf/edit`,params)

// =======================>床位API
//获取所有床位信息
export const getBedInfo=()=>get(`bed/info`)
//获取床位占用信息
export const getBedUsedInfo=(id)=>get(`bed/usedInfo?id=${id}`)
//查询床位信息
export const searchBedInfo=(word)=>get(`bed/search?word=${word}`)
//新增床位
export const addBed=(params)=>post(`bed/add`,params)
//修改床位信息
export const editBed=(params)=>post(`bed/edit`,params)
//删除床位信息
export const delBed=(id)=>deletes(`bed/delete?id=${id}`)

// =======================>客户API
//获取所有客户信息
export const getCustomerInfo=()=>get(`customer/info`)
//查询
export const searchCustomerInfo=(word)=>get(`customer/search?word=${word}`)
//新增
export const addCustomer=(params)=>post(`customer/add`,params)
//删除
export const delCustomer=(id)=>deletes(`customer/del?id=${id}`)
//修改
export const editCustomer=(params)=>post(`customer/edit`,params)

// =======================>入住API
//获取所有入住信息
export const getLiveInfo=()=>get(`live/info`)
//获取所使用的床位号
export const getUseBed=(id)=>get(`live/useBed?id=${id}`)
//搜索入住信息
export const searchLiveInfo=(params)=>post(`live/search`,params)
//新增入住
export const addLive=(params)=>post(`live/add`,params)
//删除记录
export const delLive=(id)=>deletes(`live/del?id=${id}`)
//修改
export const editLive=(params)=>post(`live/edit`,params)
//退住
export const leave=(id)=>get(`live/leave?id=${id}`)

// =======================>食品项API
//获取所有食品信息
export const getFoodInfo=()=>get(`food/info`)
//搜索
export const searchFoodInfo=(word)=>get(`food/search?word=${word}`)
//新增
export const addFood=(params)=>post(`food/add`,params)
//删除
export const delFood=(id)=>deletes(`food/del?id=${id}`)
//修改
export const editFood=(params)=>post(`food/edit`,params)