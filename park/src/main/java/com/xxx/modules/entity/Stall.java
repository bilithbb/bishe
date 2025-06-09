package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 停车位
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-02-26
 */
@Data
@TableName("`stall`")
@ApiModel(value="stall对象", description="停车位")
public class Stall {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 编号
     */
	@ApiModelProperty(value = "编号")
	private String stallNumber;
    /**
     * 名称
     */
	@ApiModelProperty(value = "名称")
	private String stallName;
    /**
     * 分类
     */
	@ApiModelProperty(value = "分类")
	private Integer sortId;
    /**
     * 楼层位置
     */
	@ApiModelProperty(value = "楼层位置")
	private String location;
    /**
     * 简介
     */
	@TableField(value = "`desc`")
	@ApiModelProperty(value = "简介")
	private String desc;
    /**
     * 内容
     */
	@ApiModelProperty(value = "内容")
	private String content;
    /**
     * 状态
     */
	@ApiModelProperty(value = "状态")
	private Integer status;
    /**
     * 是否推荐
     */
	@ApiModelProperty(value = "是否推荐")
	private Integer isBanner;
    /**
     * 图片
     */
	@ApiModelProperty(value = "图片")
	private String imageUrl;
    /**
     * 访问量
     */
	@ApiModelProperty(value = "访问量")
	private Integer count;
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
	 * 分类名
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "分类名")
	private String sortName;

	private Double price;

	@TableField(exist = false)
	private List<Integer> idList = new ArrayList<>();

}
