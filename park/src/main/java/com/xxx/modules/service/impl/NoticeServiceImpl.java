package com.xxx.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.modules.entity.Notice;
import com.xxx.modules.mapper.NoticeMapper;
import com.xxx.modules.service.NoticeService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import com.xxx.modules.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 公告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-11-07
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;


    /**
     *  获取所有公告接口实现类
     * @param pageNum
     * @param pageSize
     * @param notice
     * @return
     */
    @Override
    public Result selectNoticeList(Notice notice, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        if (notice.getTitle() !=null){
            wrapper.like("title",notice.getTitle());
        }
        List<Notice> data = noticeMapper.selectList(wrapper);
        return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个公告接口实现类
     * @param id
     * @return
     */
    @Override
    public Result selectNoticeInfo(Integer id) {
        Notice notice = noticeMapper.selectById(id);
        return ResultUtil.success(1,"成功",notice);
    }

    /**
     * 保存公告接口实现类
     * @param notice
     * @return
     */
    @Override
    public Result saveNoticeInfo(Notice notice) {
        notice.setCreateTime(TimeUtil.getCurrentTime());
        notice.setUpdateTime(TimeUtil.getCurrentTime());
        noticeMapper.insert(notice);
        return ResultUtil.success(1,"成功",null);
}

    /**
     * 更新公告接口实现类
     * @param notice
     * @return
     */
    @Override
    public Result updateNoticeInfo(Notice notice) {
        notice.setUpdateTime(TimeUtil.getCurrentTime());
        noticeMapper.updateById(notice);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除公告删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result delNoticeInfo(Integer id) {
        noticeMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除公告接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result delBatchNoticeInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        noticeMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

}