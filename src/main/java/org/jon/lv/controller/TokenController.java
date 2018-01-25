package org.jon.lv.controller;

import org.jon.lv.annotation.AuthPower;
import org.jon.lv.common.TokenConstants;
import org.jon.lv.domain.UserBean;
import org.jon.lv.enums.PlatformType;
import org.jon.lv.repository.UserRepository;
import org.jon.lv.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jack
 * @date 2018/1/22
 */
@RestController
@RequestMapping("/api/{version}")
public class TokenController {

    @Autowired
    private UserRepository userRepository;

    @AuthPower(avoidLogin = true, avoidVersion = true, avoidSign = true)
    @PostMapping("/token")
    public ResultDO<String> token(@RequestParam("username")String username,
                                  @RequestParam("password")String password,
                                  @RequestHeader(TokenConstants.X_PLATFORM)String platform
                                  ) {

        return userRepository.login(username, password, PlatformType.getTypeByPlatform(platform));
    }
}
