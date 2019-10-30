package com.order.controller;


import com.order.message.MyProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by 廖师兄
 * 2018-02-13 18:20
 */
@RestController
public class SendMessageController {

    @Autowired
    private MyProcessor myProcessor;

    @GetMapping("/sendMessage")
    public void process() {
        String message = "now " + new Date();
        myProcessor.output().send(MessageBuilder.withPayload(message).build());
    }

    /**
     * 发送 orderDTO对象
     */
//    @GetMapping("/sendMessage")
//    public void process() {
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setOrderId("123456");
//        myProcessor.output().send(MessageBuilder.withPayload(orderDTO).build());
//    }
}
