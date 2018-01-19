## boot-stream

自定义注解完成版本号校、登录校验、接口权限校验、接口参数签名校验。统一controller层使用stream方式请求，
多次读取请求的stream来完成签名校验。接口版本向下兼容，统一拦截规则，统一异常规则，统一响应。

````
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
````