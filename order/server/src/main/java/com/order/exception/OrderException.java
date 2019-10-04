package com.order.exception;

import com.order.enums.OrderResult;

public class OrderException extends Exception {
    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(OrderResult orderResult) {
        super(orderResult.getMsg());
        this.code = orderResult.getCode();
    }
}
