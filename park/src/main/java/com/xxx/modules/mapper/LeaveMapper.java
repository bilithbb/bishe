package com.xxx.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Leave;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 留言
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-11-07
 */
@Mapper
public interface LeaveMapper extends BaseMapper<Leave> {

    List<Leave> selectListInfo(Leave leave);
	
}