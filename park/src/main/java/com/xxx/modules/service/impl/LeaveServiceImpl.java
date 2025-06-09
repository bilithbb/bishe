package com.xxx.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.modules.entity.Leave;
import com.xxx.modules.mapper.LeaveMapper;
import com.xxx.modules.service.LeaveService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import com.xxx.modules.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 留言
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-11-07
 */
@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;


    /**
     *  获取所有留言接口实现类
     * @param pageNum
     * @param pageSize
     * @param leave
     * @return
     */
    @Override
    public Result selectLeaveList(Leave leave, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Leave> data = leaveMapper.selectListInfo(leave);
       return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个留言接口实现类
     * @param id
     * @return
     */
    @Override
    public Result selectLeaveInfo(Integer id) {
        Leave leave = leaveMapper.selectById(id);
        return ResultUtil.success(1,"成功",leave);
    }

    /**
     * 保存留言接口实现类
     * @param leave
     * @return
     */
    @Override
    public Result saveLeaveInfo(Leave leave) {
        QueryWrapper<Leave> leaveMessageWrapper = new QueryWrapper<>();
        leaveMessageWrapper.eq("leave_message",leave.getLeaveMessage());
        Leave leaveMessageInfo = leaveMapper.selectOne(leaveMessageWrapper);
        if (leaveMessageInfo != null){
            return ResultUtil.error(-1,"留言内容重复");
        }
        leave.setCreateTime(TimeUtil.getCurrentTime());
        leave.setUpdateTime(TimeUtil.getCurrentTime());
        leaveMapper.insert(leave);
        return ResultUtil.success(1,"成功",null);
}

    /**
     * 更新留言接口实现类
     * @param leave
     * @return
     */
    @Override
    public Result updateLeaveInfo(Leave leave) {
        QueryWrapper<Leave> leaveMessageWrapper = new QueryWrapper<>();
        leaveMessageWrapper.eq("leave_message",leave.getLeaveMessage()).ne("id",leave.getId());
        Leave leaveMessageInfo = leaveMapper.selectOne(leaveMessageWrapper);
        if (leaveMessageInfo != null){
            return ResultUtil.error(-1,"留言内容重复");
        }
        leave.setUpdateTime(TimeUtil.getCurrentTime());
        leaveMapper.updateById(leave);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除留言删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result delLeaveInfo(Integer id) {
        leaveMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除留言接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result delBatchLeaveInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        leaveMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

}