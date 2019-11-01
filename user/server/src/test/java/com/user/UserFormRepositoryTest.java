package com.user;

import com.user.dataobject.User;
import com.user.reponsitory.UserRepository;
import com.user.userForm.AuthInfo;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

@Component
public class UserFormRepositoryTest extends UserFormApplicationTests {
    @Autowired
    private UserRepository userRepository;


    @Test
    public void insert(){
        User user=new User();
        user.setEmail("yhq19950826@qq.com");
        user.setIsActive(1);
        user.setPassword("1");
        user.setRole("1");
        user.setUsername("1");
        user.setCreateTime(new Timestamp(new Date().getTime()));
        User a=userRepository.save(user);
    }

    @Test
    public void findbyId(){
        List<User> result=userRepository.findAllByEmail("1245414819@qq.com");
//        assertTrue(result != null);
        System.out.println(result);
    }

    @Test
    public void findByEmailAndPassword(){
        User user=userRepository.findByEmailAndPassword("2509622015@qq.com","77c21c9ea568d95ff2e120a2bd241283");
        System.out.println(user);

    }


    @Test
    public void findAll(){
//        List<User> result=userRepository.findAll();

        List<AuthInfo> result=userRepository.findAll()
                .stream()
                .map(e -> {
                    AuthInfo authInfo = new AuthInfo();
                    BeanUtils.copyProperties(e, authInfo);
                    return authInfo;
                })
                .collect(Collectors.toList());

        System.out.println(result);

    }




}