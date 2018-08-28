package com.game.service;

import com.game.domain.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface UserService {
	
	// 有四个接口,1000代表正确，1001代表会出错
	// 进行注册操作
	int regist(String email, String password);
	
	// 检查用户的邮箱是否重复
	int checkEmail(String email);
	
	// 激活邮箱
	int activateEmail(String email, String activecode);
	
	// 进行用户资料补充
	int completeUserMaterial(User user, CommonsMultipartFile headimg);

	// 根据用户的邮箱重置密码,用户点击代resetPrePassword.do接口，然后邮箱发送邮件，
	// 然后用户点击邮箱里面的链接，进入tipPage.do接口，根据邮箱和resetcode，如果都是
	// 对的，就执行跳转操作，跳转到修改密码的界面，点击到resetPassword.do界面，
	// 根据changepassword界面对password进行重新赋值
	int resetprePassword(String email);
	User tipPage(String email, String resetcode);
	int resetPassword(String email, String password);
}
