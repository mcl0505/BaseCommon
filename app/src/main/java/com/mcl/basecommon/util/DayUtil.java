package com.mcl.basecommon.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 作者：Create by (mcl)
 * 时间：2020/3/19
 * 文件名：DayUtil.java
 * 描述：   时间获取工具类
 */
public class DayUtil {
    Calendar ca=Calendar.getInstance();

    String year = ca.get(Calendar.YEAR) + "";
    String month = (ca.get(Calendar.MONTH) + 1) < 10 ? "0"
            + (ca.get(Calendar.MONTH) + 1) : ca.get(Calendar.MONTH) + 1 + "";
    String date = (ca.get(Calendar.DATE)) < 10 ? "0" + ca.get(Calendar.DATE)
            : ca.get(Calendar.DATE) + "";
    String hour = (ca.get(Calendar.HOUR_OF_DAY) < 10 ? "0"
            + ca.get(Calendar.HOUR_OF_DAY) : ca.get(Calendar.HOUR_OF_DAY) + "");
    String minute = (ca.get(Calendar.MINUTE) < 10 ? "0"
            + ca.get(Calendar.MINUTE) : ca.get(Calendar.MINUTE) + "");
    String second = (ca.get(Calendar.SECOND) < 10 ? "0"
            + ca.get(Calendar.SECOND) : ca.get(Calendar.SECOND) + "");

    //获取当前年月日，小时分钟秒，并按照需求格式化为 2016-07-08 12:01:01这种格式
    private SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String getFiveMinTime() {
        Calendar ca=Calendar.getInstance();
        int now = ca.get(Calendar.MINUTE);
        ca.set(Calendar.MINUTE, now - 5);
        String date = SDF.format(ca.getTime());
        return date;
    }//获取当前时间前五分钟

    public String getTenMinTime(){
        Calendar ca=Calendar.getInstance();
        int now = ca.get(Calendar.MINUTE);
        ca.set(Calendar.MINUTE, now - 10);
        String date = SDF.format(ca.getTime());
        return date;
    }//获取当前时间前十分钟

    public String get30MinTime(){
        Calendar ca=Calendar.getInstance();
        int now = ca.get(Calendar.MINUTE);
        ca.set(Calendar.MINUTE, now - 30);
        String date = SDF.format(ca.getTime());
        return date;
    }//获取当前时间前半小时

    public String get60MinTime(){
        Calendar ca=Calendar.getInstance();
        int now = ca.get(Calendar.MINUTE);
        ca.set(Calendar.MINUTE, now - 60);
        String date = SDF.format(ca.getTime());
        return date;
    }//获取当前时间前一小时

    public String getOneDayTime(){
        Calendar ca=Calendar.getInstance();
        int now = ca.get(Calendar.DATE);
        ca.set(Calendar.DATE, now - 1);
        String date = SDF.format(ca.getTime());
        return date;
    }//获取当前时间前一天


    public String getNowTime() {
        Calendar ca=Calendar.getInstance();
        String date = SDF.format(ca.getTime());
        return date;
    }//获取当前时间
    //获取年
    public String getYear() {
        return year;
    }
    //获取月
    public String getMonth() {
        return month;
    }
    //获取日
    public String getDate() {
        return date;
    }
    //获取小时
    public String getHour() {
        return hour;
    }
    //获取分钟
    public String getMinute() {
        return minute;
    }
    //获取秒
    public String getSecond() {
        return second;
    }
}
