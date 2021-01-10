package com.als.mall.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.als.mall.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface UserMapper extends BaseMapper<User>{

}
