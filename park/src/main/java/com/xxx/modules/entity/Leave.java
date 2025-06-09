package com.xxx.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 留言
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-11-07
 */
@Data
@TableName("`leave`")
@ApiModel(value="leave对象", description="留言")
public class Leave {

    /**
     * 主键id
     */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "主键id")
	private Integer id;
    /**
     * 留言内容
     */
	@ApiModelProperty(value = "留言内容")
	private String leaveMessage;
    /**
     * 回复信息
     */
	@ApiModelProperty(value = "回复信息")
	private String replyMessage;
    /**
     * 留言人
     */
	@ApiModelProperty(value = "留言人")
	private Integer userId;
    /**
     * 滚动速度
     */
	@ApiModelProperty(value = "滚动速度")
	private Integer time;
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
	 * 留言人
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "留言人")
	private String realName;
	/**
	 * 头像
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "头像")
	private String imageUrl;

}
