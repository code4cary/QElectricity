package com.charge.web.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by vincent on 24/09/2018.
 */
public class DateUtil {

    /**
     *  获取今日开始时间
     */
    public static Date getTodayStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     *  获取今日结束时间
     */
    public static Date getTodayEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 获取指定日期的起始时间
     */
    public static Date getCurrentDateStartTime(Date date) {
        Calendar calendar = Calendar. getInstance();
        calendar.setTime(date);
        calendar.set(Calendar. HOUR_OF_DAY, 0);
        calendar.set(Calendar. MINUTE, 0);
        calendar.set(Calendar. SECOND, 0);
        return calendar.getTime();
    }
    /**
     * 获取指定日期的结束时间
     */
    public static Date getCurrentDateEndTime(Date date) {
        Calendar calendar = Calendar. getInstance();
        calendar.setTime(date);
        calendar.set(Calendar. HOUR_OF_DAY, 23);
        calendar.set(Calendar. MINUTE, 59);
        calendar.set(Calendar. SECOND, 59);
        return calendar.getTime();
    }

    public static void main(String... args) {
        System.out.println(getTodayStartTime());
        System.out.println("-----------------------------");
        System.out.println(getTodayEndTime());
    }
}
