package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Stall;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 停车位
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
@Mapper
public interface StallMapper extends BaseMapper<Stall> {

    List<Stall> selectListInfo(Stall stall);

	
}