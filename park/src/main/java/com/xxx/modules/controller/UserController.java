package com.xxx.modules.controller;;
import com.xxx.modules.service.UserService;
import com.xxx.modules.entity.User;
import com.xxx.modules.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-11-30
 */
@RestController
@RequestMapping("/common/user")
@Api(tags="用户")
public class UserController {
    @Autowired
    private UserService userService;

    /**
    *  获取所有用户
    * @param pageNum
    * @param pageSize
    * @param user
    * @return
    */

    @GetMapping("/getUserList")
    @ApiOperation("获取所有用户")
    public Result getUserList(User user, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return userService.selectUserList(user,pageNum,pageSize);
    }


    @GetMapping("/getUserInfo")
    @ApiOperation("根据id获取单个用户")
    public Result getUserInfo(Integer id){
        return userService.selectUserInfo(id);
    }

    /**
    * 保存用户
    * @param user
    * @return
    */
    @PostMapping("/saveUserInfo")
    @ApiOperation("保存用户")
    public Result saveUserInfo(@RequestBody User user){
        return userService.saveUserInfo(user);
    }


    /**
     * 更新用户
     * @param user
     * @return
     */

    @PutMapping("/updateUserInfo")
    @ApiOperation("更新用户")
    public Result updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/delUserInfo")
    @ApiOperation("根据id删除用户")
    public Result delUserInfo(Integer id){
        return userService.delUserInfo(id);
    }

    /**
     * 根据id集合批量删除用户
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchUserInfo")
    @ApiOperation("根据id集合批量删除用户")
    public Result delBatchUserInfo(String idList){
        return userService.delBatchUserInfo(idList);
    }

    /**
     * 登录接口(根据用户名和密码)
     * @return
     */

    @PostMapping("/login")
    @ApiOperation("用户登录接口")
    public Result login(User user){
        return userService.login(user);
    }



    /**
    *
    * @param user
    * @return
     */
    @PostMapping("/updatePassword")
    @ApiOperation("用户更新密码接口")
    public Result updatePassword(@RequestBody User user){
       return userService.updatePassword(user);
    }

    @PutMapping("/userRecharge")
    @ApiOperation("用户更新密码接口")
    public Result  userRecharge(@RequestBody User user){
        return userService.userRecharge(user);
    }



    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/updateBatchTime")
    public Result updateBatchTime(){
        String s ="SELECT `table_name`,`table_comment` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = DATABASE()";
        List<Map<String, Object>> tableList = jdbcTemplate.queryForList(s);
        System.out.println(tableList);
        for (Map<String, Object> map:tableList){
            try {
                String table_name = String.valueOf(map.get("table_name"));
                String createStr = "UPDATE `"+table_name+"` set create_time = "+ "'"+TimeUtil.getCurrentTime()+"'";
                jdbcTemplate.execute(createStr);

                String updateStr = "UPDATE `"+table_name+"` set update_time = "+ "'"+TimeUtil.getCurrentTime()+"'";
                jdbcTemplate.execute(updateStr);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return ResultUtil.success(1,"正常",null);
    }




}