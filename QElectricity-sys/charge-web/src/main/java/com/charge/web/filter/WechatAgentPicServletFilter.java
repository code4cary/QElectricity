package com.charge.web.filter;

import com.charge.entity.po.wechat.agent.Agent;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.service.biz.wechat.agent.AgentService;
import com.charge.service.biz.wechat.user.ShopService;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by vincent on 19/09/2018.
 */

/**
 * 过滤微信用户端请求
 */
@Slf4j
//@Order(1)
//@Component
//@WebFilter(filterName = "wechatAgentPicServletFilter", urlPatterns = "/wechat/agent/shopManage/pic/*")
public class WechatAgentPicServletFilter extends BaseController implements Filter {

    @Autowired
    private AgentService agentService;

    @Autowired
    private ShopService shopService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("执行过滤操作");

        log.info("返回前端代理商请求的商户合同照或商户照");

        //String agentId = (String) request.getAttribute("agentId");

        //测试数据
        String agentID = request.getParameter("agentID");
        String shopID = request.getParameter("shopID");

        //String agentID = (String) request.getAttribute("agentID");
       // String shopID = (String) request.getAttribute("shopID");


        if (agentID == null || shopID == null) {
        } else {
            /**判断该代理商和商户时候存在,且该商户属于代理商呢**/
            Agent agent = agentService.findAgentByAgentIDNum(agentID);
            if (agent != null) {
                Shop shop = shopService.findShopInfoByShopId(shopID);
                if (shop != null) {
                    //判断该商户是否属于该代理商
                    if (agent.getId() == shop.getAgId()) {
                        chain.doFilter(request, response);
                    }
                }
            }
        }

        log.info("over...");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
