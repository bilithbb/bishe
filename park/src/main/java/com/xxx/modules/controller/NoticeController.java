package com.xxx.modules.controller;

import com.xxx.modules.entity.Notice;
import com.xxx.modules.service.NoticeService;
import com.xxx.modules.utils.Result;
import com.xxx.modules.utils.ResultUtil;
import com.xxx.modules.utils.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

;

/**
 * 公告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-11-07
 */
@RestController
@RequestMapping("/common/notice")
@Api(tags="公告")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
    *  获取所有公告
    * @param pageNum
    * @param pageSize
    * @param notice
    * @return
    */

    @GetMapping("/getNoticeList")
    @ApiOperation("获取所有公告")
    public Result getNoticeList(Notice notice, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return noticeService.selectNoticeList(notice,pageNum,pageSize);
    }


    @GetMapping("/getNoticeInfo")
    @ApiOperation("根据id获取单个公告")
    public Result getNoticeInfo(Integer id){
        return noticeService.selectNoticeInfo(id);
    }

    /**
    * 保存公告
    * @param notice
    * @return
    */
    @PostMapping("/saveNoticeInfo")
    @ApiOperation("保存公告")
    public Result saveNoticeInfo(@RequestBody Notice notice){
        return noticeService.saveNoticeInfo(notice);
    }


    /**
     * 更新公告
     * @param notice
     * @return
     */

    @PutMapping("/updateNoticeInfo")
    @ApiOperation("更新公告")
    public Result updateNoticeInfo(@RequestBody Notice notice){
        return noticeService.updateNoticeInfo(notice);
    }

    /**
     * 根据id删除公告
     * @param id
     * @return
     */
    @DeleteMapping("/delNoticeInfo")
    @ApiOperation("根据id删除公告")
    public Result delNoticeInfo(Integer id){
        return noticeService.delNoticeInfo(id);
    }

    /**
     * 根据id集合批量删除公告
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchNoticeInfo")
    @ApiOperation("根据id集合批量删除公告")
    public Result delBatchNoticeInfo(String idList){
        return noticeService.delBatchNoticeInfo(idList);
    }




    @PostMapping("/createNoticeData")
    public Result createNoticeData(){

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
            Notice notice = new Notice();
            notice.setCreateTime(TimeUtil.getCurrentTime());
            notice.setUpdateTime(TimeUtil.getCurrentTime());
            notice.setTitle("公告标题"+i);

            notice.setContent("公告内容"+i);

            noticeService.saveNoticeInfo(notice);
        }
        return ResultUtil.success(1,"生成成功",null);
    }


}