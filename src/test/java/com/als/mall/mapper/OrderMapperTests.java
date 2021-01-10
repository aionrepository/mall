package com.als.mall.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.als.mall.entity.Order;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
class OrderMapperTests {

	@Autowired
	private OrderMapper orderMapper;
	
	@Test
	void test() {
		Order order = new Order();
		order.setUserId(27);
		order.setUserAddress("张三家");
		order.setCost(755f);
		order.setLoginName("zs");
		order.setSerialnumber("sadfsadf");
		orderMapper.insert(order);
	}

}
