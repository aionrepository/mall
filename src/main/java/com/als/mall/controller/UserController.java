package com.als.mall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.als.mall.entity.User;
import com.als.mall.enums.GenderEnum;
import com.als.mall.service.CartService;
import com.als.mall.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final String User = null;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/login")
	public String login(@RequestParam("loginName")String loginName, 
			@RequestParam("password")String password, HttpSession session) {
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("login_name", loginName);
		queryWrapper.eq("password", password);
		User user = userService.getOne(queryWrapper);
		if(user == null) {
			return "login";
		} else {
			// 设置genderCode
			if(user.getGender() == GenderEnum.FEMALE) {
				user.setGenderCode(0);
			} else {
				user.setGenderCode(1);
			}
			// 创建session会话
			session.setAttribute("user", user);
			// 登录成功，重定向到productCategory/list
			return "redirect:/welcome";
		}
	}
	
	@PostMapping("/register")
	public String register(User user, Model model) {
		model = userService.validator(user);
		return (boolean)model.getAttribute("success") ? "login": "register";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	@RequestMapping("/userInfo")
	public ModelAndView userInfo(HttpSession session) {
		User user = (User)session.getAttribute("user");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userInfo");
		modelAndView.addObject("cartList", cartService.getAllCartVOByUserId(user.getId()));
		return modelAndView;
	}
	
	/**
	 * 上传头像
	 * @param img
	 * @param session
	 * @return
	 */
	@PostMapping("/headerImg")
	public String headerImg(@RequestParam("headerImg")MultipartFile img, HttpSession session ) {
		if(!img.isEmpty()) {
			User user = (User)session.getAttribute("user");
			userService.saveHeaderImg(user, img);
		}
		return "redirect:/user/userInfo";
	}
}
