package com.als.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class Product {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String name;
	private String description;
	private Float price;
	private Integer Stock;
	private Integer categoryId;
	private String fileName;
}
