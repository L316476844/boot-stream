package org.jon.lv.common;

import org.jon.lv.enums.PlatformType;
import org.jon.lv.redis.RedisUtils;

/**
 * Package: org.jon.lv.common.TokenUtils
 * Description: 描述
 * Copyright: Copyright (c) 2017
 *
 * @author lv bin
 * Date: 2018/1/25 9:18
 * Version: V1.0.0
 */
public class TokenUtils {


    /**
     * 生成token
     * @param userId
     * @param platform
     * @return
     */
    public static String generateToken(Long userId, PlatformType platform){
        String token = UUIDUtils.getUuid();

        String redisKey = TokenConstants.LOGIN_USER_KEY.concat(String.valueOf(userId));

        Object object = RedisUtils.get(redisKey, platform.getPlatform());

        if(object != null){
            RedisUtils.remove(object.toString());
        }
        /** 永久记忆 **/
        RedisUtils.put(redisKey, platform.getPlatform(), token, -1);

        /** 存放token **/
        RedisUtils.put(TokenConstants.CURRENT_LOGIN_TOKEN, token, String.valueOf(userId), TokenConstants.TOKEN_EXPIRES_TIME);

        return token;
    }


    /**
     * 删除token 重置密码/找回密码
     * @param userId
     */
    public static void deleteToken(Long userId){
        String redisKey = TokenConstants.LOGIN_USER_KEY.concat(String.valueOf(userId));
        /**
         * 删除三端的token
         */
        Object pc = RedisUtils.get(redisKey, PlatformType.PC.getPlatform());
        Object ios = RedisUtils.get(redisKey, PlatformType.IOS.getPlatform());
        Object android = RedisUtils.get(redisKey, PlatformType.ANDROID.getPlatform());

        if(pc != null){
            RedisUtils.remove(TokenConstants.CURRENT_LOGIN_TOKEN, pc.toString());
            RedisUtils.remove(redisKey, PlatformType.PC.getPlatform());
        }

        if(ios != null){
            RedisUtils.remove(TokenConstants.CURRENT_LOGIN_TOKEN, ios.toString());
            RedisUtils.remove(redisKey, PlatformType.IOS.getPlatform());
        }

        if(android != null){
            RedisUtils.remove(TokenConstants.CURRENT_LOGIN_TOKEN, android.toString());
            RedisUtils.remove(redisKey, PlatformType.ANDROID.getPlatform());
        }
    }
}
