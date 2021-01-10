package com.als.mall.service;

import org.springframework.stereotype.Service;

import com.als.mall.entity.Product;
import com.als.mall.mapper.ProductMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> {

}
