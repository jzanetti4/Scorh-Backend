package com.user.service.impl;


import com.user.dataobject.User;
import com.user.dataobject.UserInfo;
import com.user.reponsitory.UserInfoRepostory;
import com.user.reponsitory.UserRepository;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 廖师兄
 * 2018-03-04 21:45
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoRepostory repostory;


	@Autowired
	private UserRepository userRepository;
	/**
	 * 通过openid来查询用户信息
	 *
	 * @param openid
	 * @return
	 */
	@Override
	public UserInfo findByOpenid(String openid) {
		return repostory.findByOpenid(openid);
	}

	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> findAllByEmailAddress(String emailAddress) {
		return userRepository.findAllByEmail(emailAddress);
	}

	@Override
	public User findByEmailAddress(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByEmailAndPwd(String email, String pwd) {
		return userRepository.findByEmailAndPassword(email,pwd);
	}



}
