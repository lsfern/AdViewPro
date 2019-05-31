package com.adviewpro.utils;

import android.Manifest;
import android.content.Context;

import com.yanzhenjie.permission.AndPermission;

/**
 * 权限管理类
 */

public class PermissionUtil {
    private volatile static PermissionUtil INSTANCE;
    private int level = -1;

    /**
     * 单例
     *
     * @return 实例
     */
    public static PermissionUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (PermissionUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PermissionUtil();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 权限枚举
     */
    public enum permissionErrorEnum {
        /* 允许 */
        ALLOW(0),
        /* 拒绝 */
        DENIAL(1);
        private int result;

        permissionErrorEnum(int result) {
            this.result = result;
        }

        private int getValue() {
            return this.result;
        }
    }

    /**
     * 申请所需权限
     * @param context
     * @return
     */
    public int checkNeedPermission(Context context) {
        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE};
        AndPermission.with(context).runtime().permission(permissions)// 为了兼容Android8.O，直接申请存储权限组
                .onGranted(allow -> {
                    level = permissionErrorEnum.ALLOW.getValue();
                    writeResult(level);
                })
                .onDenied(denied -> {
                    level = permissionErrorEnum.DENIAL.getValue();
                    writeResult(level);
                }).start();
        return level;
    }

    /**
     * 回传数据
     *
     * @param level 权限状态值
     */
    private int writeResult(int level) {
        return level;
    }

}
