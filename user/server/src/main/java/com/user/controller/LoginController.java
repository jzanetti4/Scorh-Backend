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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * controller to handle the login request
 * @description: LoginController
 * @author: Hangqi Yu
 * @date: Created in 2019-10-10 16:21
 * @version: V1.0
 * @modified: Hangqi Yu
 */

@RestController
@RequestMapping("/login")
public class LoginController {

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
                AuthInfo authInfo=new AuthInfo();
                BeanUtils.copyProperties(user,authInfo);
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
