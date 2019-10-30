package com.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by 廖师兄
 * 2018-02-13 18:16
 */
public interface MyProcessor {

//    String INPUT = "myMessage";
//
//    String INPUT2 = "myMessage";

    @Input("inputChannel")
    SubscribableChannel input();

    @Output("outputChannel")
    MessageChannel output();

//    @Input(MyProcessor.INPUT2)
//    SubscribableChannel input2();
//
//    @Output(MyProcessor.INPUT2)
//    MessageChannel output2();
}
