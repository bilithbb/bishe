import request from '../utils/request';


/**
 * 获取所有留言,包括条件查询接口
 * @param query
 * @returns {AxiosPromise}
 */
export const getLeaveList = query =>{
    return request({
        url:'/common/leave/getLeaveList',
        method:'get',
        params:query
    })
};

/**
 * 获取单个留言
 * @param query
 * @returns {AxiosPromise}
 */
export const getLeaveInfo = query =>{
    return request({
        url:'/common/leave/getLeaveInfo',
        method:'get',
        params:query
    })
};

/**
 * 保存留言接口
 * @param data
 * @returns {AxiosPromise}
 */
export const saveLeaveInfo = data =>{
    return request({
        url:'/common/leave/saveLeaveInfo',
        method:'post',
        data
    })
};
/**
 * 更新留言接口
 * @param data
 * @returns {AxiosPromise}
 */
export const updateLeaveInfo = data =>{
    return request({
        url:'/common/leave/updateLeaveInfo',
        method:'put',
        data
    })
};
/**
 *根据id删除留言接口
 * @param id
 * @returns {AxiosPromise}
 */
export const delLeaveInfo = id =>{
    return request({
        url:'/common/leave/delLeaveInfo',
        method:'delete',
        params:{id:id}
    })
};
/**
 * 根据id集合批量删除留言接口
 * @param id
 * @returns {AxiosPromise}
 */
export const delBatchLeaveInfo = id =>{
    return request({
        url:'/common/leave/delBatchLeaveInfo',
        method:'delete',
        params:{idList:id}
    })
};




