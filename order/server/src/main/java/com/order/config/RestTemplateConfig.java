package com.order.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfig {

    @LoadBalanced
    @Bean
    public RestTemplate RestTemplateConfig() {
        return new RestTemplate();
    }
}
