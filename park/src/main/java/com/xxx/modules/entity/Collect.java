package com.xxx.modules.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 收藏
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2024-03-27
 */
@Data
@TableName("`collect`")
@ApiModel(value="collect对象", description="收藏")
public class Collect {

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
     * 广告
     */
	@ApiModelProperty(value = "广告")
	private Integer advertId;
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
	 * 广告
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "广告")
	private String title;

}
