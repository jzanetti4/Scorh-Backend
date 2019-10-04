package com.order.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    SUCCESS(0, "成功"),
    FAILURE(1, "失败");

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
