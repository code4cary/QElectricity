package com.charge.web.filter;

import com.charge.common.enums.StatusInfo;
import com.charge.web.utils.CommonDataReturnUtil;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import java.io.IOException;
import java.util.Map;

/**
 * Created by vincent on 18/09/2018.
 */

/**
 * 通用过滤器,过滤没有请求参数的请求
 */
//@WebFilter(filterName="commonServletFilter",urlPatterns="/*")
//@Order(1)
public class CommonServletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("执行过滤操作");

        //获得参数map
        Map<String, String[]> parameterMap = request.getParameterMap();
        //如果参数为空或长度为0,返回错误信息
        if (parameterMap == null || parameterMap .isEmpty()) {
            CommonDataReturnUtil.requestFail(StatusInfo.FailInfo1);
        }


        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
