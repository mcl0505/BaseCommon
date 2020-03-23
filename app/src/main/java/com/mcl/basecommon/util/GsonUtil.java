package com.mcl.basecommon.util;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Create by mcl
 * @Date 2019/12/25
 * @ClassName GsonUtil
 * @描述
 */
public class GsonUtil{
    private static Gson gson;
    public static Gson formGson(){
        if (gson == null){
            synchronized (GsonUtil.class){
                if (gson == null){
                    gson = new Gson();
                }
            }
        }
        return gson;
    }

    public static List<Class<?>> stringToList(String json,Class<?> cla) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        List<Class<?>> mList = new ArrayList<>();
        JSONObject jsonObject = null;
        if (jsonArray.length()>0){
            for (int i = 0;i<jsonArray.length();i++){
                jsonObject = new JSONObject((String) jsonArray.get(i));
                cla = new Gson().fromJson(jsonObject.toString(), (Type) cla);
                mList.add(cla);
            }
        }else {
            return null;
        }


        return mList;
    }
}
