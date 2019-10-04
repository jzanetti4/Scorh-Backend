package com.order.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {

    PAID(0, "已支付"),
    WAIT(1, "等待支付");

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}


