package com.user.utils;

import com.user.dataobject.User;
import com.user.userForm.RegisterForm;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;
import java.util.Date;


public class FormToUser {

    public static User covertToUser(RegisterForm registerForm){
        User user=new User();
        BeanUtils.copyProperties(registerForm, user);
        user.setIsActive(0);
        user.setCreateTime(new Timestamp(new Date().getTime()));
        user.setRole(registerForm.getSelectedType());
        user.setPassword(MD5Util.inputPassToFormPass(user.getPassword()));
        System.out.println("form to user"+user);
        return user;
    }
}
