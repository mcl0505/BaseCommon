package com.mcl.basecommon.util;

import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnImageClickListener;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author Create by mcl
 * @Date 2020/3/18
 * @ClassName StringUtil
 * @描述
 */
public class StringUtil {
    private static StringUtil instance;

    public static StringUtil getInstance() {
        if (instance == null){
            synchronized (StringUtil.class){
                if (instance == null){
                    instance = new StringUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 剪切字符串
     * @param str
     * @param first
     * @param up
     * @return
     */
    public String subString(String str,int first,int up){
        return str.substring(first,str.length()-up);
    }

    /**
     * 科学计数法转为正常String
     * @param str
     * @return
     */
    public String scienceToString(String str){
        BigDecimal bd = new BigDecimal(str);
       return bd.toPlainString();
    }

    /**
     * 判断字符串是否为空或为null
     *
     * @param str
     * @return
     */
    public boolean getNull(String str) {
        return str == null || TextUtils.equals(str, "");
    }

    /**
     * 倒计时显示
     */
    public void countDown(final TextView textView) {
        CountDownTimer timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setEnabled(false);
                textView.setText( millisUntilFinished / 1000 + "" );
                textView.setTextColor(Color.parseColor("#aeaeae"));
            }

            @Override
            public void onFinish() {
                textView.setEnabled(true);
                textView.setText("获取验证码");
                textView.setTextColor(Color.parseColor("#FCD300"));
            }
        }.start();
    }

    /**
     * 富文本解析起使用
     * @param content   解析内容
     * @param textView   TextView控件
     */
    public void setRichText(String content,TextView textView){
        RichText.from(content)
                .imageClick(new OnImageClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void imageClicked(List<String> imageUrls, int position) {
                        Calendar calendar = Calendar.getInstance();
                        int m = calendar.get(Calendar.MINUTE);
                        int s = calendar.get(Calendar.SECOND);
                    }
                }).into(textView);
    }
}
