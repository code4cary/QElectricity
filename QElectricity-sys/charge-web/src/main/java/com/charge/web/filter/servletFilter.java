package com.charge.web.filter;

import com.charge.common.enums.StatusInfo;
import com.charge.web.utils.CommonDataReturnUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by vincent on 18/09/2018.
 */

@WebFilter(filterName="paramFilter",urlPatterns="/*")
public class servletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("执行过滤操作");

        Map<String, String[]> parameterMap = request.getParameterMap();
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
