package com.example.email.userForm;

import lombok.Data;

/**
 * Created by 廖师兄
 * 2018-03-04 21:41
 */
@Data
public class UserForm {


	private String username;
	private String password;
	private String email;
	private String selectedType;

	public String toString(){
		return username+email+password+selectedType;
	}
}
