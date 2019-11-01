package com.user.userForm;

import lombok.Data;

/**
 * Created by Hangqi Yu
 * 2019-10-01 21:41
 */
@Data
public class RegisterForm {


	private String username;
	private String password;
	private String email;
	private String selectedType;

	public String toString(){
		return username+email+password+selectedType;
	}
}
