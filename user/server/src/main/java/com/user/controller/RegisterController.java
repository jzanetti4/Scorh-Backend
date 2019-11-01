package com.user.controller;


import VO.ResultVO;
import com.user.constant.CookieConstant;
import com.user.constant.RedisConstant;
import com.user.dataobject.User;
import com.user.dataobject.UserInfo;
import com.user.enums.ResultEnum;
import com.user.enums.RoleEnum;
import com.user.userForm.RegisterForm;
import com.user.service.UserService;
import com.user.utils.CookieUtil;
import com.user.utils.FormToUser;
import com.user.utils.JsonUtil;
import com.user.utils.ResultVOUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * controller to handle the register request
 * @description: RegisterController
 * @author: Hangqi Yu
 * @date: Created in 2019-10-10 16:21
 * @version: V1.0
 * @modified: Hangqi Yu
 */
@RestController
@RequestMapping("/register")
public class RegisterController {


	@Autowired
	private UserService userService;

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * publish the message to rabbitmq for invoke
	 * @param registerForm
	 * @return
	 */
	@PostMapping("/initReg")
    @Transactional
	public String initReg( RegisterForm registerForm){
		User result=userService.findByEmailAddress(registerForm.getEmail());
		if(result!=null){
			return "email has already been registed";
		}
		amqpTemplate.convertAndSend("emailService", JsonUtil.toJson(registerForm.getEmail()));
		User user= FormToUser.covertToUser(registerForm);
		System.out.println(user);
		userService.addUser(user);
		return "email has been sent";
	}


	/**
	 * handle for the confirmation of the validation code
	 * @param code
	 * @return
	 */
	@GetMapping("/regActive")
	public String regActive(@RequestParam("code") String code) {
		System.out.println("got from phone");
		System.out.println(code);
		String emailAddress=stringRedisTemplate.opsForValue().get(code);
		if (StringUtils.isEmpty(emailAddress)){
			return "invaild code";
		}

		User user=userService.findByEmailAddress(emailAddress);
		if(user.getIsActive()==1){
			return "already verify";
		}
		else if(user.getIsActive()==0){
			user.setIsActive(1);
			userService.addUser(user);
			return "success verify";
		}
		return "fail to auth ";
	}






}
