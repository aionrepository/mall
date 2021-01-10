package com.als.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.als.mall.entity.Cart;
import com.als.mall.entity.Product;
import com.als.mall.mapper.CartMapper;
import com.als.mall.mapper.ProductMapper;
import com.als.mall.vo.CartVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class CartService extends ServiceImpl<CartMapper, Cart> {
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	/**
	 * 通过用户id获取该用户的购物车
	 * @param userId
	 * @return
	 */
	public List<CartVO> getAllCartVOByUserId(Integer userId) {
		return cartMapper.queryAllByUserId(userId);
	}
	
	/**
	 * 添加到购物车
	 */
	public boolean save(Cart cart) {
		Product product = productMapper.selectById(cart.getProductId());
		// 判断产品是否存在
		if(product == null) {
			return false;
		}
		Integer stock = product.getStock() - cart.getQuantity();
		// 判断库存是否充足
		if(stock < 0) {
			return false;
		}
		product.setStock(stock);
		// 更新库存
		productMapper.updateById(product);
		// 添加到购物车
		if(cartMapper.insert(cart) == 1) {
			return true;
		}
		return false;
	}	
}
