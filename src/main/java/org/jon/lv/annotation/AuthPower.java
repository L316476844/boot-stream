package org.jon.lv.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Package: org.jon.lv.annotation.AuthPower
 * Description: 定制权限
 * Copyright: Copyright (c) 2017
 *
 * @author lv bin
 * Date: 2018/1/19 15:49
 * Version: V1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPower {

    /**
     * 免版本号校验 默认情况所有接口必须全部校验接口版本号
     * @return
     */
    boolean avoidVersion() default false;

    /**
     * 免登陆默认情况都必须登录 --- X-Token
     * @return
     */
    boolean avoidLogin() default false;

    /**
     * 免接口权限校验 --- 判断当前用户是否有访问此接口权限
     * 默认情况不要做权限校验 个别接口需要做权限认证
     * @return
     */
    boolean avoidPower() default true;

    /**
     * 免参数加密校验  eg: SHA-1算法  X-Auth
     * @return
     */
    boolean avoidSign() default false;
}
