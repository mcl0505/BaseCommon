package com.mcl.basecommon.util;

import androidx.fragment.app.FragmentManager;

/**
 *
 */
public class BugUtil {
    /** 判断是否是快速点击 */
    private static long lastClickTime;
    // 记录点击返回时第一次的时间毫秒值
    private static long firstTime;

    public static boolean isFastDoubleClick(int mTime) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;

        if (0 < timeD && timeD < mTime) {
            return true;
        }

        lastClickTime = time;
        return false;
    }

    public static void closeApp(FragmentManager manager) {
        if (manager.getBackStackEntryCount() != 0) {
            manager.popBackStack();
        } else {
            exitApp(2000);// 退出应用
        }
    }

    /**
     * 退出应用
     *
     * @param timeInterval 设置第二次点击退出的时间间隔
     */
    private static void exitApp(long timeInterval) {
        // 第一次肯定会进入到if判断里面，然后把firstTime重新赋值当前的系统时间
        // 然后点击第二次的时候，当点击间隔时间小于2s，那么退出应用；反之不退出应用
        if (System.currentTimeMillis() - firstTime >= timeInterval) {

            firstTime = System.currentTimeMillis();
        } else {
            ActivityUtil.getInstance().exitSystem();
        }
    }
}
