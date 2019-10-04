package com.order.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

@Data
public class ResultVO<T> {
    private String code;
    private String msg;
    private T data;

}
