package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.mapper.StallMapper;
import com.xxx.modules.entity.Stall;
import com.xxx.modules.service.StallService;
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
 * 停车位
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
@Service
public class StallServiceImpl extends ServiceImpl<StallMapper, Stall> implements StallService {

    @Autowired
    private StallMapper stallMapper;



    /**
     *  获取所有停车位接口实现类
     * @param pageNum
     * @param pageSize
     * @param stall
     * @return
     */
    @Override
    public Result selectStallList(Stall stall, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Stall> data = stallMapper.selectListInfo(stall);
       return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个停车位接口实现类
     * @param id
     * @return
     */
    @Override
    public Result selectStallInfo(Integer id) {
        Stall stall = stallMapper.selectById(id);
        return ResultUtil.success(1,"成功",stall);
    }

    /**
     * 保存停车位接口实现类
     * @param stall
     * @return
     */
    @Override
    public Result saveStallInfo(Stall stall) {

        stall.setStatus(1);
        stall.setCount(0);
        QueryWrapper<Stall> stallNumberWrapper = new QueryWrapper<>();
        stallNumberWrapper.eq("stall_number",stall.getStallNumber());
        Stall stallNumberInfo = stallMapper.selectOne(stallNumberWrapper);
        if (stallNumberInfo != null){
            return ResultUtil.error(-1,"编号重复");
        }
        stall.setCreateTime(TimeUtil.getCurrentTime());
        stall.setUpdateTime(TimeUtil.getCurrentTime());
        stallMapper.insert(stall);
        return ResultUtil.success(1,"成功",null);
}

    /**
     * 更新停车位接口实现类
     * @param stall
     * @return
     */
    @Override
    public Result updateStallInfo(Stall stall) {
        QueryWrapper<Stall> stallNumberWrapper = new QueryWrapper<>();
        stallNumberWrapper.eq("stall_number",stall.getStallNumber()).ne("id",stall.getId());
        Stall stallNumberInfo = stallMapper.selectOne(stallNumberWrapper);
        if (stallNumberInfo != null){
            return ResultUtil.error(-1,"编号重复");
        }
        stall.setUpdateTime(TimeUtil.getCurrentTime());
        stallMapper.updateById(stall);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除停车位删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result delStallInfo(Integer id) {
        stallMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除停车位接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result delBatchStallInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        stallMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

    @Override
    public Result updateStallCountInfo(Stall stall) {
        Stall stall1 = stallMapper.selectById(stall.getId());
        stall1.setCount(stall1.getCount() + 1);
        stallMapper.updateById(stall1);
        return ResultUtil.success(1,"正常",null);
    }


}