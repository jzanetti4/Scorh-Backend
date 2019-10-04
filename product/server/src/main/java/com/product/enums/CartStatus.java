package com.product.enums;

import lombok.Getter;

@Getter
public enum CartStatus {

    NOT_FOUND(1,"商品不存在"),
    OUT_OF_STOCK(2,"超出库存");


    private Integer code;
    private String msg;

    CartStatus( Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

}
