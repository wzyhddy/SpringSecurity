package com.sparrow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/17 22:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "sys_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;

	private String userName;

	private String nickName;

	private String password;

	private String status;

	private String email;

	private String phone;

	private String sex;

	private String avatar;

	private String userType;

	private Long createBy;

	private Date createTime;

	private Date updateTime;

	private Integer delFlag;


}
