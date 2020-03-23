package com.mcl.basecommon.view.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.ggh.common.R;

/**
 * 图片加载
 */

public class ImageLoaderUtil {

    private static int defPlaceholder = R.mipmap.loading_error;
    private static int defErrorImage = R.mipmap.loading_error;

    public static void setDefPlaceholder(int defPlaceholder) {
        ImageLoaderUtil.defPlaceholder = defPlaceholder;
    }

    public static void setDefErrorImage(int defErrorImage) {
        ImageLoaderUtil.defErrorImage = defErrorImage;
    }

    //加载图片   全局上下文   路径int  控件
    public static void load(Context context, int imgUrl, ImageView imageView) {
        RequestOptions options2 = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH);//优先级
//                .transform(new GlideCircleTransform());//转化为圆形
        if (defErrorImage != 0) {//加载失败显示图片
            options2.error(defErrorImage);
        }
        if (defPlaceholder != 0) {//预加载图片
            options2.placeholder(defPlaceholder);
        }
        Glide.with(context).load(imgUrl).apply(options2).into(imageView);
    }
    //加载图片   全局上下文   url String  控件
    public static void load(Context context, String imgUrl, ImageView imageView) {
        RequestOptions options2 = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH);//优先级
//                .transform(new GlideCircleTransform());//转化为圆形
        if (defErrorImage != 0) {//加载失败显示图片
            options2.error(defErrorImage);
        }
        if (defPlaceholder != 0) {//预加载图片
            options2.placeholder(defPlaceholder);
        }
        Glide.with(context).load(imgUrl).apply(options2).into(imageView);
    }
    //加载圆形图片
    public static void loadCircle(Context context, String imgUrl, ImageView imageView) {
        RequestOptions options2 = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)//优先级
                .transform(new GlideCircleTransform());//转化为圆形
        if (defErrorImage != 0) {//加载失败显示图片
            options2.error(defErrorImage);
        }
        if (defPlaceholder != 0) {//预加载图片
            options2.placeholder(defPlaceholder);
        }
        Glide.with(context).load(imgUrl).apply(options2).into(imageView);
     }

    public static void loadCircle(Context context, int imgUrl, ImageView imageView) {
        RequestOptions options2 = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)//优先级
                .transform(new GlideCircleTransform());//转化为圆形
        if (defErrorImage != 0) {//加载失败显示图片
            options2.error(defErrorImage);
        }
        if (defPlaceholder != 0) {//预加载图片
            options2.placeholder(defPlaceholder);
        }
        Glide.with(context).load(imgUrl).apply(options2).into(imageView);
     }

}
