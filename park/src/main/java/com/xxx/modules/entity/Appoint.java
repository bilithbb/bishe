package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 预约
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
@Data
@TableName("`appoint`")
@ApiModel(value="appoint对象", description="预约")
public class Appoint {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 用户
     */
	@ApiModelProperty(value = "用户")
	private Integer userId;
    /**
     * 车位号
     */
	@ApiModelProperty(value = "车位号")
	private Integer stallId;
    /**
     * 车辆
     */
	@ApiModelProperty(value = "车辆")
	private Integer carId;
    /**
     * 开始时间
     */
	@ApiModelProperty(value = "开始时间")
	private String startTime;
    /**
     * 结束时间
     */
	@ApiModelProperty(value = "结束时间")
	private String endTime;
    /**
     * 状态
     */
	@ApiModelProperty(value = "状态")
	private Integer status;
    /**
     * 总价
     */
	@ApiModelProperty(value = "总价")
	private Double totalPrice;
    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间")
	private String createTime;
    /**
     * 更新时间
     */
	@ApiModelProperty(value = "更新时间")
	private String updateTime;
	/**
	 * 用户
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "用户")
	private String realName;
	/**
	 * 车位号
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "车位号")
	private String stallNumber;
	/**
	 * 车牌号
	 */
	@ApiModelProperty(value = "车牌号")
	private String carNumber;

	private Double score;

//	@TableField(exist = false)
	private String currentDay;

	private String fileName;

	@TableField(exist = false)
	private String cardImage;

	private Integer type;

	private Integer month;

	private String year;
}
