package com.xxx.modules.controller;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.modules.entity.Appoint;
import com.xxx.modules.entity.Sort;
import com.xxx.modules.entity.User;
import com.xxx.modules.mapper.AppointMapper;
import com.xxx.modules.mapper.SortMapper;
import com.xxx.modules.mapper.StallMapper;
import com.xxx.modules.mapper.UserMapper;
import com.xxx.modules.utils.EchartUtils;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/common/echart")
public class EchartController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SortMapper sortMapper;
    @Autowired
    private AppointMapper appointMapper;
    @Autowired
    private StallMapper stallMapper;


    @GetMapping("/getStaInfo")
    public Result getStaInfo(){
        JSONObject jsonObject = new JSONObject();
        Double totalPrice = appointMapper.selectTotalPrice(new Appoint());
        Integer userCount = userMapper.selectCount(null);
        Integer stallCount = stallMapper.selectCount(null);
        jsonObject.put("totalPrice",totalPrice==null?0:totalPrice);
        jsonObject.put("userCount",userCount == null?0:userCount);
        jsonObject.put("stallCount",stallCount == null?0:stallCount);
        return ResultUtil.success(1,"正常",jsonObject);
    }


    /**
     * 获取一种统计数据的柱状图或者折线图,可以区分年月日,
     * type=1横坐标为近三年
     * type=2横坐标为近十二月
     * type=3横坐标为近七天
     * type=4的话就是其他了
     * 纵坐标的值,一般都是要循环xData,将横坐标作为条件去查询,得出纵坐标的值
     * @param type
     * @return
     */
    @GetMapping("/getOneHistogramOrLineData")
    public Result getHistogramOrLineData(Integer type){
        /**
         * 一种统计的柱状图类型,一般都是要求这种数据格式
         * const xData = ['2019-01','2019-02','2019-03','2019-04','2019-05','2019-06'];
         * const yData = [23,60,20,36,23,85];
         */
//        List<String> xData = new ArrayList<>(); //横坐标
        List<Object> yData = new ArrayList<>(); //纵坐标
        List<String> xData =  EchartUtils.recentSevenDay();
        for (String currentDay:xData){
            Appoint appoint = new Appoint();
            appoint.setCurrentDay(currentDay);
            Double count = appointMapper.selectTotalPrice(appoint);
            yData.add(count==null?0:count);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("xData",xData);
        jsonObject.put("yData",yData);
        return ResultUtil.success(1,"正常",jsonObject);
    }


    /**
     * 获取饼图的数据
     * @param type
     * @return
     */
    @GetMapping("/getPieData")
    public Result getPieData(Integer type){
        /**
         * 一种统计的饼图类型,一般都是要求这种数据格式
         *  const legends = ['湖北', '河南', '山西', '陕西', '广西', '吉林'];
         *  const colors = ['#0278e6', '#34d160', '#fcdf39', '#f19611', '#00c6ff', '#f76363'].reverse();
         *  const data = [
         *         { name: '湖北', value: 1 },
         *         { name: '河南', value: 2 },
         *         { name: '山西', value: 3 },
         *         { name: '陕西', value: 4 },
         *         { name: '广西', value: 5 },
         *         { name: '吉林', value: 6 }
         *  ];
         *  legends和colors可能没有,但是data这个数据格式是一定有的
         */
        List<String> legendData = new ArrayList<>(); //横坐标
        List<Object> data = new ArrayList<>(); //纵坐标
        for (int i=1;i<3;i++){
            JSONObject jsonObject = new JSONObject();
            if (i == 1){
                jsonObject.put("name","男");
                legendData.add("男");
            }else {
                jsonObject.put("name","女");
                legendData.add("女");
            }
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("sex",i);
            jsonObject.put("value",userMapper.selectCount(wrapper));
            data.add(jsonObject);

        }
        //获取颜色
        List<String> colorList = getRandomColorList(2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",data);
        jsonObject.put("legendData",legendData);
        jsonObject.put("colorList",colorList);
        return ResultUtil.success(1,"正常",jsonObject);
    }



    /**
     * 获取两种统计数据的柱状图或者折线图,可以区分年月日,
     * type=1横坐标为近三年
     * type=2横坐标为近十二月
     * type=3横坐标为近七天
     * type=4的话就是其他了
     * 纵坐标的值,一般都是要循环xData,将横坐标作为条件去查询,得出纵坐标的值
     * @param type
     * @return
     */
    @GetMapping("/getTwoHistogramOrLineData")
    public Result getTwoHistogramOrLineData(Integer type,Integer userId){
        /**
         * 两种种统计的柱状图类型,一般都是要求这种数据格式
         *    const legendData = ["销售水量", "主营业务"];
         *     const xData =  ["当年完成水量", "去年同期水量", "滚动目标值水量", "全年目标值水量", "当年完成金额", "去年同期金额", "滚动目标金额", "全年目标值"];
         *     const yOneData = [4.2, 3.8, 4.8, 3.5, 2.9, 2.8, 3, 5];
         *     const yTwoData = [7.4, 3.8, 4.8, 3.5, 2.9, 2.8, 3, 5];;
         */
        List<String> legendData = new ArrayList<>();
        List<String> xData = new ArrayList<>(); //横坐标
        List<Object> yOneData = new ArrayList<>(); //纵坐标数据1
        List<Object> yTwoData = new ArrayList<>(); //纵坐标数据2
        legendData.add("数据1");
        legendData.add("数据2");
        if (type == null){
            type =1;
        }

        if (type == 3){
            xData = EchartUtils.recentThreeYear();
        }
        if(type == 2){
            xData = EchartUtils.recentTwelveMonth();
        }
        if (type == 1){
            xData = EchartUtils.recentSevenDay();
        }
        int i=1;
        for (String x:xData){

            Appoint appoint = new Appoint();
            appoint.setUserId(userId);
            if (type == 1){
                appoint.setCurrentDay(x);
                Integer totalCount = appointMapper.selectTotalCount(appoint);
                yOneData.add(totalCount==null?0:totalCount);
                Double totalPrice = appointMapper.selectTotalPrice(appoint);
                yTwoData.add(totalPrice==null?0:totalPrice);
            }
            if (type == 2){

                appoint.setMonth(i);
                Integer totalCount = appointMapper.selectTotalCount(appoint);
                yOneData.add(totalCount==null?0:totalCount);
                Double totalPrice = appointMapper.selectTotalPrice(appoint);
                yTwoData.add(totalPrice==null?0:totalPrice);
            }
            if (type == 3){
                appoint.setYear(x);
                Integer totalCount = appointMapper.selectTotalCount(appoint);
                yOneData.add(totalCount==null?0:totalCount);
                Double totalPrice = appointMapper.selectTotalPrice(appoint);
                yTwoData.add(totalPrice==null?0:totalPrice);
            }
            i++;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("xData",xData);
        jsonObject.put("legendData",legendData);
        jsonObject.put("yOneData",yOneData);
        jsonObject.put("yTwoData",yTwoData);
        return ResultUtil.success(1,"正常",jsonObject);
    }




    /**
     * 获取三种统计数据的柱状图或者折线图,可以区分年月日,
     * type=1横坐标为近三年
     * type=2横坐标为近十二月
     * type=3横坐标为近七天
     * type=4的话就是其他了
     * 纵坐标的值,一般都是要循环xData,将横坐标作为条件去查询,得出纵坐标的值
     * @param type
     * @return
     */
    @GetMapping("/getThreeHistogramOrLineData")
    public Result getThreeHistogramOrLineData(Integer type){
        /**
         * 三种统计的柱状图类型,一般都是要求这种数据格式
         const xData =  ['北京', '上海', '广州', '深圳', '香港', '澳门', '台湾'];
         const lineOneData = [10, 10, 30, 12, 15, 3, 7]
         const lineTwoData = [5, 12, 11, 14, 25, 16, 10];
         const lineThreeData = [150, 120, 170, 140, 500, 160, 110];
         const colorList = ["#9E87FF", '#73DDFF', '#fe9a8b', '#F56948', '#9E87FF'];
         */
        List<String> xData = new ArrayList<>(); //横坐标
        List<Object> yOneData = new ArrayList<>(); //纵坐标数据1
        List<Object> yTwoData = new ArrayList<>(); //纵坐标数据2
        List<Object> yThreeData = new ArrayList<>(); //纵坐标数据2
        List<String> colorList = getRandomColorList(4);
        if (type == 1){
            xData = EchartUtils.recentThreeYear();
        }
        if(type == 2){
            xData = EchartUtils.recentTwelveMonth();
        }
        if (type == 3){
            xData = EchartUtils.recentSevenDay();
        }
        if (type == 4){
            //横坐标其他类型的,需要自行查询
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("xData",xData);
        jsonObject.put("yOneData",yOneData);
        jsonObject.put("yTwoData",yTwoData);
        jsonObject.put("yThreeData",yThreeData);
        jsonObject.put("colorList",colorList);
        return ResultUtil.success(1,"正常",jsonObject);
    }



    /**
     * 从集合中随机取出N个不重复的元素
     * @param n 取出的元素数量
     * @return
     */
    private List<String> getRandomColorList( int n) {
        List<String> list = new ArrayList<>();
        list.add("#0278e6");
        list.add("#34d160");
        list.add("#fcdf39");
        list.add("#f19611");
        list.add("#00c6ff");
        list.add("#f76363");
        list.add("#37a2da");
        list.add("#32c5e9");
        list.add("#9fe6b8");

        list.add("#ffdb5c");
        list.add("#ff9f7f");
        list.add("#fb7293");
        list.add("#e7bcf3");
        list.add("#8378ea");

        list.add("#00ffff");
        list.add("#ff3000");
        list.add("#ff5b00");
        list.add("#ffa800");
        list.add("#ffe000");

        list.add("#006ced");
        list.add("#00cfff");
        list.add("#00ffff");

        Map<Integer,String> map = new HashMap();
        List<String> news = new ArrayList();
        if (list.size() <= n) {
            return list;
        } else {
            while (map.size() < n) {
                int random = (int)(Math.random() * list.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    news.add(list.get(random));
                }
            }
            return news;
        }
    }

}
