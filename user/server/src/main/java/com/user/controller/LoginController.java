package com.user.controller;


import VO.ResultVO;
import com.user.constant.CookieConstant;
import com.user.constant.RedisConstant;
import com.user.dataobject.User;
import com.user.dataobject.UserInfo;
import com.user.enums.ResultEnum;
import com.user.enums.RoleEnum;
import com.user.service.UserService;
import com.user.userForm.AuthInfo;
import com.user.userForm.LoginForm;
import com.user.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController

@RequestMapping("/login")
public class LoginController {


    /**
     * ----done----
     * 1.获得帐号名+密码
     * 2.调用查找帐号名+密码服务
     * 3.查看该用户是否active
     * 4.如果激活+
     * 5.没有打回
     *
     * ---todo---
     * 1.存储cookie到redis
     * 2.如果没有获得cookie或者cookie不在redis中，返回登陆失败,客户端返回登陆界面
     * 3.登陆成功返回成功
     */

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;
    @PostMapping("/")
    public ResultVO login(LoginForm loginForm, HttpServletResponse response) {
        loginForm.setPassword(MD5Util.inputPassToFormPass(loginForm.getPassword()));
        User user=userService.findByEmailAndPwd(loginForm.getEmail(),loginForm.getPassword());
        System.out.println("login pass"+loginForm.getPassword());
        if(user!=null){
            if(user.getIsActive()==1){
                String cookieValue=String.format(RedisConstant.TOKEN_TEMPLATE,UUIDGenerator.getUUID32());
                System.out.println(user);
                AuthInfo authInfo=new AuthInfo(user.getId(),user.getUsername());
                CookieUtil.set(response, CookieConstant.TOKEN,cookieValue, CookieConstant.expire);
                stringRedisTemplate.opsForValue().set(cookieValue, JsonUtil.toJson(authInfo));
                System.out.println("cookieValue is"+cookieValue);
                return ResultVOUtil.success();
            }
            return ResultVOUtil.error(ResultEnum.NOT_VERIFY);
        }
        return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
    }

}
