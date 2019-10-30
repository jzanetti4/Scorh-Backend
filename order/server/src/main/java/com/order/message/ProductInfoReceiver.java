package com.order.message;

import com.example.common.ProductInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.order.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class ProductInfoReceiver {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";
    @RabbitListener(queuesToDeclare = @Queue("productInfo")
    )
    public void processComputer(String message) {
        List<ProductInfo> productInfoOutputList = (List<ProductInfo>)JsonUtil.fromJson(message,
                new TypeReference<List<ProductInfo>>() {});
        log.info("从{}接受消息，productInfo: {}", "prodcutinfo",productInfoOutputList);

        for (ProductInfo productInfo : productInfoOutputList) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfo.getProductId()),
                    String.valueOf(productInfo.getProductStock()));
        }


    }



}
