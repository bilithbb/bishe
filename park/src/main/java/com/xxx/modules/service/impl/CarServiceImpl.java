package com.xxx.modules.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.mapper.CarMapper;
import com.xxx.modules.entity.Car;
import com.xxx.modules.service.CarService;
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
 * 汽车
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

    @Autowired
    private CarMapper carMapper;



    /**
     *  获取所有汽车接口实现类
     * @param pageNum
     * @param pageSize
     * @param car
     * @return
     */
    @Override
    public Result selectCarList(Car car, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Car> data = carMapper.selectListInfo(car);
       return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个汽车接口实现类
     * @param id
     * @return
     */
    @Override
    public Result selectCarInfo(Integer id) {
        Car car = carMapper.selectById(id);
        return ResultUtil.success(1,"成功",car);
    }

    /**
     * 保存汽车接口实现类
     * @param car
     * @return
     */
    @Override
    public Result saveCarInfo(Car car) {
        QueryWrapper<Car> carNumberWrapper = new QueryWrapper<>();
        carNumberWrapper.eq("car_number",car.getCarNumber());
        Car carNumberInfo = carMapper.selectOne(carNumberWrapper);
        if (carNumberInfo != null){
            return ResultUtil.error(-1,"车牌号重复");
        }
        car.setCreateTime(TimeUtil.getCurrentTime());
        car.setUpdateTime(TimeUtil.getCurrentTime());
        carMapper.insert(car);
        return ResultUtil.success(1,"成功",null);
}

    /**
     * 更新汽车接口实现类
     * @param car
     * @return
     */
    @Override
    public Result updateCarInfo(Car car) {
        QueryWrapper<Car> carNumberWrapper = new QueryWrapper<>();
        carNumberWrapper.eq("car_number",car.getCarNumber()).ne("id",car.getId());
        Car carNumberInfo = carMapper.selectOne(carNumberWrapper);
        if (carNumberInfo != null){
            return ResultUtil.error(-1,"车牌号重复");
        }
        car.setUpdateTime(TimeUtil.getCurrentTime());
        carMapper.updateById(car);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除汽车删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result delCarInfo(Integer id) {
        carMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除汽车接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result delBatchCarInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        carMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }



}