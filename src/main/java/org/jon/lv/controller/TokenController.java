package org.jon.lv.controller;

import javafx.geometry.Pos;
import org.apache.catalina.User;
import org.jon.lv.common.ResponseData;
import org.jon.lv.domain.TokenModel;
import org.jon.lv.domain.UserBean;
import org.jon.lv.repository.TokenRepository;
import org.jon.lv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jack on 2018/1/22.
 */
@RestController
@RequestMapping("/token/")
public class TokenController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public ResponseData<TokenModel> login(UserBean user){
        UserBean userBean=userRepository.findByNameAndPwd(user);

        if (userBean!=null){
            TokenModel tokenModel=tokenRepository.generateToken(userBean.getId());

            return new ResponseData().isOk(1).data(tokenModel).msg("登录成功");
        }
        return new ResponseData().isOk(0).msg("登录失败");
    }
}
