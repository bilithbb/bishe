package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Appoint;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 预约
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
@Mapper
public interface AppointMapper extends BaseMapper<Appoint> {

    List<Appoint> selectListInfo(Appoint appoint);


    Double selectTotalPrice(Appoint appoint);

    Integer selectTotalCount(Appoint appoint);

    List<Integer> selectDistinctUserId();
}