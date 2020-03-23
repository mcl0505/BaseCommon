package com.mcl.basecommon.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * @Author Create by mcl
 * @Date 2020/3/19
 * @ClassName ScreenUtil
 * @描述  屏幕工具
 */
public class ScreenUtil {
    private static ScreenUtil instance;

    public static ScreenUtil getInstance(){
        if (instance == null){
            synchronized (ScreenUtil.class){
                if (instance == null){
                    instance = new ScreenUtil();
                }
            }
        }
        return instance;
    }


    /**
     * 设置当前屏幕方向为竖屏或者横屏   ture竖屏   false横屏
     */
    public void setVerticalScreen(Activity activity, boolean isVertical) {
        if (isVertical){
            //竖屏
            if (activity.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }else {
            //横屏
            if (activity.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        }

    }

    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public void measureWidthAndHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public  int getScreenRealWidth(Context context) {
        Display display = getDisplay(context);
        if (display == null) {
            return 0;
        }
        Point outSize = new Point();
        display.getRealSize(outSize);
        return outSize.x;
    }

    private  Display getDisplay(Context context) {
        WindowManager wm;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            wm = activity.getWindowManager();
        } else {
            wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        if (wm != null) {
            return wm.getDefaultDisplay();
        }
        return null;
    }
}
