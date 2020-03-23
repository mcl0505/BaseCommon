package com.mcl.basecommon.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

/**
 *状态栏设置
 */

public class StatusBarUtils {
    private static StatusBarUtils statusBarUtils;

    public static StatusBarUtils getIntance(){
        if (statusBarUtils==null){
            synchronized (StatusBarUtils.class){
                if (statusBarUtils==null){
                    statusBarUtils = new StatusBarUtils();
                }
            }
        }
        return statusBarUtils;
    }
    private StatusBarUtils(){

    }

    /**
     * 状态栏设置透明
     * @param activity
     */
    public void setStatusBarTransparent(Activity activity){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
    public void setStatusBarTransparent(Activity activity,int color){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            activity.getWindow().setStatusBarColor(color);
        }
    }

    public  void setStatusBarColors(Activity activity,int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(color));
        }
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    /**
     * 设置状态栏字体颜色  亮色 或者 暗色
     * @param dark
     */
    public static void setStateBarTextColor(Activity activity,boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            //黑
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            //白
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

    }
}
