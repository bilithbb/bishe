package com.xxx.modules.controller;;
import com.xxx.modules.service.AppointService;
import com.xxx.modules.entity.Appoint;
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
 * 预约
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
@RestController
@RequestMapping("/common/appoint")
@Api(tags="预约")
public class AppointController {
    @Autowired
    private AppointService appointService;

    /**
    *  获取所有预约
    * @param pageNum
    * @param pageSize
    * @param appoint
    * @return
    */

    @GetMapping("/getAppointList")
    @ApiOperation("获取所有预约")
    public Result getAppointList(Appoint appoint, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return appointService.selectAppointList(appoint,pageNum,pageSize);
    }


    @GetMapping("/getAppointInfo")
    @ApiOperation("根据id获取单个预约")
    public Result getAppointInfo(Integer id){
        return appointService.selectAppointInfo(id);
    }

    /**
    * 保存预约
    * @param appoint
    * @return
    */
    @PostMapping("/saveAppointInfo")
    @ApiOperation("保存预约")
    public Result saveAppointInfo(@RequestBody Appoint appoint){
        return appointService.saveAppointInfo(appoint);
    }

    @PostMapping("/payAppointInfo")
    @ApiOperation("保存预约")
    public Result payAppointInfo(@RequestBody Appoint appoint){
        return appointService.payAppointInfo(appoint);
    }


    /**
     * 更新预约
     * @param appoint
     * @return
     */

    @PutMapping("/updateAppointInfo")
    @ApiOperation("更新预约")
    public Result updateAppointInfo(@RequestBody Appoint appoint){
        return appointService.updateAppointInfo(appoint);
    }

    /**
     * 根据id删除预约
     * @param id
     * @return
     */
    @DeleteMapping("/delAppointInfo")
    @ApiOperation("根据id删除预约")
    public Result delAppointInfo(Integer id){
        return appointService.delAppointInfo(id);
    }

    /**
     * 根据id集合批量删除预约
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchAppointInfo")
    @ApiOperation("根据id集合批量删除预约")
    public Result delBatchAppointInfo(String idList){
        return appointService.delBatchAppointInfo(idList);
    }










}