import request from '../utils/request';


/**
 * 获取所有汽车,包括条件查询接口
 * @param query
 * @returns {AxiosPromise}
 */
export const getCarList = query =>{
    return request({
        url:'/common/car/getCarList',
        method:'get',
        params:query
    })
};

/**
 * 获取单个汽车
 * @param query
 * @returns {AxiosPromise}
 */
export const getCarInfo = query =>{
    return request({
        url:'/common/car/getCarInfo',
        method:'get',
        params:query
    })
};

/**
 * 保存汽车接口
 * @param data
 * @returns {AxiosPromise}
 */
export const saveCarInfo = data =>{
    return request({
        url:'/common/car/saveCarInfo',
        method:'post',
        data
    })
};
/**
 * 更新汽车接口
 * @param data
 * @returns {AxiosPromise}
 */
export const updateCarInfo = data =>{
    return request({
        url:'/common/car/updateCarInfo',
        method:'put',
        data
    })
};
/**
 *根据id删除汽车接口
 * @param id
 * @returns {AxiosPromise}
 */
export const delCarInfo = id =>{
    return request({
        url:'/common/car/delCarInfo',
        method:'delete',
        params:{id:id}
    })
};
/**
 * 根据id集合批量删除汽车接口
 * @param id
 * @returns {AxiosPromise}
 */
export const delBatchCarInfo = id =>{
    return request({
        url:'/common/car/delBatchCarInfo',
        method:'delete',
        params:{idList:id}
    })
};



