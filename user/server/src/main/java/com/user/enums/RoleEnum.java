package com.user.enums;

import lombok.Getter;

/**
 * Created by Hangqi Yu
 * 2019-10-01 23:23
 */
@Getter
public enum RoleEnum {
	BUYER(1, "买家"),
	SELLER(2, "卖家"),
	;

	private Integer code;

	private String message;

	RoleEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
