package com.mcl.basecommon.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * 创建人：孟从伦 qq:2815553255
 * 创建时间：2019/9/30
 * 意义：SharedPreferences封装
 */
public class SPUtil {

    public static final String UID = "uid";
    public static final String ACCOUNT = "account";
    public static final String TOKEN = "token";
    public static final String NAME = "name";
    public static final String SITE = "site";
    public static final String SITE_ID = "site_id";


    //用于创建名称
    private final static String spName = "SPUtil";

    private static SPUtil instance;


    public static SPUtil getInstance(){
        if (instance == null){
            synchronized (SPUtil.class){
                if (instance == null){
                    instance = new SPUtil();
                }
            }
        }
        return instance;
    }


    /**
     * 存入值 判断是什么类型的数据，根据不同的数据值存到不同的地方
     * @param key   关键字
     * @param value   存入值
     */
    public static void putValue(Context context,String key, Object value) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        } else if (value instanceof String) {
            edit.putString(key, (String) value);
        }
        edit.apply();
    }

    /**
     * 读取值
     * @param key   关键字
     * @param defValue  默认值
     * @return
     */
    public static Object getValue(Context context,String key, Object defValue) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        if (defValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Float) {
            return sp.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Integer) {
            return sp.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Long) {
            return sp.getLong(key, (Long) defValue);
        } else if (defValue instanceof String) {
            return sp.getString(key, (String) defValue);
        }
        return null;
    }

    public static void clearSP(Context context) {
        context.getSharedPreferences(spName, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }

    public static void removeSP(Context context,String Key) {
        context.getSharedPreferences(spName, Context.MODE_PRIVATE)
                .edit()
                .remove(Key)
                .apply();
    }

    public static Map<String, ?> getAllSP(Context context) {
        return context.getSharedPreferences(spName, Context.MODE_PRIVATE).getAll();
    }

}
