package com.xxx.modules.controller;

import com.xxx.modules.entity.Leave;
import com.xxx.modules.service.LeaveService;
import com.xxx.modules.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

;

/**
 * 留言
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-11-07
 */
@RestController
@RequestMapping("/common/leave")
@Api(tags="留言")
public class    LeaveController {
    @Autowired
    private LeaveService leaveService;

    /**
    *  获取所有留言
    * @param pageNum
    * @param pageSize
    * @param leave
    * @return
    */

    @GetMapping("/getLeaveList")
    @ApiOperation("获取所有留言")
    public Result getLeaveList(Leave leave, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                               @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return leaveService.selectLeaveList(leave,pageNum,pageSize);
    }


    @GetMapping("/getLeaveInfo")
    @ApiOperation("根据id获取单个留言")
    public Result getLeaveInfo(Integer id){
        return leaveService.selectLeaveInfo(id);
    }

    /**
    * 保存留言
    * @param leave
    * @return
    */
    @PostMapping("/saveLeaveInfo")
    @ApiOperation("保存留言")
    public Result saveLeaveInfo(@RequestBody Leave leave){
        return leaveService.saveLeaveInfo(leave);
    }


    /**
     * 更新留言
     * @param leave
     * @return
     */

    @PutMapping("/updateLeaveInfo")
    @ApiOperation("更新留言")
    public Result updateLeaveInfo(@RequestBody Leave leave){
        return leaveService.updateLeaveInfo(leave);
    }

    /**
     * 根据id删除留言
     * @param id
     * @return
     */
    @DeleteMapping("/delLeaveInfo")
    @ApiOperation("根据id删除留言")
    public Result delLeaveInfo(Integer id){
        return leaveService.delLeaveInfo(id);
    }

    /**
     * 根据id集合批量删除留言
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchLeaveInfo")
    @ApiOperation("根据id集合批量删除留言")
    public Result delBatchLeaveInfo(String idList){
        return leaveService.delBatchLeaveInfo(idList);
    }






}