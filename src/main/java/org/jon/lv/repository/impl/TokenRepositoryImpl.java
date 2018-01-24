package org.jon.lv.repository.impl;

import org.jon.lv.common.TokenContants;
import org.jon.lv.domain.TokenModel;
import org.jon.lv.redis.RedisUtils;
import org.jon.lv.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * Created by Jack on 2018/1/22.
 */
@Service
public class TokenRepositoryImpl implements TokenRepository{

    @Override
    public TokenModel generateToken(Long userId,int platform) {

        String tokenKey=userId+"";

        //如果允许多平台客户端登录,则生成对应key
        if (TokenContants.ENABLE_MULTI_CLIENT){
            tokenKey+="_"+platform;
        }

        //生成uuid作为token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);

        //在登录时生成Token,已有则删除重置
        if (RedisUtils.exists(tokenKey)){
            deleteToken(userId,platform);
        }
        RedisUtils.set(tokenKey,token, TokenContants.TOKEN_EXPIRES_TIME, TokenContants.TOKEN_EXPIRES_TIMEUNIT);
        return model;
    }

    @Override
    public void deleteToken(Long userId,int platform) {
        String tokenKey=userId+"";

        if (TokenContants.ENABLE_MULTI_CLIENT){
            tokenKey+="_"+platform;
        }
        RedisUtils.remove(tokenKey);
    }

    @Override
    public boolean checkToken(Long userId, int platform,String token) {
        String tokenKey=userId+"";

        if (TokenContants.ENABLE_MULTI_CLIENT){
            tokenKey+="_"+platform;
        }
        String tokenRedis= (String) RedisUtils.get(tokenKey);

        if (tokenRedis!=null&&tokenRedis.equals(token)){
            RedisUtils.expire(tokenKey,TokenContants.TOKEN_EXPIRES_TIME, TokenContants.TOKEN_EXPIRES_TIMEUNIT);
            return true;
        }
        return false;
    }
}
