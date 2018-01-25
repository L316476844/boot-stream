package org.jon.lv.repository.impl;

import org.apache.commons.lang.StringUtils;
import org.jon.lv.common.TokenUtils;
import org.jon.lv.enums.PlatformType;
import org.jon.lv.redis.RedisUtils;
import org.jon.lv.repository.UserRepository;
import org.jon.lv.result.ResultDO;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Jack
 * @date 2018/1/22
 */
@Service
public class UserRepositoryImpl implements UserRepository {


    @Override
    public ResultDO<String> login(String username, String password, PlatformType platformType) {

        ResultDO<String> resultDO = new ResultDO<>();

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)
                || platformType == null){

            resultDO.setSuccess(false);
            resultDO.setErrMsg("登录失败");

            return resultDO;
        }


        Long userId = new Random().nextLong();
        resultDO.setSuccess(true);

        // 设置token
        resultDO.setData(TokenUtils.generateToken(userId, platformType));

        return resultDO;
    }

    @Override
    public ResultDO<Boolean> resetPwd(Long userId) {
        ResultDO<Boolean> resultDO = new ResultDO<>();
        if(userId == null){
            resultDO.setErrMsg("重置密码失败");
            return resultDO;
        }

        TokenUtils.deleteToken(userId);

        resultDO.setSuccess(true);
        return resultDO;
    }

    @Override
    public ResultDO<Boolean> retrievePwd(Long userId) {
        ResultDO<Boolean> resultDO = new ResultDO<>();
        if(userId == null){
            resultDO.setErrMsg("找回密码失败");
            return resultDO;
        }

        TokenUtils.deleteToken(userId);

        resultDO.setSuccess(true);
        return resultDO;
    }

}
