package com.charge.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by vincent on 19/09/2018.
 */

/**
 * 过滤微信用户端请求
 */
//@WebFilter(filterName="wechatUserServletFilterr",urlPatterns="/wechat/user/firstPage/*")
//@Order(2)
public class WechatUserServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("执行过滤操作");

        //判断是登录还是请求数据
        String code = (String) request.getAttribute("code");
        if ( code == null || code.length()==0) {
            //chain.doFilter();
        }



        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
