package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.entity.User;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Appoint;
import java.util.List;
/**
 * 预约
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
public interface AppointService extends IService<Appoint>{

    /**
     *  获取所有预约接口
     * @param pageNum
     * @param pageSize
     * @param appoint
     * @return
     */
    Result selectAppointList(Appoint appoint, Integer pageNum, Integer pageSize);


    /**
     *  获取单个预约接口
     * @param id
     * @return
     */
    Result selectAppointInfo(Integer id);

    /**
     * 保存预约接口
     * @param appoint
     * @return
     */
    Result saveAppointInfo(Appoint appoint);

    /**
     * 更新预约接口
     * @param appoint
     * @return
     */
    Result updateAppointInfo(Appoint appoint);

    /**
     * 根据id删除预约接口
     * @param id
     * @return
     */
    Result delAppointInfo(Integer id);

    /**
     * 根据id集合批量删除预约接口
     * @param idList
     * @return
     */
    Result delBatchAppointInfo(String idList);


    Result payAppointInfo(Appoint appoint);
}