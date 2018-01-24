package org.jon.lv.repository;

import org.jon.lv.domain.TokenModel;

/**
 * Created by Jack on 2018/1/22.
 */
public interface TokenRepository {


    public TokenModel generateToken(Long userId,int platform);

    public void deleteToken(Long userId,int platform);

    public boolean checkToken(Long userId,int platform,String token);


}
