package com.xxx.modules.service.impl;
import cn.hutool.core.date.Quarter;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.modules.entity.Car;
import com.xxx.modules.entity.Stall;
import com.xxx.modules.entity.User;
import com.xxx.modules.mapper.AppointMapper;
import com.xxx.modules.entity.Appoint;
import com.xxx.modules.mapper.CarMapper;
import com.xxx.modules.mapper.StallMapper;
import com.xxx.modules.service.AppointService;
import com.xxx.modules.mapper.UserMapper;
import com.xxx.modules.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.springframework.transaction.annotation.Transactional;

/**
 * 预约
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
@Service
@Slf4j
public class AppointServiceImpl extends ServiceImpl<AppointMapper, Appoint> implements AppointService {

    @Autowired
    private AppointMapper appointMapper;



    /**
     *  获取所有预约接口实现类
     * @param pageNum
     * @param pageSize
     * @param appoint
     * @return
     */
    @Override
    public Result selectAppointList(Appoint appoint, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Appoint> data = appointMapper.selectListInfo(appoint);
       return ResultUtil.success(1,"成功",new PageInfo<>(data));
    }


    /**
     * 获取单个预约接口实现类
     * @param id
     * @return
     */
    @Override
    public Result selectAppointInfo(Integer id) {
        Appoint appoint = appointMapper.selectById(id);
        return ResultUtil.success(1,"成功",appoint);
    }
    @Value("${file.path}")
    private String filePath;
    @Autowired
    private CarMapper carMapper;
    /**
     * 保存预约接口实现类
     * @param appoint
     * @return
     */
    @Override
    public Result saveAppointInfo(Appoint appoint) {
        Stall stall1 = stallMapper.selectById(appoint.getStallId());
        if (stall1.getStatus() !=1){
            return ResultUtil.error(-1,"当前车位状态不可预约");
        }

        if (appoint.getCardImage() == null){
            return ResultUtil.error(-1,"请录入车牌号");
        }

        String fileName = TimeUtil.dateRandom18();

        String imageName = ImageUtil.base64ToImgTransformation(filePath, appoint.getCardImage(), fileName);
        appoint.setFileName(imageName);

        String result = Sample.getCarNumber(filePath + appoint.getFileName());
        JSONObject jsonObject = JSONObject.parseObject(result);
        assert jsonObject != null;
        String words_result = jsonObject.getString("words_result");
        JSONObject jsonObject1 = JSONObject.parseObject(words_result);
        String number = jsonObject1.getString("number");
        log.info("车牌号===={}",number);
        log.info("截图成功..");
        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.eq("car_number",number);
        Car car = carMapper.selectOne(wrapper);
        appoint.setType(1);
        if (car == null){
//            return ResultUtil.error(-1,"该车牌未录入");
            appoint.setType(2);
        }
//        appoint.setCarId(car.getId());
        appoint.setCarNumber(number);
        appoint.setStatus(1);
        appoint.setCreateTime(TimeUtil.getCurrentTime());
        appoint.setUpdateTime(TimeUtil.getCurrentTime());
        appoint.setYear(TimeUtil.getCurrentYear());
        appoint.setMonth(TimeUtil.getCurrentMonth());
        appointMapper.insert(appoint);
        return ResultUtil.success(1,"成功",null);
}


@Autowired
private StallMapper stallMapper;

    /**
     * 更新预约接口实现类
     * @param appoint
     * @return
     */
    @Override
    @Transactional
    public Result updateAppointInfo(Appoint appoint) {
        Appoint appoint1 = appointMapper.selectById(appoint.getId());

        if (appoint1.getStatus() == 4){
            return ResultUtil.error(-1,"该订单已经结束,不可操作");
        }
        appoint.setUpdateTime(TimeUtil.getCurrentTime());

        if (appoint.getStatus() == 2){
            //审核通过
            Stall stall = stallMapper.selectById(appoint.getStallId());
            if (stall.getStatus() == 2){
                return ResultUtil.error(-1,"当前车位状态非空闲,审核失败");
            }
            stall.setStatus(2);
            stallMapper.updateById(stall);
        }

        if (appoint.getStatus() == 4){
            if (appoint.getEndTime() == null){
                return ResultUtil.error(-1,"结束时间不得为空");
            }
            //已结束
            Stall stall = stallMapper.selectById(appoint.getStallId());
            stall.setStatus(1);
            stallMapper.updateById(stall);
            long startTimeAndEndTimeGapSecond = TimeUtil.getStartTimeAndEndTimeGapSecond(appoint.getStartTime(), appoint.getEndTime());
            DecimalFormat df = new DecimalFormat ("0.0");
            double l = (double )startTimeAndEndTimeGapSecond / 3600;
            String format = df.format(l);
            Double aDouble = Double.valueOf(format);

            double v = aDouble * stall.getPrice();
            appoint.setScore(v / 10);
            appoint.setTotalPrice(v);
        }
        appointMapper.updateById(appoint);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id删除预约删除接口实现类
     * @param id
     * @return
     */
    @Override
    public Result delAppointInfo(Integer id) {
        appointMapper.deleteById(id);
        return ResultUtil.success(1,"成功",null);
    }

    /**
     * 根据id集合批量删除预约接口实现类
     * @param idList
     * @return
     */
    @Override
    public Result delBatchAppointInfo(String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        appointMapper.deleteBatchIds(list);
        return ResultUtil.success(1,"成功",null);
    }

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public Result payAppointInfo(Appoint appoint) {
        Appoint appoint1 = appointMapper.selectById(appoint.getId());
        appoint1.setStatus(5);
        User user = userMapper.selectById(appoint1.getUserId());

        double v = user.getMoney(); //总价

        if (appoint1.getTotalPrice() > v){
            return ResultUtil.error(-1,"余额不足");
        }

        user.setMoney(v - appoint1.getTotalPrice());

        userMapper.updateById(user);
        appoint1.setCurrentDay(TimeUtil.getCurrentDay());
        appointMapper.updateById(appoint1);
        return ResultUtil.success(1,"正常",null);
    }


}