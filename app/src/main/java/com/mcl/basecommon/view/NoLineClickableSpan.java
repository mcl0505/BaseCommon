package com.mcl.basecommon.view;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

/**
 * @Author Create by mcl
 * @Date 2020/3/11
 * @ClassName NoLineClickableSpan
 * @描述  没有下划线
 */
public class NoLineClickableSpan extends ClickableSpan {
    @Override
    public void onClick(@NonNull View widget) {

    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
    }
}
