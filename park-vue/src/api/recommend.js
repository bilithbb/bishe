import request from '../utils/request';

export const getRecommendList = query =>{
    return request({
        url:'/common/stall/getRecommendList',
        method:'get',
        params:query
    })
};
