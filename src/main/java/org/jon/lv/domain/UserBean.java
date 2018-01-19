package org.jon.lv.domain;

import java.io.Serializable;

/**
 * Package: org.jon.lv.domain.UserBean
 * Description: 描述
 * Copyright: Copyright (c) 2017
 *
 * @author lv bin
 * Date: 2018/1/19 14:01
 * Version: V1.0.0
 */
public class UserBean implements Serializable{
    private static final long serialVersionUID = 4264329146861798867L;

    private Long id;

    private String name;

    private Integer age;

    private String address;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
