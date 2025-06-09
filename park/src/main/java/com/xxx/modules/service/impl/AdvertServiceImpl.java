package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.entity.Collect;
import com.xxx.modules.mapper.AdvertMapper;
import com.xxx.modules.entity.Advert;
import com.xxx.modules.mapper.CollectMapper;
import com.xxx.modules.service.AdvertService;
import com.xxx.modules.mapper.UserMapper;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import com.xxx.modules.utils.TimeUtil;
/**
 * 广告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-03-27
 */
@Service
public class AdvertServiceImpl extends ServiceImpl<AdvertMapper, Advert> implements AdvertService {

    @Autowired
    private AdvertMapper advertMapper;



    /**
     *  获取所有广告接口实现类
     * @param pageNum
     * @param pageSize
     * @param advert
     * @return
     */
    @Override
    public Result selectAdvertList(Advert advert, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Advert> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        if (advert.getTitle() !=null){
            wrapper.like("title",advert.getTitle());
        }
        if (advert.getId() !=null){
            wrapper.eq("id",advert.getId());
        }
        List<Advert> data = advertMapper.selectList(wrapper);
        if (advert.getUserId() !=null){
            for (Advert advert1:data){
                QueryWrapper<Collect> wrapper2 = new QueryWrapper<>();
                wrapper2.eq("user_id",advert.getUserId()).eq("advert_id",advert1.getId());
                Collect collect = collectMapper.selectOne(wrapper2);
                advert1.setIsCollect(collect !=null);
            }
        }

        return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }
    @Autowired
    private CollectMapper collectMapper;


    /**
     * 获取单个广告接口实现类
     * @param id
     * @return
     */
    @Override
    public Result selectAdvertInfo(Integer id) {
        Advert advert = advertMapper.selectById(id);

        return ResultUtil.success(1,"成功",advert);
    }

    /**
     * 保存广告接口实现类
     * @param advert
     * @return
     */
    @Override
    public Result saveAdvertInfo(Advert advert) {
        advert.setCreateTime(TimeUtil.getCurrentTime());
        advert.setUpdateTime(TimeUtil.getCurrentTime());
        advertMapper.insert(advert);
        return ResultUtil.success(1,"成功",null);
}

    /**
     * 更新广告接口实现类
     * @param advert
     * @return
     */
    @Override
    public Result updateAdvertInfo(Advert advert) {
        advert.setUpdateTime(TimeUtil.getCurrentTime());
        advertMapper.updateById(advert);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除广告删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result delAdvertInfo(Integer id) {
        advertMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除广告接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result delBatchAdvertInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        advertMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }



}