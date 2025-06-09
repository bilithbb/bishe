package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 汽车
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
@Data
@TableName("`car`")
@ApiModel(value="car对象", description="汽车")
public class Car {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 车牌号
     */
	@ApiModelProperty(value = "车牌号")
	private String carNumber;
    /**
     * 品牌
     */
	@ApiModelProperty(value = "品牌")
	private String brand;
    /**
     * 颜色
     */
	@ApiModelProperty(value = "颜色")
	private String color;
    /**
     * 图片
     */
	@ApiModelProperty(value = "图片")
	private String imageUrl;
    /**
     * 车型
     */
	@ApiModelProperty(value = "车型")
	private Integer type;
    /**
     * 用户
     */
	@ApiModelProperty(value = "用户")
	private Integer userId;
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

}
