package com.als.mall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.als.mall.entity.Cart;
import com.als.mall.entity.User;
import com.als.mall.entity.UserAddress;
import com.als.mall.service.CartService;
import com.als.mall.service.UserAddressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserAddressService userAddressService;
	
	@RequestMapping("/add/{productId}/{price}/{quantity}")
	public String add(@PathVariable("productId")Integer productId,
						@PathVariable("price")Float price, 
						@PathVariable("quantity")Integer quantity, 
						HttpSession session) {
		
		Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setCost(price*quantity);
        User user = (User) session.getAttribute("user");
        cart.setUserId(user.getId());
        try {
            if(cartService.save(cart)){
                return "redirect:/cart/findAllCart";
            }
        } catch (Exception e) {
        	e.printStackTrace();
            return "redirect:/welcome";
        }
        return null;
	}
	
	@GetMapping("/findAllCart")
	public ModelAndView findAll(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement1");
        User user = (User)session.getAttribute("user");
        modelAndView.addObject("cartList",cartService.getAllCartVOByUserId(user.getId()));
        return modelAndView;
	}
	
	@GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") Integer id){
        cartService.removeById(id);
        return "redirect:/cart/findAllCart";
    }

    @GetMapping("/settlement2")
    public ModelAndView settlement2(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement2");
        User user = (User)session.getAttribute("user");
        modelAndView.addObject("cartList",cartService.getAllCartVOByUserId(user.getId()));
        QueryWrapper<UserAddress> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",user.getId());
        List<UserAddress> list = userAddressService.list(wrapper);
        modelAndView.addObject("addressList", list);
        log.debug("{}",list);
        return modelAndView;
    }

    @PostMapping("/update/{id}/{quantity}/{cost}")
    @ResponseBody
    public String updateCart(
            @PathVariable("id") Integer id,
            @PathVariable("quantity") Integer quantity,
            @PathVariable("cost") Float cost
    ){
        Cart cart = cartService.getById(id);
        cart.setQuantity(quantity);
        cart.setCost(cost);
        if(cartService.updateById(cart)){
            return "success";
        }else{
            return "fail";
        }
    }
}
