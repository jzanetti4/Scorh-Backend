package com.example.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

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
    @Transactional
	public String sendHtml(String toUser,String code) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        String hostAddr=getHostIp();
        String authLink="http://"+hostAddr+":10001/register/regActive?code=" + code + "";
        String htmlMsg = "<a href="+authLink+"><h3>click here to verify your Scorh account</h3>";
        mimeMessage.setFrom("hangqiy@163.com");
        mimeMessage.setSubject("scorh check");

        Multipart mp = new MimeMultipart();
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(htmlMsg, "text/html");
        mp.addBodyPart(htmlPart);
        mimeMessage.setContent(mp);

        // 接收者
        mimeMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse(toUser));
        System.out.println("reciever is"+toUser);

        javaMailSender.send(mimeMessage);
        return "success";
    }

	public String send(String toUser, String code) {
	    try {

            // 建立邮件消息
            SimpleMailMessage mainMessage = new SimpleMailMessage();

            // 发送者
            mainMessage.setFrom("hangqiy@163.com");

            // 接收者
            System.out.println("reciever is"+toUser);
            mainMessage.setTo(toUser);

            // 发送的标题
            mainMessage.setSubject("scorh check");



            String hostAddr=getHostIp();
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


    private static String getHostIp(){
        try{
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()){
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && ip.getHostAddress().indexOf(":")==-1){
                        System.out.println("本机的IP = " + ip.getHostAddress());
                        return ip.getHostAddress();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
