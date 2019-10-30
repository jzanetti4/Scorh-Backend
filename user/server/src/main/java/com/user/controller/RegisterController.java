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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by 廖师兄
 * 2018-03-04 23:12
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

	@PostMapping("/initReg")
	public String initReg( RegisterForm registerForm){
		amqpTemplate.convertAndSend("emailService", JsonUtil.toJson(registerForm.getEmail()));
		System.out.println("收到"+ registerForm);
		User user= FormToUser.covertToUser(registerForm);
		System.out.println(user);
		userService.addUser(user);
		return registerForm.toString();
	}



	@GetMapping("/regActive")
	public String regActive(@RequestParam("code") String code) {
		System.out.println("got from phone");
		System.out.println(code);
		String emailAddress=stringRedisTemplate.opsForValue().get(code);
		if (StringUtils.isEmpty(emailAddress)){
			return "no register";
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


		/**
         * 买家登录
         * @param openid
         * @param response
         * @return
         */

	@GetMapping("/buyer")
	public ResultVO buyer(@RequestParam("openid") String openid,
						  HttpServletResponse response) {
		//1. openid和数据库里的数据是否匹配
		UserInfo userInfo = userService.findByOpenid(openid);
		if (userInfo == null) {
			return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
		}

		//2. 判断角色
		if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
			return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
		}

		//3. cookie里设置openid=abc
		CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);

		return ResultVOUtil.success();
	}

	@GetMapping("/seller")
	public ResultVO seller(@RequestParam("openid") String openid,
						  HttpServletRequest request,
						  HttpServletResponse response) {
		//判断是否已登录
		Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
		if (cookie != null &&
				!StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
			return ResultVOUtil.success();
		}

		//1. openid和数据库里的数据是否匹配
		UserInfo userInfo = userService.findByOpenid(openid);
		if (userInfo == null) {
			return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
		}

		//2. 判断角色
		if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
			return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
		}

		//3. redis设置key=UUID, value=xyz(openid)
		String token = UUID.randomUUID().toString();
		Integer expire = CookieConstant.expire;
		stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
				openid,
				expire,
				TimeUnit.SECONDS);

		//4. cookie里设置token=UUID
		CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);
		return ResultVOUtil.success();
	}

}