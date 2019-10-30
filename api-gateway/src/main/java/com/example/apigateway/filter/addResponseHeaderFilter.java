package com.example.apigateway.filter;

import com.example.apigateway.utils.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

/**
 * Created by 廖师兄
 * 2018-02-15 16:00
 */
@Component
@Slf4j
public class addResponseHeaderFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Object run() {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        Cookie mycookie = CookieUtil.get(request, "token");
        HttpServletResponse response = requestContext.getResponse();
        if(mycookie!=null){
            log.info("redis cookie 对应的value是{},",stringRedisTemplate.opsForValue().get(String.format(mycookie.getValue())));
            response.setHeader("userInfo",stringRedisTemplate.opsForValue().get(String.format(mycookie.getValue())));
        }
        return null;
    }
}
