import request from '../utils/request';


/**
 * 获取所有停车位,包括条件查询接口
 * @param query
 * @returns {AxiosPromise}
 */
export const getStallList = query =>{
    return request({
        url:'/common/stall/getStallList',
        method:'get',
        params:query
    })
};

/**
 * 获取单个停车位
 * @param query
 * @returns {AxiosPromise}
 */
export const getStallInfo = query =>{
    return request({
        url:'/common/stall/getStallInfo',
        method:'get',
        params:query
    })
};

/**
 * 保存停车位接口
 * @param data
 * @returns {AxiosPromise}
 */
export const saveStallInfo = data =>{
    return request({
        url:'/common/stall/saveStallInfo',
        method:'post',
        data
    })
};
/**
 * 更新停车位接口
 * @param data
 * @returns {AxiosPromise}
 */
export const updateStallInfo = data =>{
    return request({
        url:'/common/stall/updateStallInfo',
        method:'put',
        data
    })
};
export const updateStallCountInfo = data =>{
    return request({
        url:'/common/stall/updateStallCountInfo',
        method:'put',
        data
    })
};

/**
 *根据id删除停车位接口
 * @param id
 * @returns {AxiosPromise}
 */
export const delStallInfo = id =>{
    return request({
        url:'/common/stall/delStallInfo',
        method:'delete',
        params:{id:id}
    })
};
/**
 * 根据id集合批量删除停车位接口
 * @param id
 * @returns {AxiosPromise}
 */
export const delBatchStallInfo = id =>{
    return request({
        url:'/common/stall/delBatchStallInfo',
        method:'delete',
        params:{idList:id}
    })
};



export const createStallData = data =>{
    return request({
        url:'/common/stall/createStallData',
        method:'post',
    })
};
