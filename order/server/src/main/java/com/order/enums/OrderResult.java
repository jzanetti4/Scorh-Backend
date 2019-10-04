package com.order.enums;

import lombok.Getter;

@Getter
public enum OrderResult {
    ERROR(1, "参数错误"),
    EMPTY_CART(2, "购物车为空");


    private Integer code;
    private String msg;

    OrderResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
