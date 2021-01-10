package com.als.mall.handler.fillhandler;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

@Component
public class LocalDateTimeFillHandler implements MetaObjectHandler{

	/**
	 * 在插入资源时，将当前时间赋值给createTime、updateTime属性
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
		this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
	}

	/**
	 * 在更新资源时，将当前时间赋值给updateTime属性
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
	}

}
