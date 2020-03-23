package com.mcl.basecommon.util;

import android.app.Activity;
import android.os.Process;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 作者：Create by (mcl)
 * 时间：2020/3/14
 * 文件名：ActivityUtil.java
 * 描述：  Activity单例管理模式
 */
public class ActivityUtil {
    private List<Activity> activityList = new ArrayList<>();
    private static ActivityUtil sInstance;
    private boolean lock = false;

    public static ActivityUtil getInstance() {
        if (null == sInstance) {
            synchronized (ActivityUtil.class) {
                sInstance = new ActivityUtil();
            }
        }
        return sInstance;
    }

    // 添加Activity到容器中
    public synchronized void addActivity(Activity activity) {
        if (activityList == null)
            activityList = new ArrayList<>();
        activityList.add(activity);
    }

    // 移除Activity
    public synchronized void removeActivity(Activity activity) {
        if (activityList != null)
            activityList.remove(activity);
    }

    // 保存Activity不变移除其他Activity
    public void removeActivitySKeepA(Activity activity) {
        if (!lock) {
            lock = true;
            Iterator<Activity> iterator = activityList.iterator();
            while (iterator.hasNext()) {
                Activity activity1 = iterator.next();
                if (activity1 != null && activity != null && !activity.getComponentName().getClassName().equals(activity1.getComponentName().getClassName())) {
                    activity1.finish();
                    iterator.remove();
                }
            }
            lock = false;
        }
    }

    // 遍历所有Activity
    public void removeAllActivity() {
        for (Activity activity : activityList) {
            if (activity != null)
                activity.finish();
        }
        activityList.clear();
    }

    // 遍历所有Activity并关闭软件
    public void exitSystem() {
        for (Activity activity : activityList) {
            if (activity != null)
                activity.finish();
        }
        System.gc();
        Process.killProcess(Process.myPid());
        System.exit(0);
    }
}
