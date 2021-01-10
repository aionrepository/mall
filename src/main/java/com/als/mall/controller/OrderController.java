package com.als.mall.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.als.mall.entity.Order;
import com.als.mall.entity.User;
import com.als.mall.service.CartService;
import com.als.mall.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @PostMapping("/settlement3")
    public ModelAndView settlement3(Order order, HttpSession session,String address,String remark){
    	log.debug(order.toString());
    	log.debug(address);
    	log.debug(remark);
        User user = (User)session.getAttribute("user");
        orderService.save(order,user,address,remark);
        log.debug("订单已保存");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement3");
        modelAndView.addObject("cartList",cartService.getAllCartVOByUserId(user.getId()));
        log.debug("成功获取");
        modelAndView.addObject("orders",order);
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderList");
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("list",orderService.findAllOrederVOByUserId(user.getId()));
        modelAndView.addObject("cartList",cartService.getAllCartVOByUserId(user.getId()));
        return modelAndView;
    }
}

