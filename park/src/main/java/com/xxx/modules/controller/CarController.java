package com.xxx.modules.controller;;
import com.xxx.modules.service.CarService;
import com.xxx.modules.entity.Car;
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
 * 汽车
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
@RestController
@RequestMapping("/common/car")
@Api(tags="汽车")
public class CarController {
    @Autowired
    private CarService carService;

    /**
    *  获取所有汽车
    * @param pageNum
    * @param pageSize
    * @param car
    * @return
    */

    @GetMapping("/getCarList")
    @ApiOperation("获取所有汽车")
    public Result getCarList(Car car, @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                      @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize){
        return carService.selectCarList(car,pageNum,pageSize);
    }


    @GetMapping("/getCarInfo")
    @ApiOperation("根据id获取单个汽车")
    public Result getCarInfo(Integer id){
        return carService.selectCarInfo(id);
    }

    /**
    * 保存汽车
    * @param car
    * @return
    */
    @PostMapping("/saveCarInfo")
    @ApiOperation("保存汽车")
    public Result saveCarInfo(@RequestBody Car car){
        return carService.saveCarInfo(car);
    }


    /**
     * 更新汽车
     * @param car
     * @return
     */

    @PutMapping("/updateCarInfo")
    @ApiOperation("更新汽车")
    public Result updateCarInfo(@RequestBody Car car){
        return carService.updateCarInfo(car);
    }

    /**
     * 根据id删除汽车
     * @param id
     * @return
     */
    @DeleteMapping("/delCarInfo")
    @ApiOperation("根据id删除汽车")
    public Result delCarInfo(Integer id){
        return carService.delCarInfo(id);
    }

    /**
     * 根据id集合批量删除汽车
     * @param idList
     * @return
     */
    @DeleteMapping("/delBatchCarInfo")
    @ApiOperation("根据id集合批量删除汽车")
    public Result delBatchCarInfo(String idList){
        return carService.delBatchCarInfo(idList);
    }










}