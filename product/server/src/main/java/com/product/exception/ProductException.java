package com.product.exception;

import com.product.enums.CartStatus;

public class ProductException extends RuntimeException {
    private Integer code;

    ProductException(Integer code,String msg){
        super(msg);
        this.code=code;
    }

    public ProductException(CartStatus cartStatus){
        super(cartStatus.getMsg());
        this.code=cartStatus.getCode();
    }

}
