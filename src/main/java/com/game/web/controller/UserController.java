package com.game.web.controller;

import com.game.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.common.JsonModel;
import com.game.service.UserService;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	/*
	 * 只需要邮箱和密码：
	 * 	注册功能
	 * 	邮箱是否重复
	 * 	邮箱激活
	 * 	完善用户资料
	 * 	忘记密码的操作
	 */
	/**
	 * 执行注册操作，根据邮箱和密码进行注册
	 * @param email,用户的注册邮箱名
	 * @param password， 用户的密码设置
	 * @return 返回值1000，代表 成功，1001代表不成功
	 */
	@RequestMapping("regist.do")
	@ResponseBody
	public JsonModel<User> regist(String email, String password) {

		JsonModel<User> resultJsonModel = new JsonModel<>();

		int registResult = userService.regist(email, password);

		if (registResult > 0) {
			resultJsonModel.setCode(1000);
			resultJsonModel.setMsg("注册成功请激活！");
		} else {
			resultJsonModel.setCode(1001);
			resultJsonModel.setMsg("注册失败！");
		}
		return resultJsonModel;
	}
	
	/**
	 * 检查用户注册的邮箱是否重复
	 * @param email,邮箱名
	 * @return
	 */
	@RequestMapping("checkEmail.do")
	@ResponseBody
	public JsonModel<User> checkEmail(String email) {
		JsonModel<User> resultJsonModel = new JsonModel<>();
		int checkEmail = userService.checkEmail(email);
		if (checkEmail == 0) {
			resultJsonModel.setCode(1000);
			resultJsonModel.setMsg("邮箱可用");
		} else {
			resultJsonModel.setCode(1001);
			resultJsonModel.setMsg("邮箱不可用");
		}
		return resultJsonModel;
	}


	// 进行邮箱激活操作
	@RequestMapping("activateEmail.do")
	@ResponseBody
	public  JsonModel checkEmail(@RequestParam("email")String email, @RequestParam("activecode") String activecode) {

		JsonModel resultModel = new JsonModel();
		int result = userService.activateEmail(email, activecode);
		if (result != 0) {
			resultModel.setCode(1000);
			resultModel.setMsg("已激活");
		} else {
			resultModel.setCode(1001);
			resultModel.setMsg("激活失败，请检查您的邮箱是否正确");
		}
		return resultModel;
	}
	/**
	 * 完善用户资料
	 * @param user
	 * @return
	 */
	@RequestMapping("completeUserMaterial.do")
	@ResponseBody
	public JsonModel<User> completeUserMaterial(User user, @RequestParam("headimg")CommonsMultipartFile headimg, HttpServletRequest request) {
		User userSession = (User) request.getSession().getAttribute("user");
		user.setId(userSession.getId());
		int i = userService.completeUserMaterial(user,headimg);
		return null;
	}

	/* 用户忘记密码的接口:
	resetprePassword.do(向用户发邮件)
	tipPage.do（邮箱会跳转到该接口，进行邮箱和resetcode码的校验，然后跳转到html文件）
	resetPassowrd.do（根据前面的页面传过来阿德数据，更新用户的密码）
	*/

	@RequestMapping("resetprePassword.do")
	@ResponseBody
	public JsonModel resetprePassword(String email) {
		JsonModel<Object> resultJsonModel = new JsonModel<>();
		int i = userService.resetprePassword(email);
		if (i == 0) {
			resultJsonModel.setCode(1000);
		} else {
			resultJsonModel.setCode(1001);
		}
		return resultJsonModel;
	}

	@RequestMapping("tipPage.do")
	public String tiPage(String email, String resetcode) {

		User user = userService.tipPage(email, resetcode);
		if (user == null) {
			return "error.html";
		} else {
			return "record/WEB-INF/resetPassword.html?email=" + email;
		}
	}

	@RequestMapping("resetPassword.do")
	@ResponseBody
	public JsonModel resetPassword(String email, String password) {
		JsonModel<Object> resultJsonModel = new JsonModel<>();
		int checkEmail = userService.checkEmail(email);
		if (checkEmail == 0) {
			resultJsonModel.setCode(1001);
			resultJsonModel.setMsg("用户不存在");
		} else {
			int i = userService.resetPassword(email, password);
			if (i > 0) {
				resultJsonModel.setCode(1000);
				resultJsonModel.setMsg("激活成功");
			} else {
				resultJsonModel.setCode(1001);
				resultJsonModel.setMsg("激活失败");
			}
		}
		return resultJsonModel;
	}

}