package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.entity.User;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Advert;
import java.util.List;
/**
 * 广告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-03-27
 */
public interface AdvertService extends IService<Advert>{

    /**
     *  获取所有广告接口
     * @param pageNum
     * @param pageSize
     * @param advert
     * @return
     */
    Result selectAdvertList(Advert advert, Integer pageNum, Integer pageSize);


    /**
     *  获取单个广告接口
     * @param id
     * @return
     */
    Result selectAdvertInfo(Integer id);

    /**
     * 保存广告接口
     * @param advert
     * @return
     */
    Result saveAdvertInfo(Advert advert);

    /**
     * 更新广告接口
     * @param advert
     * @return
     */
    Result updateAdvertInfo(Advert advert);

    /**
     * 根据id删除广告接口
     * @param id
     * @return
     */
    Result delAdvertInfo(Integer id);

    /**
     * 根据id集合批量删除广告接口
     * @param idList
     * @return
     */
    Result delBatchAdvertInfo(String idList);




}