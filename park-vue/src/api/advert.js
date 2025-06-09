import request from '../utils/request';


/**
 * 获取所有广告,包括条件查询接口
 * @param query
 * @returns {AxiosPromise}
 */
export const getAdvertList = query =>{
    return request({
        url:'/common/advert/getAdvertList',
        method:'get',
        params:query
    })
};

/**
 * 获取单个广告
 * @param query
 * @returns {AxiosPromise}
 */
export const getAdvertInfo = query =>{
    return request({
        url:'/common/advert/getAdvertInfo',
        method:'get',
        params:query
    })
};

/**
 * 保存广告接口
 * @param data
 * @returns {AxiosPromise}
 */
export const saveAdvertInfo = data =>{
    return request({
        url:'/common/advert/saveAdvertInfo',
        method:'post',
        data
    })
};
/**
 * 更新广告接口
 * @param data
 * @returns {AxiosPromise}
 */
export const updateAdvertInfo = data =>{
    return request({
        url:'/common/advert/updateAdvertInfo',
        method:'put',
        data
    })
};
/**
 *根据id删除广告接口
 * @param id
 * @returns {AxiosPromise}
 */
export const delAdvertInfo = id =>{
    return request({
        url:'/common/advert/delAdvertInfo',
        method:'delete',
        params:{id:id}
    })
};
/**
 * 根据id集合批量删除广告接口
 * @param id
 * @returns {AxiosPromise}
 */
export const delBatchAdvertInfo = id =>{
    return request({
        url:'/common/advert/delBatchAdvertInfo',
        method:'delete',
        params:{idList:id}
    })
};



