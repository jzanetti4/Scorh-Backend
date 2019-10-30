package com.order.controller;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@RefreshScope
@Component
@ConfigurationProperties("pojo")
public class Pojo
{
   private String name;
   private int age;
}
