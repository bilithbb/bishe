package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 广告
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-03-27
 */
@Data
@TableName("`advert`")
@ApiModel(value="advert对象", description="广告")
public class Advert {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 标题
     */
	@ApiModelProperty(value = "标题")
	private String title;
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
     * 图片
     */
	@ApiModelProperty(value = "图片")
	private String imageUrl;
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

	@TableField(exist = false)
	private Boolean isCollect = false;

	@TableField(exist = false)
	private Integer userId;

}
