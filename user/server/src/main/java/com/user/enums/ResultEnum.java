package com.user.enums;

import lombok.Getter;

/**
 * Created by Hangqi Yu
 * 2019-10-10 17:32
 */
@Getter
public enum ResultEnum {
    LOGIN_FAIL(101, "fail to login,check your password and email"),
    NOT_VERIFY(102,"please verify"),
    ROLE_ERROR(103, "角色权限有误"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
