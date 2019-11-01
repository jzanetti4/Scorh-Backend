package com.user.reponsitory;


import com.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Hangqi Yu
 * 2019-10-01 21:42
 */
public interface UserInfoRepostory extends JpaRepository<UserInfo, String> {

	UserInfo findByOpenid(String openid);
}
