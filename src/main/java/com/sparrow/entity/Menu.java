package com.sparrow.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/19 10:29
 **/
@TableName(value = "sys_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;

	private String menuName;

	private String path;

	private String component;

	private String visible;

	private String status;

	private String perms;

	private String icon;

	private Long createBy;

	private Date createTime;

	private Long updateBy;

	private Date updateTime;

	private Integer delFlag;

	private String remark;


}
