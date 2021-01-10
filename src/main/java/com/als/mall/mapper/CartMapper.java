package com.als.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.als.mall.entity.Cart;
import com.als.mall.vo.CartVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface CartMapper extends BaseMapper<Cart>{
	@Select("SELECT cart.id, product_id, quantity, cost, \r\n" + 
			"	NAME, price, stock, file_name FROM cart \r\n" + 
			"		LEFT JOIN product ON product_id = product.`id`\r\n" + 
			"	WHERE user_id = #{userId}")
	List<CartVO> queryAllByUserId(@Param("userId")int userId);
}
