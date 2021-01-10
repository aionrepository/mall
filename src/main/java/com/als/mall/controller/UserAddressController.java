package com.als.mall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.als.mall.entity.User;
import com.als.mall.entity.UserAddress;
import com.als.mall.service.CartService;
import com.als.mall.service.UserAddressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/userAddress")
public class UserAddressController {
	@Autowired
	private UserAddressService userAddressService;
	
	@Autowired
	private CartService cartService;
	
	
	@GetMapping("/list")
	public ModelAndView list(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userAddressList");
		User user = (User)session.getAttribute("user");
		modelAndView.addObject("cartList", cartService.getAllCartVOByUserId(user.getId()));
		QueryWrapper<UserAddress> wrapper = new QueryWrapper<UserAddress>();
		wrapper.eq("user_id", user.getId());
		List<UserAddress> list = userAddressService.list(wrapper);
		log.debug("{}", list);
		modelAndView.addObject("addressList", list);
		return modelAndView;
	}
}
