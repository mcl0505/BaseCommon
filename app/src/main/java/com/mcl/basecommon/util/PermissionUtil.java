package com.mcl.basecommon.util;

import android.Manifest;
import android.app.Activity;

import com.luck.picture.lib.tools.ToastUtils;
import com.master.permissionhelper.PermissionHelper;

import org.jetbrains.annotations.NotNull;

public class PermissionUtil {

    public static PermissionHelper getPermission(Activity activity) {
        PermissionHelper permissionHelper = new PermissionHelper(activity, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        }, 100);

        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {
                //全部授权时的操作
            }

            @Override
            public void onIndividualPermissionGranted(@NotNull String[] strings) {
                //当其中某个授权同意时的操作
            }

            @Override
            public void onPermissionDenied() {
                //其中某个授权拒绝时的操作
                ToastUtils.s(activity,"权限未授予，请开启权限");
            }

            @Override
            public void onPermissionDeniedBySystem() {
                //用户选择“不在询问后”，点击“拒绝”按钮后执行的操作
                ToastUtils.s(activity,"权限未授予，请开启权限");
            }
        });
        return permissionHelper;
    }
}
