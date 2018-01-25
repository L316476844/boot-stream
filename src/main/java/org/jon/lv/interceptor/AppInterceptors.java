package org.jon.lv.interceptor;

import org.jon.lv.annotation.AuthPower;
import org.jon.lv.common.TokenConstants;
import org.jon.lv.enums.PlatformType;
import org.jon.lv.exception.AppWebException;
import org.jon.lv.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.regex.Pattern;

/**
 * Package: org.jon.lv.interceptor.AppInterceptors
 * Description: 描述
 * Copyright: Copyright (c) 2017
 *
 * @author lv bin
 * Date: 2018/1/19 14:11
 * Version: V1.0.0
 */
@Configuration
public class AppInterceptors extends WebMvcConfigurerAdapter{

    // 路径中版本的前缀， 这里用 /v[1-9]/的形式
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+)/");

    @Value("${app.version}")
    private String APP_VERSION;

    /**
     * 默认请求request header 头部存放 token 名称
     */
    public String DEFAULT_TOKEN_NAME = "X-Token";

    /**
     * 参数加密值
     */
    public String DEFAULT_AUTH_NAME = "X-Auth";

    public String DEFAULT_PLATFORM = "X-Platform";


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiInterceptor()).addPathPatterns("/api/{version}/*");
    }

    /**
     * api 拦截器
     */
    private class ApiInterceptor extends HandlerInterceptorAdapter{

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            String uri = request.getRequestURI();

            boolean avoidVersion = false;

            boolean avoidLogin = false;

            boolean avoidPower = true;

            boolean avoidSign = true;

            if(handler instanceof HandlerMethod) {

                HandlerMethod method = (HandlerMethod) handler;
                AuthPower authPower = method.getMethodAnnotation(AuthPower.class);
                avoidVersion = authPower.avoidVersion();
                avoidLogin = authPower.avoidLogin();
                avoidPower = authPower.avoidPower();
                avoidSign = authPower.avoidSign();
            }

            String platform =  request.getHeader(TokenConstants.X_PLATFORM);

            if(StringUtils.isEmpty(platform) || PlatformType.getTypeByPlatform(platform) == null){
                throw new AppWebException("-----invalid platform please !----");
            }

            String version = "/api/".concat(APP_VERSION);

            // 版本号校验
            if(!avoidVersion && !uri.startsWith(version)){
                throw new AppWebException("-----invalid api version,access denied !----");
            }

            String tokenAuth = request.getHeader(DEFAULT_TOKEN_NAME);

            // 登录校验
            if(!avoidLogin){
                // token 是否为空  以及redis中获取token是否存在
                if(StringUtils.isEmpty(tokenAuth)){
                    throw new AppWebException("-----please log in to access this method !----");
                }

                Long userId = (Long)RedisUtils.get(tokenAuth);
                if(userId == null){
                    throw new AppWebException("-----login expired,re-login please !----");
                }

                // 延长token时间
                RedisUtils.set(tokenAuth, userId, TokenConstants.TOKEN_EXPIRES_TIME);
            }

            if(!avoidPower){
                // 需要判断用户是否有权访问接口 -- db内配置用户角色接口访问权限
                throw new AppWebException("-----authorization is required to access this method !----");
            }

            if(!avoidSign){
                // 判断是否需要校验参数规则  是否验签名
                throw new AppWebException("-----invalid signature,access denied !----");

            }

            if(request instanceof BodyReaderHttpServletRequestWrapper){

                BodyReaderHttpServletRequestWrapper requestWrapper = (BodyReaderHttpServletRequestWrapper) request;

                // 请求body不为空
                BufferedReader reader = requestWrapper.getReader();
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                System.out.println("--------------------------" + sb.toString());
            }


            return super.preHandle(request, response, handler);
        }
    }
}
