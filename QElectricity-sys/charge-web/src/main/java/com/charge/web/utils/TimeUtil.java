package com.charge.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vincent on 24/09/2018.
 */
public class TimeUtil {

    /**
     * 返回指定格式的日期
     */


    public static Date getSpecificDateFormat(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date dateFormat = df.parse(date);
        return dateFormat;
    }

}
