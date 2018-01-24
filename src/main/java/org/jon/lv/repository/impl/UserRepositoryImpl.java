package org.jon.lv.repository.impl;

import org.jon.lv.domain.UserBean;
import org.jon.lv.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by Jack on 2018/1/22.
 */
@Service
public class UserRepositoryImpl implements UserRepository{

    @Override
    public UserBean findByNameAndPwd(UserBean user) {
        if (user!=null){
            user.setId(new Random().nextLong());
            return user;
        }
        return null;
    }
}
