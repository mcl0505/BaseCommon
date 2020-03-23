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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mcl.basecommon.R;
import com.mcl.basecommon.util.ActivityUtil;
import com.mcl.basecommon.util.BugUtil;
import com.mcl.basecommon.util.ScreenUtil;
import com.mcl.basecommon.util.SoftInputUtil;
import com.mcl.basecommon.util.StatusBarUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者：Create by (mcl)
 * 时间：2020/3/19
 * 文件名：BindTitleActivity.java
 * 描述：   带标题的Activity
 */
public abstract class BindTitleActivity extends AppCompatActivity {
    //父布局控件
    protected View rootView;
    //子布局
    protected View childView;
    protected TextView titleCenterTv;//标题
    protected TextView titleRightTv;//右边文字
    protected ImageView titleLeftImg;//左边返回图标
    //布局加载器
    protected LayoutInflater inflater;
    //全局上下文
    protected Context mContext;
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
        inflater = getLayoutInflater();
        //设置屏幕竖屏
        ScreenUtil.getInstance().setVerticalScreen(mActivity, true);
        //状态栏透明
        StatusBarUtils.getIntance().setStatusBarTransparent(mActivity);
        //设置状态栏字体颜色
        StatusBarUtils.setStateBarTextColor(mActivity, false);
        //输入框位于键盘上方
        getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_ADJUST_PAN);
        rootView = View.inflate(this, R.layout.activity_base, null);
        setContentView(rootView);
        addContent();
    }

    /**
     * 添加父布局
     */
    private void addContent() {
        titleCenterTv = rootView.findViewById(R.id.title_center);
        titleLeftImg = rootView.findViewById(R.id.title_back);
        titleRightTv = rootView.findViewById(R.id.title_right_text);
        FrameLayout flContent = (FrameLayout) rootView.findViewById(R.id.fl_content);

        titleLeftImg.setOnClickListener(v -> {
            if (SoftInputUtil.getInstance().isShowSoftInput(mActivity)) {
                SoftInputUtil.getInstance().hideSoftInput(mActivity);
            }

            finish();
        });//java 8 写法
        childView = getBindRootView();
        if (childView != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            flContent.addView(childView, params);

            //将Activity添加到栈中
            ActivityUtil.getInstance().addActivity(mActivity);
            //输入框会自动位于软键盘上方，且布局不变形
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
            //利用注解，判断是否使用EventBus
            if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
                EventBus.getDefault().register(this);
            }


            titleCenterTv.setText(setTitleText() == null ? "" : setTitleText());
            initData();
        }
    }

    /**
     * 页面跳转
     * @param cls
     */
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
     * 获取布局id
     *
     * @return
     */
    protected abstract View getBindRootView();

    /**
     * title赋值
     *  可以在这里面获取 跳转传递过来的值
     * @return
     */
    protected abstract String setTitleText();

    /**
     * 逻辑处理
     */
    protected abstract void initData() ;

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
        //注销EventBus
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
        }
        if (childView != null){
            childView = null;
        }
        super.onDestroy();
    }
}
