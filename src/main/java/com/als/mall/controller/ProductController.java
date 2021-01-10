package com.als.mall.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.als.mall.entity.Product;
import com.als.mall.entity.User;
import com.als.mall.service.CartService;
import com.als.mall.service.ProductService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
    private ProductService productService;
	
    @Autowired
    private CartService cartService;

    @PostMapping("/findByKey")
    public ModelAndView findByKey(String keyWord,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        //根据关键字查询
        QueryWrapper<Product> wrapper = new QueryWrapper<Product>();
        wrapper.like("name",keyWord);
        modelAndView.addObject("productList",productService.list(wrapper));
        User user = (User)session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{
            modelAndView.addObject("cartList",cartService.getAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") Integer id,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productDetail");
        modelAndView.addObject("product",productService.getById(id));
        User user = (User)session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{
            modelAndView.addObject("cartList",cartService.getAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }
    
    @GetMapping("/list/{id}")
    public ModelAndView list(
            @PathVariable("id") Integer id,
            HttpSession session
    ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        QueryWrapper<Product> wrapper = new QueryWrapper<Product>();
        wrapper.eq("category_id", id);
        modelAndView.addObject("productList", productService.list(wrapper));
        User user = (User)session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{
            modelAndView.addObject("cartList",cartService.getAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }
}
