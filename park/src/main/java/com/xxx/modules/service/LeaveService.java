package com.xxx.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.entity.Leave;
import com.xxx.modules.utils.Result;

/**
 * 留言
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-11-07
 */
public interface LeaveService extends IService<Leave>{

    /**
     *  获取所有留言接口
     * @param pageNum
     * @param pageSize
     * @param leave
     * @return
     */
    Result selectLeaveList(Leave leave, Integer pageNum, Integer pageSize);


    /**
     *  获取单个留言接口
     * @param id
     * @return
     */
    Result selectLeaveInfo(Integer id);

    /**
     * 保存留言接口
     * @param leave
     * @return
     */
    Result saveLeaveInfo(Leave leave);

    /**
     * 更新留言接口
     * @param leave
     * @return
     */
    Result updateLeaveInfo(Leave leave);

    /**
     * 根据id删除留言接口
     * @param id
     * @return
     */
    Result delLeaveInfo(Integer id);

    /**
     * 根据id集合批量删除留言接口
     * @param idList
     * @return
     */
    Result delBatchLeaveInfo(String idList);




}