package com.als.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectHandler {

	/**
	 * 通过重定向获取模板
	 * @param path 路径
	 * @return
	 */
	@RequestMapping("/{path}")
	public String redirct(@PathVariable("path")String path) {
		return path;
	}
	
	@RequestMapping("/")
	public String welcom() {
		return "redirect:/welcome";
	} 
	
	@RequestMapping("/productCategory/list")
	public String toWelcome() {
		return "redirect:/welcome";
	}
}
