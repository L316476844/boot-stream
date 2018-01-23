package org.jon.lv.controller;

import org.jon.lv.domain.TokenModel;
import org.jon.lv.domain.UserBean;
import org.jon.lv.repository.TokenRepository;
import org.jon.lv.repository.UserRepository;
import org.jon.lv.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public ResultDO<TokenModel> login(UserBean user){

        ResultDO<TokenModel> resultDO = new ResultDO<>();

        UserBean userBean=userRepository.findByNameAndPwd(user);
        System.out.println("uid======="+userBean.getId());
        if (userBean!=null){

            TokenModel tokenModel=tokenRepository.generateToken(userBean.getId(),user.getPlatform());

            resultDO.setSuccess(true);
            resultDO.setData(tokenModel);
            resultDO.setErrMsg("登录成功");

            return resultDO;
        }
        resultDO.setSuccess(false);
        resultDO.setErrMsg("登录失败");

        return resultDO;
    }
}
