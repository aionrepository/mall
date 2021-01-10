package com.als.mall.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.als.mall.entity.User;
import com.als.mall.enums.GenderEnum;
import com.als.mall.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 *	继承ServiceImpl<M extends BaseMapper<T>, T>,
 *	该类实现了IService<T>接口；
 * @author 艾霖森
 */
@Slf4j
@Service
public class UserService extends ServiceImpl<UserMapper, User>{
	
	@Autowired
	private UserMapper userMapper;
	
	public Model validator(User user) {
		Model model = new BindingAwareModelMap();
		model.addAttribute("success", false);
		log.debug("性别添加");
		if(user.getGenderCode() == 1) {
			user.setGender(GenderEnum.MALE);
		} else if (user.getGenderCode() == 0) {
			user.setGender(GenderEnum.FEMALE);
		} else {
			return model;
		}
		log.debug(user.toString());
		log.debug("开始注册");
		try {
			save(user);
			 model.addAttribute("success", true);
			 log.debug("{}注册成功", user.getLoginName());
		} catch (Exception e) {
			model.addAttribute("error", user.getLoginName() + "已存在！");
			log.debug("注册失败");
		}
		log.debug("注册结束");
		return model;
	}
	
	public boolean saveHeaderImg(User user, MultipartFile img) {
		// 获取文件名
		String fileName = img.getOriginalFilename();
		String[] strings = fileName.split("\\.");
		// 获取后缀，拼接新的文件名
		fileName = String.valueOf(user.getId())+ "." + strings[strings.length-1];
		try {
			// ResourceUtils.getURL("classpath:")获取到target/classes的绝对路径
			// 将路径设置为static下的images文件夹
			// 因为是保存类路径下的static中，每次上传都需要手动刷新项目，否则在浏览器中无法；获取资源
			String realPath = ResourceUtils.getURL("classpath:").getPath() + "../../src/main/resources/static/images/";
			
			// 项目部署时使用下面变量
//			String realPath = ResourceUtils.getURL("classpath:").getPath() + "/static/images/";
			log.debug(realPath);
			// 存储上传的头像
			img.transferTo(new File(realPath+ fileName));
			log.debug(fileName);
			user.setFileName(fileName);
			userMapper.updateById(user);
			return true;
		} catch (IOException e) {
			log.debug("头像保存失败");
			return false;
		}
	}
}
