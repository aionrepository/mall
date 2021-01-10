package com.als.mall.mapper;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.als.mall.entity.Cart;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class CartMapperTests {

	@Autowired
	private CartMapper cartMapper;
	
	@Test
	void test() {
		Cart cart = new Cart();
		cart.setUserId(27);
		cart.setCost(157f);
		cart.setProductId(733);
		cart.setQuantity(2);
		int i = cartMapper.insert(cart);
		log.debug(String.valueOf(i));
	}

}
