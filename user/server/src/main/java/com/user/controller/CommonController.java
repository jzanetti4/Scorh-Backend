package com.user.controller;


import com.user.reponsitory.UserRepository;
import com.user.userForm.AuthInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * controller to handle the common request
 * @description: CommonController
 * @author: Hangqi Yu
 * @date: Created in 2019-10-10 16:21
 * @version: V1.0
 * @modified: Hangqi Yu
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/findAllUsers")
    public List<AuthInfo> findByProductIdIn() {
       return userRepository.findAll()
                .stream()
                .map(e -> {
                    AuthInfo authInfo = new AuthInfo();
                    BeanUtils.copyProperties(e, authInfo);
                    return authInfo;
                })
                .collect(Collectors.toList());
    }
}
