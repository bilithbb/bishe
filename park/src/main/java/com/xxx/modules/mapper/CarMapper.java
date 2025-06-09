package com.xxx.modules.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.modules.entity.Car;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * 汽车
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {

    List<Car> selectListInfo(Car car);

	
}