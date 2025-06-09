package com.xxx.modules.controller;;
import com.xxx.modules.service.AdvertService;
import com.xxx.modules.entity.Advert;
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
 * 广告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-03-27
 */
@RestController
@RequestMapping("/common/advert")
@Api(tags="广告")
public class AdvertController {
    @Autowired
    private AdvertService advertService;

    /**
    *  获取所有广告
    * @param pageNum
    * @param pageSize
    * @param advert
    * @return
    */

    @GetMapping("/getAdvertList")
    @ApiOperation("获取所有广告")
    public Result getAdvertList(Advert advert, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return advertService.selectAdvertList(advert,pageNum,pageSize);
    }


    @GetMapping("/getAdvertInfo")
    @ApiOperation("根据id获取单个广告")
    public Result getAdvertInfo(Integer id){
        return advertService.selectAdvertInfo(id);
    }

    /**
    * 保存广告
    * @param advert
    * @return
    */
    @PostMapping("/saveAdvertInfo")
    @ApiOperation("保存广告")
    public Result saveAdvertInfo(@RequestBody Advert advert){
        return advertService.saveAdvertInfo(advert);
    }


    /**
     * 更新广告
     * @param advert
     * @return
     */

    @PutMapping("/updateAdvertInfo")
    @ApiOperation("更新广告")
    public Result updateAdvertInfo(@RequestBody Advert advert){
        return advertService.updateAdvertInfo(advert);
    }

    /**
     * 根据id删除广告
     * @param id
     * @return
     */
    @DeleteMapping("/delAdvertInfo")
    @ApiOperation("根据id删除广告")
    public Result delAdvertInfo(Integer id){
        return advertService.delAdvertInfo(id);
    }

    /**
     * 根据id集合批量删除广告
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchAdvertInfo")
    @ApiOperation("根据id集合批量删除广告")
    public Result delBatchAdvertInfo(String idList){
        return advertService.delBatchAdvertInfo(idList);
    }










}