package com.game.util;


import com.game.domain.User;

import javax.mail.Flags;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

/*
 * 1
 * */
public class EmailUtils {
	public static void sendEmail(User user, int flag){
		//邮箱
		String myAccount = "18838190692@163.com";
		//授权码
		String myPass = "lxj123";
		//邮箱服务器
		String SMTPHost = "smtp.163.com";
		//邮箱设置
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");//
		prop.setProperty("mail.smtp.host", SMTPHost);//
		prop.setProperty("mail.smtp.auth", "true");//
		//1.创建会话
		Session session = Session.getDefaultInstance(prop);
		//开启开发者模式
		session.setDebug(true);
		
		//2.创建消息
		MimeMessage message = createMsg(session,myAccount,user,flag);
		//4.根据协议连接
		try {
			Transport tran = session.getTransport();
			//连接
			tran.connect(myAccount, myPass);
			//发送消息
			tran.sendMessage(message, message.getAllRecipients());
			//关闭
			tran.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	//创建邮件消息
	private static MimeMessage createMsg(Session session, String myAccount, User user,int flag) {
		//创建消息对象
		MimeMessage message = new MimeMessage(session);
		//3.组装消息
		try {
			//3.1设置发送方
			message.setFrom(new InternetAddress(myAccount, "17锋迷", "utf-8"));
			//3.2 设置接收方
            /*
			 * MimeMessage.RecipientType.TO
			 * MimeMessage.RecipientType.CC
			 * MimeMessage.RecipientType.BCC
			 * */
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(user.getEmail(), "用户", "utf-8"));
			//3.3设置主题
			message.setSubject("18伪造商城诚邀您激活使用","utf-8");
			String ip = Inet4Address.getLocalHost().getHostAddress();
			String url;
			if (flag == 0) {
				url = "http://"+ip+":8080/activateEmail.do?email="+ Base64Utils.encode(user.getEmail())+"&activecode="+Base64Utils.encode(user.getActivecode());
				//设置正文内容
				message.setContent(user.getName()+",您好：<br>欢迎您在我们18伪造商城注册会员，期待您的激活:<a href='"+url+"'>"+url+"</a>","text/html;charset=utf-8");
			} else {
				url = "http://"+ip+":8080/tipPage.do?email="+ Base64Utils.encode(user.getEmail())+"&resetcode="+Base64Utils.encode(user.getResetcode());
				//设置正文内容
				message.setContent(user.getName()+",您好：您已开始重置密码操作，请点击链接进行重置密码:<a href='"+url+"'>"+url+"</a>","text/html;charset=utf-8");
			}

			//设置发送日期
			message.setSentDate(new Date());
			//保存消息
			message.saveChanges();
		} catch (UnsupportedEncodingException | MessagingException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
}
