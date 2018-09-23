package com.charge.web.controller.wechat.agent.login;

import com.charge.common.enums.StatusInfo;
import com.charge.service.biz.wechat.agent.AgentService;
import com.charge.web.utils.CommonDataReturnUtil;
import com.charge.web.utils.RedisPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by vincent on 15/09/2018.
 */
@RestController
@RequestMapping("wechat/agent/loginAgent")
public class LoginAgentController {

    @Autowired
    private AgentService agentService;

    @RequestMapping
    @ResponseBody
    public Map loginAgent(@RequestBody(required = true) Map<String, String> queryData) {

        //如果传入的参数不符合要求
        if (queryData == null || queryData.isEmpty()) CommonDataReturnUtil.requestFail(StatusInfo.FailInfo1);

        //获取用户名
        String username = queryData.get("username");
        //获取密码
        String password = queryData.get("password");

        //先去redis缓存查询有没该用户
        String password_redis = RedisPoolUtil.getRedis(username);

        //如果缓存里面没有该用户
        if (password_redis == null) {
            //去数据库查询是否有该用户
            String agentPwd = agentService.findPwdByName(username);
            if (agentPwd == null) {//用户不存在
                return CommonDataReturnUtil.requestSuccess(StatusInfo.FailInfo2, "loginAgentPage", "loginAgent", null);
            } else if (agentPwd != password) {//密码错误
                return CommonDataReturnUtil.requestSuccess(StatusInfo.FailInfo3, "loginAgentPage", "loginAgent", null);
            }
        } else if (password_redis != password) {//密码错误

            return CommonDataReturnUtil.requestSuccess(StatusInfo.FailInfo3, "loginAgentPage", "loginAgent", null);

        }

        //经过上面的逻辑处理后,该用户肯定存在,去数据库查询相应需要返回的信息
        String agentId= agentService.findAgentIdNoByName(username);

        //封装该用户的信息
        Map loginAgentBack = CommonDataReturnUtil.requestSuccess(StatusInfo.SuccessInfo1, "loginAgentPage", "loginAgent", agentId);

        return loginAgentBack;
    }


}

