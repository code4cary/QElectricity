package com.charge.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    /**
     * 获取指定年月最后一天
     *
     * @param
     * @throws ParseException
     */
    //获取指定年月第一天 
    public static Calendar getlastDaySpecificMonth() {
        Calendar calstar = Calendar.getInstance();
        calstar.set(Calendar.DAY_OF_MONTH, 0);//最后一天
        return calstar;

    }


    /**
     * 某一个月第一天和最后一天
     *
     * @param date
     * @return
     */
    public static Map<String, String> getSpecificMonthFirstDayLastday(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date theDate = calendar.getTime();

        // 这个月第一天
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(
                " 00:00:00");
        day_first = str.toString();

        // 这个月最后一天
        calendar.add(Calendar.MONTH, 1); // 加一个月
        calendar.set(Calendar.DATE, 1); // 设置为该月第一天
        calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_last).append(
                " 23:59:59");
        day_last = endStr.toString();

        Map<String, String> map = new HashMap<String, String>();
        map.put("firstDay", day_first);
        map.put("lastDay", day_last);
        return map;
    }


    public static void main(String... args) throws ParseException {
        String format = "yyyy-MM";//日期格式
        Date date = DateUtil.getSpecificDateStartTime(DateUtil.getSpecificDateFormat("2018-09", format));//获取指定日期的起始时间

        Date specificDateFormat = DateUtil.getSpecificDateFormat("2018-08", "yyyy-MM");
        String firstDay = DateUtil.getSpecificMonthFirstDayLastday(specificDateFormat).get("firstDay");
        String lastDay = DateUtil.getSpecificMonthFirstDayLastday(specificDateFormat).get("lastDay");
        System.out.println(firstDay);
        System.out.println(lastDay);

        Date specificDateFormat1 = DateUtil.getSpecificDateFormat(firstDay, "yyyy-MM-dd HH:mm:ss");
        Date specificDateFormat2 = DateUtil.getSpecificDateFormat(lastDay, "yyyy-MM-dd HH:mm:ss");
        System.out.println(specificDateFormat1);
        System.out.println(specificDateFormat2);


    }
}
