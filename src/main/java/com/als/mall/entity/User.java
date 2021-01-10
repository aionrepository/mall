package com.als.mall.entity;

import java.time.LocalDateTime;

import com.als.mall.enums.GenderEnum;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String loginName;
	private String userName;
	private String password;
	private GenderEnum gender;
	
	/**
	 * 	genderCode接收由浏览器端传递的性别属性
	 */
	@TableField(exist = false)
	private Integer genderCode;
	
	private String identityCode;
	private String email;
	private String mobile;
	private String fileName;
	
	/**
	 * 	插入时自动填充
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;
	
	/**
	 * 	插入或更新时，自动填充
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;
}
