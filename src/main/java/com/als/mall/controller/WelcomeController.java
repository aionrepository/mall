package com.als.mall.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.als.mall.entity.User;
import com.als.mall.service.CartService;

@Controller
public class WelcomeController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/welcome")
    public ModelAndView list(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        User user = (User)session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{
            modelAndView.addObject("cartList",cartService.getAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }

}

