package org.jon.lv.controller;

import com.alibaba.fastjson.JSON;
import org.jon.lv.annotation.AuthPower;
import org.jon.lv.domain.UserBean;
import org.jon.lv.result.ResultDO;
import org.springframework.web.bind.annotation.*;

/**
 * Package: org.jon.lv.controller.StreamController
 * Description: 描述
 * Copyright: Copyright (c) 2017
 *
 * @author lv bin
 * Date: 2018/1/19 14:00
 * Version: V1.0.0
 */
@RestController
@RequestMapping("/{version}/api")
public class StreamController {

    /**
     * 免登陆
     */
    @AuthPower(avoidLogin = true)
    @PostMapping("/login")
    public ResultDO<String> login(@RequestBody String name){
        ResultDO<String> resultDO = new ResultDO<>();
        resultDO.setData(name);
        return resultDO;
    }

    /**
     * 免版本号校验
     * @param id
     * @return
     */
    @AuthPower(avoidVersion = true)
    @GetMapping("/get/{id}")
    public ResultDO<String> get(@PathVariable Long id){
        ResultDO<String> resultDO = new ResultDO<>();
        resultDO.setData(String.valueOf(id));
        return resultDO;
    }


    /**
     * 免参数加密校验
     * @param user
     * @return
     */
    @AuthPower(avoidSign = true)
    @PostMapping("/save")
    public ResultDO<String> save(@RequestBody UserBean user){

        System.out.println("===============" + JSON.toJSONString(user));
        ResultDO<String> resultDO = new ResultDO<>();
        resultDO.setData(JSON.toJSONString(user));
        return resultDO;
    }

    /**
     * 需要校验用户是否有权访问此接口
     * @param user
     * @return
     */
    @AuthPower(avoidPower = false)
    @PostMapping("update")
    public ResultDO<String> power(@RequestBody UserBean user){
        ResultDO<String> resultDO = new ResultDO<>();
        resultDO.setData(JSON.toJSONString(user));
        return resultDO;
    }
}
