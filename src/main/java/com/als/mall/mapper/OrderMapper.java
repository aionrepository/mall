package com.als.mall.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.als.mall.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
