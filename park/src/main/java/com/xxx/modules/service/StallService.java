package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.entity.User;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Stall;
import java.util.List;
/**
 * 停车位
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
public interface StallService extends IService<Stall>{

    /**
     *  获取所有停车位接口
     * @param pageNum
     * @param pageSize
     * @param stall
     * @return
     */
    Result selectStallList(Stall stall, Integer pageNum, Integer pageSize);


    /**
     *  获取单个停车位接口
     * @param id
     * @return
     */
    Result selectStallInfo(Integer id);

    /**
     * 保存停车位接口
     * @param stall
     * @return
     */
    Result saveStallInfo(Stall stall);

    /**
     * 更新停车位接口
     * @param stall
     * @return
     */
    Result updateStallInfo(Stall stall);

    /**
     * 根据id删除停车位接口
     * @param id
     * @return
     */
    Result delStallInfo(Integer id);

    /**
     * 根据id集合批量删除停车位接口
     * @param idList
     * @return
     */
    Result delBatchStallInfo(String idList);


    Result updateStallCountInfo(Stall stall);
}