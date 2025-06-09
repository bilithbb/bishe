package com.xxx.modules.service;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.modules.entity.User;
import com.xxx.modules.utils.Result;
import com.xxx.modules.entity.Car;
import java.util.List;
/**
 * 汽车
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
public interface CarService extends IService<Car>{

    /**
     *  获取所有汽车接口
     * @param pageNum
     * @param pageSize
     * @param car
     * @return
     */
    Result selectCarList(Car car, Integer pageNum, Integer pageSize);


    /**
     *  获取单个汽车接口
     * @param id
     * @return
     */
    Result selectCarInfo(Integer id);

    /**
     * 保存汽车接口
     * @param car
     * @return
     */
    Result saveCarInfo(Car car);

    /**
     * 更新汽车接口
     * @param car
     * @return
     */
    Result updateCarInfo(Car car);

    /**
     * 根据id删除汽车接口
     * @param id
     * @return
     */
    Result delCarInfo(Integer id);

    /**
     * 根据id集合批量删除汽车接口
     * @param idList
     * @return
     */
    Result delBatchCarInfo(String idList);




}