package com.charge.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class QEFileUtils {

    private Logger logger = LoggerFactory.getLogger(QEFileUtils.class);

    //获取当前时间的文件目录
    public static String getImgTimePath(String rootPath) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String imgStr = rootPath + calendar.get(Calendar.YEAR) + System.getProperty("file.separator") + calendar.get(Calendar.MONTH) + System.getProperty("file.separator");
        validatePath(imgStr);
        return imgStr;
    }

    public static void main(String[] args) {
        System.out.println(getImgTimePath("C:/Users/sir/Desktop/img/"));
    }

    private static void validatePath(String path) {
        File file = new File(path);
        //目录不存在创建目录
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
