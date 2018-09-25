package com.charge.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by vincent on 24/09/2018.
 */
public class DateUtil {


    /**
     * 返回指定格式的日期
     *
     * @param date   类型为String
     * @param format
     * @return 类型为Date
     * @throws ParseException
     */
    public static Date getSpecificDateFormat(String date, String format) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date dateFormat = df.parse(date);
        return dateFormat;

    }


    /**
     * 精确到毫秒
     * 获取指定格式的时间
     *
     * @param date   类型为Date
     * @param format
     * @return 类型为Date
     * @throws ParseException
     */
    public static Date getSpecificDateFormat(Date date, String format) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.parse(df.format(date));
    }


    /**
     * 获取今日开始时间
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
     * 获取今日结束时间
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
    public static Date getSpecificDateStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定日期的结束时间
     */
    public static Date getSpecificDateEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取当前月第一天
     *
     * @return
     */
    public static Date firstDayCurrentMonth() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return c.getTime();

    }


    /**
     * 获取当前月最后一天
     */
    public static Date lastDayCurrentMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }


    public static void main(String... args) throws ParseException {
        System.out.println(getTodayStartTime());
        System.out.println("-----------------------------");
        System.out.println(getTodayEndTime());
        System.out.println("-----------------------------");

        String format = "yyyy-MM";//日期格式
        String dateStr = "2018-09-15";
        Date specificDateFormat = getSpecificDateStartTime(getSpecificDateFormat(dateStr, format));
        System.out.println(specificDateFormat);
        System.out.println("--------------------");

        System.out.println("当前月第一天是:" + firstDayCurrentMonth());
        System.out.println("当前月最后一天是:" + lastDayCurrentMonth());
        System.out.println("--------------------");

        Date dateStart = DateUtil.getSpecificDateStartTime(DateUtil.getSpecificDateFormat(dateStr, format));//获取指定日期的起始时间
        Date dateEnd = DateUtil.getSpecificDateEndTime(DateUtil.getSpecificDateFormat(dateStr, format));//获取指定日期的结束时间
        System.out.println(dateStart);
        System.out.println(dateEnd);
        System.out.println("--------------------");

        Date specificDateFormat1 = getSpecificDateFormat(new Date(),"yyyy-MM");
        System.out.println(specificDateFormat1);

        System.out.println("--------------------");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        // 输出字符串
        String dateFormat1 = df.format(new Date());
        System.out.println(dateFormat1);
        Date specificDateFormat2 = getSpecificDateFormat(dateFormat1, "yyyy-MM-");
        System.out.println(specificDateFormat2);

    }
}
