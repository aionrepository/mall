package com.als.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class ProductCategory {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String name;
}
