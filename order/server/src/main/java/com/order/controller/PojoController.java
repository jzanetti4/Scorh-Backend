package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class PojoController {
    @Autowired
    Pojo pojo;

    @GetMapping("getPojo")
    public Pojo getPojo(){
        return pojo;
    }

}
