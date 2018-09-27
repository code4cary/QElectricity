package com.charge.web.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by vincent on 28/09/2018.
 */
public class MathUtil {


    /**
     * 获取指定小数位数并四舍五入后的double值,
     *
     * @param value 需要转换格式的double值
     * @param num   需要保留的小数位数
     * @return
     */
    public Double getSpecificFormatDoubleUp(Double value, Integer num) {

        return new BigDecimal(value).setScale(num, RoundingMode.UP).doubleValue();

    }

    /**
     * 获取直接截取指定小数位位数的double值,
     *
     * @param value 需要转换格式的double值
     * @param num   需要保留的小数位数
     * @return
     */
    public Double getSpecificFormatDoubleDown(Double value, Integer num) {

        return new BigDecimal(value).setScale(num, RoundingMode.DOWN).doubleValue();

    }
}
