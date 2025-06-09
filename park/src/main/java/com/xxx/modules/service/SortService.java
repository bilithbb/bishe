package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.entity.User;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Sort;
import java.util.List;
/**
 * 分类
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-01-05
 */
public interface SortService extends IService<Sort>{

    /**
     *  获取所有分类接口
     * @param pageNum
     * @param pageSize
     * @param sort
     * @return
     */
    Result selectSortList(Sort sort, Integer pageNum, Integer pageSize);


    /**
     *  获取单个分类接口
     * @param id
     * @return
     */
    Result selectSortInfo(Integer id);

    /**
     * 保存分类接口
     * @param sort
     * @return
     */
    Result saveSortInfo(Sort sort);

    /**
     * 更新分类接口
     * @param sort
     * @return
     */
    Result updateSortInfo(Sort sort);

    /**
     * 根据id删除分类接口
     * @param id
     * @return
     */
    Result delSortInfo(Integer id);

    /**
     * 根据id集合批量删除分类接口
     * @param idList
     * @return
     */
    Result delBatchSortInfo(String idList);




}