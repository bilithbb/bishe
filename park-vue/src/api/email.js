import request from '../utils/request';

/**
 * 发送邮件信息接口
 * @param data
 * @returns {AxiosPromise}
 */
export const sendEmail = query =>{
    return request({
        url:'/common/email/sendEmail',
        method:'get',
        params:query
    })
};
export const resetPassword = data =>{
    return request({
        url:'/common/email/resetPassword',
        method:'post',
        data
    })
};
