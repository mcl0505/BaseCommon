package com.mcl.basecommon.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

/**
 * @Author Create by mcl
 * @Date 2020/3/14
 * @ClassName BindFragment
 * @描述  Fragment视图绑定基类
 */
public abstract class BindFragment extends Fragment {
    /**
     * 试图
     */
    protected View rootView;
    /**
     * 视图加载器
     */
    protected LayoutInflater inflater;
    /**
     * 全局上下文
     */
    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater = layoutInflater;
        mContext = getActivity();
        if(rootView == null){
            rootView = getBindRootView();
            //加载 Fragment 不全屏问题
            if (rootView instanceof RelativeLayout) {
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                rootView.setLayoutParams(lp);
            } else if (rootView instanceof LinearLayout) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                rootView.setLayoutParams(lp);
            }
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }

        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }

        //返回加载的视图
        return rootView;

    }

    /**
     * 获取视图
     * @return
     */
    protected abstract View getBindRootView();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

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
        intent.setClass(mContext, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
        }
        if (rootView !=null){
            rootView = null;
        }
        super.onDestroy();
    }
}
