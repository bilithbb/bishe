import request from '../utils/request';


/**
 * 获取所有挑战,包括条件查询接口
 * @param query
 * @returns {AxiosPromise}
 */
export const getActivityList = query =>{
    return request({
        url:'/common/activity/getActivityList',
        method:'get',
        params:query
    })
};

/**
 * 获取单个挑战
 * @param query
 * @returns {AxiosPromise}
 */
export const getActivityInfo = query =>{
    return request({
        url:'/common/activity/getActivityInfo',
        method:'get',
        params:query
    })
};

/**
 * 保存挑战接口
 * @param data
 * @returns {AxiosPromise}
 */
export const saveActivityInfo = data =>{
    return request({
        url:'/common/activity/saveActivityInfo',
        method:'post',
        data
    })
};
/**
 * 更新挑战接口
 * @param data
 * @returns {AxiosPromise}
 */
export const updateActivityInfo = data =>{
    return request({
        url:'/common/activity/updateActivityInfo',
        method:'put',
        data
    })
};
/**
 *根据id删除挑战接口
 * @param id
 * @returns {AxiosPromise}
 */
export const delActivityInfo = id =>{
    return request({
        url:'/common/activity/delActivityInfo',
        method:'delete',
        params:{id:id}
    })
};
/**
 * 根据id集合批量删除挑战接口
 * @param id
 * @returns {AxiosPromise}
 */
export const delBatchActivityInfo = id =>{
    return request({
        url:'/common/activity/delBatchActivityInfo',
        method:'delete',
        params:{idList:id}
    })
};



