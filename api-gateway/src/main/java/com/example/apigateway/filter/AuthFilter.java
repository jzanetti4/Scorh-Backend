package com.example.apigateway.filter;

import com.example.apigateway.constant.RedisConstant;
import com.example.apigateway.utils.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;


/**
 * AuthFilter to give access to user who have the token
 * Created by Hangqi Yu
 * 2019-10-10 16:57
 */

@Slf4j
@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String requestURL = request.getRequestURI();
        if (!"/zuulUser/login/".equals(requestURL) & !"/zuulUser/register/initReg".equals(requestURL) & !"/zuulUser/register/regActive".equals(requestURL)) {
            Cookie cookie = CookieUtil.get(request, "token");
            if (cookie == null || StringUtils.isEmpty(cookie.getValue()) || StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(cookie.getValue()))) {
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            }
        }
        return null;
    }
}


