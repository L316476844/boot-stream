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
    public TokenModel generateToken(Long userId) {
        //生成uuid作为token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);

        RedisUtils.set(userId+"",token, TokenContants.TOKEN_EXPIRES_TIME, TimeUnit.HOURS);
        return null;
    }
}
