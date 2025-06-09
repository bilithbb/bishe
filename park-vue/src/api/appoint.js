import request from '../utils/request';


/**
 * 获取所有预约,包括条件查询接口
 * @param query
 * @returns {AxiosPromise}
 */
export const getAppointList = query =>{
    return request({
        url:'/common/appoint/getAppointList',
        method:'get',
        params:query
    })
};

/**
 * 获取单个预约
 * @param query
 * @returns {AxiosPromise}
 */
export const getAppointInfo = query =>{
    return request({
        url:'/common/appoint/getAppointInfo',
        method:'get',
        params:query
    })
};

/**
 * 保存预约接口
 * @param data
 * @returns {AxiosPromise}
 */
export const saveAppointInfo = data =>{
    return request({
        url:'/common/appoint/saveAppointInfo',
        method:'post',
        data
    })
};

export const payAppointInfo = data =>{
    return request({
        url:'/common/appoint/payAppointInfo',
        method:'post',
        data
    })
};
/**
 * 更新预约接口
 * @param data
 * @returns {AxiosPromise}
 */
export const updateAppointInfo = data =>{
    return request({
        url:'/common/appoint/updateAppointInfo',
        method:'put',
        data
    })
};
/**
 *根据id删除预约接口
 * @param id
 * @returns {AxiosPromise}
 */
export const delAppointInfo = id =>{
    return request({
        url:'/common/appoint/delAppointInfo',
        method:'delete',
        params:{id:id}
    })
};
/**
 * 根据id集合批量删除预约接口
 * @param id
 * @returns {AxiosPromise}
 */
export const delBatchAppointInfo = id =>{
    return request({
        url:'/common/appoint/delBatchAppointInfo',
        method:'delete',
        params:{idList:id}
    })
};



