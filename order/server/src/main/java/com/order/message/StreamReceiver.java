package com.order.message;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * Created by Hangqi Yu
 * 2018-02-13 18:17
 */
@Component
@EnableBinding(MyProcessor.class)
@Slf4j
public class StreamReceiver {

    @StreamListener("outputChannel")
    public void process(Object message) {
        log.info("StreamReceiver: {}", message);
    }

    /**
     * 接收orderDTO对象 消息
     * @param message
     */
//    @StreamListener(value = MyProcessor.INPUT)
//    @SendTo(MyProcessor.INPUT2)
//    public String process(OrderDTO message) {
//        log.info("StreamReceiver: {}", message);
//        return "received.";
//    }

//    @StreamListener(value = MyProcessor.INPUT2)
//    public void process2(String message) {
//        log.info("StreamReceiver2: {}", message);
//    }
}
