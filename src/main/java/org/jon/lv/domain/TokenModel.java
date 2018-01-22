package org.jon.lv.domain;

/**
 * Created by Jack on 2018/1/22.
 */
public class TokenModel {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 随机生成的uuid
     */
    private String token;

    public TokenModel(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
