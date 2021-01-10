package com.als.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class OrderDetail {
	  @TableId(type = IdType.AUTO)
      private Integer id;
      private Integer orderId;
      private Integer productId;
      private Integer quantity;
      private Float cost;
}
