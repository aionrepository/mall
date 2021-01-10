package com.als.mall.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.als.mall.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
