package org.jon.lv.enums;

/**
 * Package: org.jon.lv.enums.PlatformType
 * Description: 描述
 * Copyright: Copyright (c) 2017
 *
 * @author lv bin
 * Date: 2018/1/25 8:56
 * Version: V1.0.0
 */
public enum PlatformType {
    /**
     * pc android ios
     */
    PC(0, "PC"),
    ANDROID(1, "ANDROID"),
    IOS(2, "IOS");

    private final int type;
    private final String platform;

    public int getType() {
        return type;
    }

    public String getPlatform() {
        return platform;
    }

    PlatformType(int type, String platform) {
        this.type = type;
        this.platform = platform;
    }


    public static PlatformType getTypeByPlatform(String platform) {
        for (PlatformType type : PlatformType.values()) {
            if (type.platform.equalsIgnoreCase(platform)) {
                return type;
            }
        }
        return null;
    }
}
