package com.example.email.mqReceiver;


//import com.order.util.JsonUtil;
import com.example.email.service.MailService;
import com.example.email.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.UUID;


@Slf4j
@Component
public class EmailAddressReceiver {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MailService mailService;
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("emailService"),
            exchange = @Exchange("ScorhMessage")
    ))
    public void processComputer(String row) {
        String message= (String) JsonUtil.fromJson(row, new TypeReference<String>(){});
//        List<ProductInfo> productInfoOutputList = (List<ProductInfo>)JsonUtil.fromJson(message,
//                new TypeReference<List<ProductInfo>>() {});
        log.info("从email接受消息，邮箱地址: {}",message);

        String code = UUID.randomUUID().toString();

        System.out.println(message);
//        System.out.println("yhq19951005@gmail.com".equals(message));
        mailService.send(message,code);

        stringRedisTemplate.opsForValue().set(code,message);


//
//        for (ProductInfo productInfo : productInfoOutputList) {
//            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfo.getProductId()),
//                    String.valueOf(productInfo.getProductStock()));
//        }

    }



}
