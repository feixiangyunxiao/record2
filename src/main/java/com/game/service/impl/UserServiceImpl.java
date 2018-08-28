package com.game.service.impl;

import com.game.domain.User;
import com.game.mapper.UserMapper;
import com.game.util.EmailUtils;
import com.game.util.RandomUtils;
import com.game.util.ShiroCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.service.UserService;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public int regist(String email, String password) {

		// 先进行校验，邮箱是否会重复，如果重复就直接返回，如果不重复就开始下面的曹邹
		int result;
		User userCheck = userMapper.selectByEmail(email);
		if (userCheck != null) {
			result = 0;
		} else {
			// 创建user对象进行保存操作
			User user = new User();

			// 设置用户的邮箱，并对密码进行加盐的操作，保护密码
			user.setEmail(email);
			String md5Password = ShiroCheckUtil.md5(password);
			user.setPassword(md5Password);

			// 发送激活码操作
			String activecode = RandomUtils.createActive();
			user.setActivecode(activecode);
			EmailUtils.sendEmail(user,0);

			user.setActiveFlag(1);
			// 执行添加操作
			result = userMapper.insertSelective(user);

		}
		return result;
	}

	/**
	 * 检查邮箱是否重复
	 * @param email，用户的邮箱名
	 * @return 返回0代表没有此用户，返回1代表有此用户
	 */
	@Override
	public int checkEmail(String email) {
		User user = userMapper.selectByEmail(email);
		if (user == null) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public int activateEmail(String email, String code) {
		int i = userMapper.updateByEmail(email, code);
		return i;
	}

	@Override
	public int completeUserMaterial(User user, CommonsMultipartFile headimg) {
		try {
			// 保存图片到服务器本地
			String suffix = headimg.getOriginalFilename().substring(headimg.getOriginalFilename().lastIndexOf("."));
			String prefix = UUID.randomUUID().toString().replace("-","");
			String fileName = prefix + suffix;
			user.setPhoto(fileName);
			headimg.transferTo(new File("F:/recordphoto/" + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		int i = userMapper.updateByPrimaryKeySelective(user);

		return i;
	}

	// 进行用户重置密码的操作

	@Override
	public int resetprePassword(String email) {
		// 根据用户邮箱进行发送邮件操作，还是先要进行邮箱的验证
		User user = userMapper.selectByEmail(email);
		if (user == null) {
			return 1;
		} else {
			String resetcode = RandomUtils.createActive();
			user.setResetcode(resetcode);
			EmailUtils.sendEmail(user,1);
			userMapper.updateByPrimaryKey(user);
			return 0;
		}
	}

	@Override
	public User tipPage(String email, String resetcode) {

		User user = userMapper.selectByEmailAndResetcode(email, resetcode);

		return user;
	}

	@Override
	public int resetPassword(String email, String password) {
		int i = userMapper.updatePasswordByEmail(email, password);
		return i;
	}
}
