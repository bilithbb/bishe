import request from '../utils/request';


/**
 * 获取所有收藏,包括条件查询接口
 * @param query
 * @returns {AxiosPromise}
 */
export const getCollectList = query =>{
    return request({
        url:'/common/collect/getCollectList',
        method:'get',
        params:query
    })
};

/**
 * 获取单个收藏
 * @param query
 * @returns {AxiosPromise}
 */
export const getCollectInfo = query =>{
    return request({
        url:'/common/collect/getCollectInfo',
        method:'get',
        params:query
    })
};

/**
 * 保存收藏接口
 * @param data
 * @returns {AxiosPromise}
 */
export const saveCollectInfo = data =>{
    return request({
        url:'/common/collect/saveCollectInfo',
        method:'post',
        data
    })
};
/**
 * 更新收藏接口
 * @param data
 * @returns {AxiosPromise}
 */
export const updateCollectInfo = data =>{
    return request({
        url:'/common/collect/updateCollectInfo',
        method:'put',
        data
    })
};
/**
 *根据id删除收藏接口
 * @param id
 * @returns {AxiosPromise}
 */
export const delCollectInfo = id =>{
    return request({
        url:'/common/collect/delCollectInfo',
        method:'delete',
        params:{id:id}
    })
};
/**
 * 根据id集合批量删除收藏接口
 * @param id
 * @returns {AxiosPromise}
 */
export const delBatchCollectInfo = id =>{
    return request({
        url:'/common/collect/delBatchCollectInfo',
        method:'delete',
        params:{idList:id}
    })
};



