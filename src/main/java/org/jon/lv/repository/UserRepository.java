package org.jon.lv.repository;

import org.jon.lv.domain.UserBean;

/**
 * Created by Jack on 2018/1/22.
 */
public interface UserRepository {
    public UserBean findByNameAndPwd(UserBean user);
}
