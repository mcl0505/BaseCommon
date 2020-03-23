package com.mcl.basecommon.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mcl.basecommon.R;
import com.mcl.basecommon.util.ActivityUtil;
import com.mcl.basecommon.util.BugUtil;
import com.mcl.basecommon.util.StatusBarUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * @Author Create by mcl
 * @Date 2020/3/19
 * @ClassName BindActivity
 * @描述   不带标题的Activity
 */
public abstract class BindActivity  extends AppCompatActivity {

    /**
     * Activity
     */
    protected Activity mActivity;
    /**
     * 全局上下文
     */
    protected Context mContext;
    protected View rootView;

    /**
     *布局加载器
     */
    protected LayoutInflater inflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = getLayoutInflater();
        mActivity = this;
        mContext = this;
        rootView = getBindRootView();
        if (rootView != null){
            setContentView(getBindRootView());
        }else {
            setContentView(R.layout.layout_empty);
        }

        //将Activity添加到栈中
        ActivityUtil.getInstance().addActivity(mActivity);
        //输入框位于键盘上方
        getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_ADJUST_PAN);
        //状态栏透明
        StatusBarUtils.getIntance().setStatusBarTransparent(mActivity);
        //是否使用注解标记  判断是否注册EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class) ){
            EventBus.getDefault().register(this);
        }
        initData();
    }

    /**
     * 返回自动绑定的视图
     * @return
     */
    protected abstract View getBindRootView() ;

    /**
     * 逻辑处理
     */
    protected abstract void initData();

    protected void openActivity(Class<?> cls){
        openActivity(cls,null);
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void openActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     * 防止连续点击出现相同的Activity
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (BugUtil.isFastDoubleClick(300)) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * APP字体大小，不随系统的字体大小变化而变化
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void onDestroy() {
        //是否使用注解标记  判断是否注册EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class) ){
            EventBus.getDefault().unregister(this);
        }

        if (rootView != null){
            rootView = null;
        }

        super.onDestroy();
    }
}
