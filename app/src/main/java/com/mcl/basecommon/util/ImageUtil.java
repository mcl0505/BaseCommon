package com.mcl.basecommon.util;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.tools.PictureFileUtils;

/**
 * Created by LWH
 * Date: 2019/11/22
 */
public class ImageUtil {
    private static ImageUtil sInstance;
    private static Activity mActivity;
    private static Fragment mFragment;
    private static boolean mType = true;

    private static int maxSelectNum = 9;
//    private static int themeId = R.style.picture_white_style;
    // 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
    public static int chooseModeImage = PictureMimeType.ofImage();

    private static boolean mChooseMode = true;//多选 true    单选 false
    private static boolean mPreviewImg = false;//预览 true   不预览 false   图片
    private static boolean mPreviewVideo = true;//预览 true   不预览 false   视频
    private static boolean mPreviewAudio = false;//播放 true   不可播放 false   音频
    private static boolean mIsCamera = false;//是否显示拍照按钮
    private static boolean mCrop = false;//是否剪裁
    private static boolean mCompress = true;//是否压缩
    private static boolean mHide = false;//是否显示uCrop的工具栏    (－＂－怒)LZ还以为true是true呢，C！
    private static boolean mIsGif = false;//是否显示gif
    private static boolean mCropCircular = false;//是否圆形裁剪
    private static boolean mShowCropFrame = true;//是否显示裁剪矩形边框
    private static boolean mShowCropGrid = true;//是否显示裁剪矩形网格
    private static boolean mVoice = false;//是否开启点击声音

    // 单例模式
    public static ImageUtil getInstance(Activity activity) {
        mType = true;
        mActivity = activity;
        if (null == sInstance) {
            synchronized (ImageUtil.class) {
                sInstance = new ImageUtil();
            }
        }
        return sInstance;
    }

    public static ImageUtil getInstance(boolean type, Fragment fragment) {
        mType = type;
        mFragment = fragment;
        if (null == sInstance) {
            synchronized (ImageUtil.class) {
                sInstance = new ImageUtil();
            }
        }
        return sInstance;
    }

    //设置头像的属性配置
    public void setHeadConfigure(boolean chooseMode, boolean crop, boolean cropCircular) {
        mChooseMode = chooseMode;
        mCrop = crop;
        mCropCircular = cropCircular;
    }

    public void album() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector pictureSelector;
        if (mType) {
            pictureSelector = PictureSelector.create(mActivity);
        } else {
            pictureSelector = PictureSelector.create(mFragment);
        }
        pictureSelector.openGallery(chooseModeImage)
                .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
//                .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(maxSelectNum)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(mChooseMode ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                .previewImage(mPreviewImg)// 是否可预览图片
                .previewVideo(mPreviewVideo)// 是否可预览视频
                .enablePreviewAudio(mPreviewAudio) // 是否可播放音频
                .isCamera(mIsCamera)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .enableCrop(false)// 是否裁剪
                .compress(mCompress)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(mHide)// 是否显示uCrop工具栏，默认不显示
                .isGif(mIsGif)// 是否显示gif图片
                .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                .circleDimmedLayer(mCropCircular)// 是否圆形裁剪
                .showCropFrame(mShowCropFrame)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(mShowCropGrid)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(mVoice)// 是否开启点击声音
                .minimumCompressSize(100)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }


    public void camera() {
        // 单独拍照
        PictureSelector.create(mActivity)
                .openCamera(chooseModeImage)// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
//                .theme(themeId)// 主题样式设置 具体参考 values/styles
                .maxSelectNum(maxSelectNum)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .selectionMode(mChooseMode ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                .previewImage(mPreviewImg)// 是否可预览图片
                .previewVideo(mPreviewVideo)// 是否可预览视频
                .enablePreviewAudio(mPreviewAudio) // 是否可播放音频
                .isCamera(mIsCamera)// 是否显示拍照按钮
                .enableCrop(mCrop)// 是否裁剪
                .compress(mCompress)// 是否压缩
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(mHide)// 是否显示uCrop工具栏，默认不显示
                .isGif(mIsGif)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(mCropCircular)// 是否圆形裁剪
                .showCropFrame(mShowCropFrame)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(mShowCropGrid)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(mVoice)// 是否开启点击声音
                .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .loadImageEngine(GlideEngine.createGlideEngine())
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    public static void deleteImage() {
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限

        //包括裁剪和压缩后的缓存，要在上传成功后调用，type 指的是图片or视频缓存取决于你设置的ofImage或ofVideo 注意：需要系统sd卡权限
        PictureFileUtils.deleteCacheDirFile(mActivity,chooseModeImage);
        // 清除所有缓存 例如：压缩、裁剪、视频、音频所生成的临时文件
        PictureFileUtils.deleteAllCacheDirFile(mActivity);
//        RxPermissions permissions = new RxPermissions(mActivity);
//        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//            }
//
//            @Override
//            public void onNext(Boolean aBoolean) {
//                if (aBoolean) {
//                    PictureFileUtils.deleteCacheDirFile(mActivity,0);
//                } else {
//                    ToastUtil.show(getString(R.string.picture_jurisdiction));
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//
//            @Override
//            public void onComplete() {
//            }
//        });
    }

}
