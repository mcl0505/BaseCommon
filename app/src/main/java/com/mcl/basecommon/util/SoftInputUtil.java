package com.mcl.basecommon.util;

import android.app.Activity;
import android.graphics.Rect;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * @Author Create by mcl
 * @Date 2020/3/17
 * @ClassName SoftInputUtil
 * @描述  键盘显示与隐藏
 */
public class SoftInputUtil {
    private static SoftInputUtil instance;

    public static SoftInputUtil getInstance() {
        if (instance == null){
            synchronized (SoftInputUtil.class){
                if (instance == null){
                    instance = new SoftInputUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInput(Activity context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        if (context.getCurrentFocus() != null && null != imm) {
            imm.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * 显示软键盘
     */
    public void showSoftInput(Activity context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        if (context.getCurrentFocus() != null && null != imm) {
            imm.showSoftInputFromInputMethod(context.getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * 判断键盘是否弹出
     * @param activity
     * @return
     */
    public boolean isShowSoftInput(Activity activity){
        // 获取当前屏幕内容的高度
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        // 获取View可见区域的bottom
        Rect rect = new Rect();
        // DecorView即为activity的顶级view
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        // 考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
        // 选取screenHeight*2/3进行判断
        return screenHeight*2/3 > rect.bottom;
    }

}
