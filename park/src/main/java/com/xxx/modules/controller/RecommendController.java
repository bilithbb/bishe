package com.xxx.modules.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.modules.config.UserCF;
import com.xxx.modules.entity.Appoint;
import com.xxx.modules.entity.Stall;
import com.xxx.modules.entity.User;
import com.xxx.modules.mapper.AppointMapper;
import com.xxx.modules.mapper.StallMapper;
import com.xxx.modules.mapper.UserMapper;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/common/stall")
@Slf4j
public class RecommendController {
    



    @Autowired
    private StallMapper stallMapper;
    @Autowired
    private AppointMapper appointMapper;


    @GetMapping("/getRecommendList")
    public Result getRecommendStallList(Integer userId){

        if (userId == null){
            return ResultUtil.error(-1,"用户ID不得为空");
        }
        QueryWrapper<Appoint> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        Integer count = appointMapper.selectCount(wrapper);
        if (count == null || count == 0){
            log.info("用户暂无收藏信息,走随机推荐...");
            Stall stall = new Stall();
            stall.setStatus(1);
            List<Stall> stallList = stallMapper.selectListInfo(stall);
            List<Stall> list = createRandoms2(stallList, 3);
            return ResultUtil.success(1,"正常",list);
        }
        log.info("用户有收藏信息走协同过滤推荐...");
        //查询出已经收藏过的用户
        List<Integer> list = appointMapper.selectDistinctUserId();
        //查询用户对物品的评分,我们是收藏的,所以所有的评分默认就是1了;
        Map<Integer, Map<Integer, Double>> data = new HashMap<>();
        for (Integer id:list){
            QueryWrapper<Appoint> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("user_id",id);
            List<Appoint> favoriteList = appointMapper.selectList(wrapper1);
            Map<Integer,Double> map = new HashMap<>();
            for (Appoint appoint:favoriteList){
                map.put(appoint.getStallId(),1.0);
            }
            data.put(id,map);
        }

        UserCF userCF = new UserCF(data);
        List<Integer> recommendIdList = userCF.recommend(userId, 2, 3);

        if (recommendIdList !=null && recommendIdList.size() > 0){
            QueryWrapper<Stall> wrapper1 = new QueryWrapper<>();
            wrapper1.in("id",recommendIdList);
            Stall stall = new Stall();
            stall.setIdList(recommendIdList);
            List<Stall> stallList = stallMapper.selectListInfo(stall);
            return ResultUtil.success(1,"正常",stallList);
        }else {
            return ResultUtil.success(1,"正常",new ArrayList<>());
        }

    }

    /**
     * 从集合中随机取出N个不重复的元素
     * @param list 需要被取出数据的集合
     * @param n 取出的元素数量
     * @return
     */
    private List<User> createRandoms(List<User> list, int n) {
        Map<Integer,String> map = new HashMap();
        List<User> news = new ArrayList();
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

    private List<Stall> createRandoms2(List<Stall> list, int n) {
        Map<Integer,String> map = new HashMap();
        List<Stall> news = new ArrayList();
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
