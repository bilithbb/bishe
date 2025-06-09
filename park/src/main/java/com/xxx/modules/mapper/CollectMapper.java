package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Collect;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 收藏
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-03-27
 */
@Mapper
public interface CollectMapper extends BaseMapper<Collect> {

    List<Collect> selectListInfo(Collect collect);

	
}