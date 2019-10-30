package com.user.service;


import com.user.dataobject.User;
import com.user.dataobject.UserInfo;

import java.util.List;

/**
 * Created by 廖师兄
 * 2018-03-04 21:44
 */
public interface UserService {

	/**
	 * 通过openid来查询用户信息
	 * @param openid
	 * @return
	 */
	UserInfo findByOpenid(String openid);


	void addUser(User user);

	List<User> findAllByEmailAddress(String emailAddress);

	User findByEmailAddress(String email);

	User findByEmailAndPwd(String email,String pwd);
}
