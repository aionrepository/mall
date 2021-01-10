package com.als.mall.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.als.mall.entity.Cart;
import com.als.mall.entity.Order;
import com.als.mall.entity.OrderDetail;
import com.als.mall.entity.Product;
import com.als.mall.entity.User;
import com.als.mall.entity.UserAddress;
import com.als.mall.mapper.CartMapper;
import com.als.mall.mapper.OrderDetailMapper;
import com.als.mall.mapper.OrderMapper;
import com.als.mall.mapper.ProductMapper;
import com.als.mall.mapper.UserAddressMapper;
import com.als.mall.vo.OrderDetailVO;
import com.als.mall.vo.OrderVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private UserAddressMapper userAddressMapper;
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	public boolean save(Order order, User user,String address,String remark) {
        //判断是新地址还是老地址
        if(order.getUserAddress().equals("newAddress")){
            //存入数据库
            UserAddress userAddress = new UserAddress();
            userAddress.setAddress(address);
            userAddress.setRemark(remark);
            userAddress.setIsDefault(1);
            userAddress.setUserId(user.getId());
            
            // 更改默认地址，添加新地址
            QueryWrapper<UserAddress> wrapper = new QueryWrapper<>();
            wrapper.eq("isdefault",1);
            UserAddress oldDefault = userAddressMapper.selectOne(wrapper);
            if(oldDefault != null) {
            	oldDefault.setIsDefault(0);
                userAddressMapper.updateById(oldDefault);
            }
            userAddressMapper.insert(userAddress);
            order.setUserAddress(address);
        }
        //存储orders
        order.setUserId(user.getId());
        order.setLoginName(user.getLoginName());
        String seriaNumber = null;
        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<32;i++) {
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            seriaNumber =  result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        order.setSerialnumber(seriaNumber);
        log.debug(order.toString());
        orderMapper.insert(order);

        //存储ordersdetail
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",user.getId());
        List<Cart> cartList = cartMapper.selectList(wrapper);
        for (Cart cart : cartList) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart,orderDetail);
            orderDetail.setId(null);
            orderDetail.setOrderId(order.getId());
            orderDetailMapper.insert(orderDetail);
        }

        //清空购物车
        QueryWrapper<Cart> wrapper1 = new QueryWrapper<Cart>();
        wrapper1.eq("user_id",user.getId());
        cartMapper.delete(wrapper1);
        return true;
    }
	
	public List<OrderVO> findAllOrederVOByUserId(Integer id) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        List<Order> ordersList = orderMapper.selectList(wrapper);
        List<OrderVO> orderVOList = ordersList.stream()
                .map(e -> new OrderVO(
                        e.getId(),
                        e.getLoginName(),
                        e.getSerialnumber(),
                        e.getUserAddress(),
                        e.getCost()
                )).collect(Collectors.toList());
        //封装OrderDetail
        for (OrderVO orderVO : orderVOList) {
            QueryWrapper<OrderDetail> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("order_id",orderVO.getId());
            List<OrderDetail> orderDetailList = orderDetailMapper.selectList(wrapper1);
            List<OrderDetailVO> orderDetailVOList = new ArrayList<>();
            for (OrderDetail orderDetail : orderDetailList) {
                OrderDetailVO orderDetailVO = new OrderDetailVO();
                Product product = productMapper.selectById(orderDetail.getProductId());
                BeanUtils.copyProperties(product,orderDetailVO);
                BeanUtils.copyProperties(orderDetail,orderDetailVO);
                orderDetailVOList.add(orderDetailVO);
            }
            orderVO.setOrderDetailVOList(orderDetailVOList);
        }
        return orderVOList;
    }
	
}
