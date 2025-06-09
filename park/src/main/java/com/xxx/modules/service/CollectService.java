package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.entity.User;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Collect;
import java.util.List;
/**
 * 收藏
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-03-27
 */
public interface CollectService extends IService<Collect>{

    /**
     *  获取所有收藏接口
     * @param pageNum
     * @param pageSize
     * @param collect
     * @return
     */
    Result selectCollectList(Collect collect, Integer pageNum, Integer pageSize);


    /**
     *  获取单个收藏接口
     * @param id
     * @return
     */
    Result selectCollectInfo(Integer id);

    /**
     * 保存收藏接口
     * @param collect
     * @return
     */
    Result saveCollectInfo(Collect collect);

    /**
     * 更新收藏接口
     * @param collect
     * @return
     */
    Result updateCollectInfo(Collect collect);

    /**
     * 根据id删除收藏接口
     * @param id
     * @return
     */
    Result delCollectInfo(Integer id);

    /**
     * 根据id集合批量删除收藏接口
     * @param idList
     * @return
     */
    Result delBatchCollectInfo(String idList);




}