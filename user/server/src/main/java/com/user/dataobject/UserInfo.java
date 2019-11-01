package com.user.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Hangqi Yu
 * 2019-10-01 21:41
 */
@Data
@Entity
public class UserInfo {

	@Id
	private String id;

	private String username;

	private String password;

	private String openid;

	private Integer role;
}
