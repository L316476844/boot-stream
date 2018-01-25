package org.jon.lv.common;

import java.util.UUID;

/**
 * Package: org.jon.lv.common.UUIDUtils
 * Description: 描述
 * Copyright: Copyright (c) 2017
 *
 * @author lv bin
 * Date: 2018/1/25 9:12
 * Version: V1.0.0
 */
public class UUIDUtils {

    /**
     * jdk uuid 工具类
     * @return
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
