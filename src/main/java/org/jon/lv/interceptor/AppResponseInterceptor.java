package org.jon.lv.interceptor;

import com.alibaba.fastjson.JSON;
import org.jon.lv.result.ResultDO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Package: org.jon.lv.interceptor.AppResponseInterceptor
 * Description: 全局响应拦截
 * Copyright: Copyright (c) 2017
 *
 * @author lv bin
 * Date: 2018/1/19 17:10
 * Version: V1.0.0
 */
@ControllerAdvice
public class AppResponseInterceptor implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 响应结果执行
        if(mediaType != null && o != null
                && mediaType.includes(MediaType.APPLICATION_JSON)
                && o instanceof ResultDO){

            if(serverHttpRequest instanceof ServletServerHttpRequest){

                ServletServerHttpRequest request = (ServletServerHttpRequest)serverHttpRequest;

                HttpServletRequest httpServletRequest = request.getServletRequest();

                Method method = methodParameter.getMethod();

            }
        }

        return o;
    }
}