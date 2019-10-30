package com.example.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

/**
 * 发送邮件的工具类
 * @author duan ss
 *
 */
@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * 发送邮件
	 * @param toUser	收件人
	 * @param code		激活码
	 * @return
	 */
	public String send(String toUser, String code) {
	    try {

            // 建立邮件消息
            SimpleMailMessage mainMessage = new SimpleMailMessage();

            // 发送者
            mainMessage.setFrom("hangqiy@163.com");

            // 接收者
            mainMessage.setTo(toUser);

            // 发送的标题
            mainMessage.setSubject("verify your registration");

            InetAddress addr = InetAddress.getLocalHost();

            String hostAddr=addr.getHostAddress();
            // 发送的内容
            System.out.println(hostAddr);
            mainMessage.setText("please click the following address to verify：http://"+hostAddr+":10001/register/regActive?code=" + code + "");
            javaMailSender.send(mainMessage);
            return "success";
        }catch (Exception e){
	        System.out.println(e.getMessage());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
	    return null;
	}


}
