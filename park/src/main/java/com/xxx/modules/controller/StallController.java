package com.xxx.modules.controller;;
import com.xxx.modules.service.StallService;
import com.xxx.modules.entity.Stall;
import com.xxx.modules.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import com.github.pagehelper.PageInfo;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import com.xxx.modules.utils.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
/**
 * 停车位
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
@RestController
@RequestMapping("/common/stall")
@Api(tags="停车位")
public class StallController {
    @Autowired
    private StallService stallService;

    /**
    *  获取所有停车位
    * @param pageNum
    * @param pageSize
    * @param stall
    * @return
    */

    @GetMapping("/getStallList")
    @ApiOperation("获取所有停车位")
    public Result getStallList(Stall stall, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return stallService.selectStallList(stall,pageNum,pageSize);
    }


    @GetMapping("/getStallInfo")
    @ApiOperation("根据id获取单个停车位")
    public Result getStallInfo(Integer id){
        return stallService.selectStallInfo(id);
    }

    /**
    * 保存停车位
    * @param stall
    * @return
    */
    @PostMapping("/saveStallInfo")
    @ApiOperation("保存停车位")
    public Result saveStallInfo(@RequestBody Stall stall){
        return stallService.saveStallInfo(stall);
    }


    /**
     * 更新停车位
     * @param stall
     * @return
     */

    @PutMapping("/updateStallInfo")
    @ApiOperation("更新停车位")
    public Result updateStallInfo(@RequestBody Stall stall){
        return stallService.updateStallInfo(stall);
    }

    @PutMapping("/updateStallCountInfo")
    @ApiOperation("更新停车位")
    public Result updateStallCountInfo(@RequestBody Stall stall){
        return stallService.updateStallCountInfo(stall);
    }

    /**
     * 根据id删除停车位
     * @param id
     * @return
     */
    @DeleteMapping("/delStallInfo")
    @ApiOperation("根据id删除停车位")
    public Result delStallInfo(Integer id){
        return stallService.delStallInfo(id);
    }

    /**
     * 根据id集合批量删除停车位
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchStallInfo")
    @ApiOperation("根据id集合批量删除停车位")
    public Result delBatchStallInfo(String idList){
        return stallService.delBatchStallInfo(idList);
    }








    @PostMapping("/createStallData")
    public Result createStallData(){

        List<String> imageList = new ArrayList<>();
        imageList.add("https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2023-10-25//242357c9-08c4-4c4f-854d-67de9b271dbe_u=1996284881,1109846881&fm=253&fmt=auto&app=138&f=JPEG.webp");
        imageList.add("https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2023-10-30//0d09b8e2-e66d-4231-8d35-5c4552a48f2a_2dd8fe9b59774c7ca160bb2d5741c763.jpg");
        imageList.add("https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2023-11-02//d5f5276d-07bb-435c-8b83-6673fed5f22d_Snipaste_2023-03-13_19-49-26.png");
        imageList.add("https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2022-12-13//22400dd3-cc8e-4477-823b-b3fb698ef9d5_25.png");
        imageList.add("http://yjy-xiaotuxian-dev.oss-cn-beijing.aliyuncs.com/picture/2021-04-05/7f483771-6831-4a7a-abdb-2625acb755f3.png");
        imageList.add("https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2023-10-30//0d09b8e2-e66d-4231-8d35-5c4552a48f2a_2dd8fe9b59774c7ca160bb2d5741c763.jpg");
        imageList.add("https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2023-11-02//d5f5276d-07bb-435c-8b83-6673fed5f22d_Snipaste_2023-03-13_19-49-26.png");
        imageList.add("https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2023-10-25//5aeb86e4-5c96-433a-8180-2ef50443dd5c_d0176b163973961ed01818d04ee94a06 (1).png");
        imageList.add("https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2023-10-25//7f50ce5d-04ab-4db6-88a5-c721e0256256_7f6a7b20-7902-4b43-b9c5-f33151ef1334.jpg");
        imageList.add("https://gulimall-psw.oss-cn-hangzhou.aliyuncs.com/2021-05-04//0bed6c30-d19f-4771-8fcd-7de722195643_2016121809101738-480x300.jpg");
        for (int i=1;i<11;i++) {
            Stall stall = new Stall();
            stall.setCreateTime(TimeUtil.getCurrentTime());
            stall.setUpdateTime(TimeUtil.getCurrentTime());
            stall.setStallNumber("编号"+i);

            stall.setStallName("名称"+i);

            stall.setSortId(i);

            stall.setLocation("楼层位置"+i);

            stall.setDesc("简介"+i);

            stall.setContent("内容"+i);

            stall.setStatus(1);

            stall.setIsBanner(1);

            stall.setImageUrl( imageList.get(i-1) );

            stall.setCount(i);

            stallService.saveStallInfo(stall);
        }
        return ResultUtil.success(1,"生成成功",null);
    }


}