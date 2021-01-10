package com.als.mall.service;

import org.springframework.stereotype.Service;

import com.als.mall.entity.OrderDetail;
import com.als.mall.mapper.OrderDetailMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class OrderDetailservice extends ServiceImpl<OrderDetailMapper, OrderDetail> {

}
