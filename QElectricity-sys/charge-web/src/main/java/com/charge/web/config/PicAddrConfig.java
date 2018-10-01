package com.charge.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by vincent on 01/10/2018.
 */
@Configuration
public class PicAddrConfig extends WebMvcConfigurerAdapter {

    //自定义静态资源文件路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/wechat/agent/shopManage/pic/**").//请求的路径
                addResourceLocations("file:/Users/vincent/program/java/img/shop/");//映射的路径
    }
}
