package com.charge.web.controller.wechat.user.firstPage;

import com.alibaba.fastjson.JSONObject;
import com.charge.common.enums.StatusInfo;
import com.charge.entity.po.wechat.user.User;
import com.charge.service.biz.wechat.user.firstPage.UserService;
import com.charge.web.utils.CommonDataReturnUtil;
import com.charge.web.utils.RedisPoolUtil;
import com.charge.web.utils.UserEndecryptUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

import static com.charge.web.utils.WXUtil.getSessionKeyOrOpenId;

/**
 * Created by vincent on 17/09/2018.
 */
//@Controller，要想返回数据就用@RestController,
//这个注解对于返回数据比较方便，因为它会自动将对象实体转换为JSON格式。
//用于定义控制器类，在spring 项目中由控制器负责将用户发来的URL请求转发到对应的服务接口（service层），
// 一般这个注解在类中，通常方法需要配合注解@RequestMapping。
@Controller
@RequestMapping("/wechat/user/firstPage/loginUser")//小程序端用户授权登录
public class LoginUserController {

    @Resource
    private UserService userService;


    /**
     * 授权用户以微信方式登录小程序
     * <p>
     * 可以通过required=false或者true来要求@RequestParam配置的前端参数是否一定要传
     * required=false表示不传的话，会给参数赋值为null，required=true就是必须要有
     *
     * @param code 前端传过来的code  code有可能是用户session_key过期而传来的新的code;也可能是新的用户传来的code
     */
    @ResponseBody
    @RequestMapping
    public Map<String, Object> doLogin(@RequestBody(required = true) Map<String, String>  code) {

        //判断code是否合法
        if (code == null || code.isEmpty()) {
            return CommonDataReturnUtil.requestFail(StatusInfo.FailInfo0);//微信端小程序传来的数据错误
        }

        //获取用户的session_key和openID
        JSONObject SessionKeyOpenId = getSessionKeyOrOpenId(code.get("code"));

        //用户的openId
        String openId = SessionKeyOpenId.getString("openId");

        //用户的session_key
        String sessionKey = SessionKeyOpenId.getString("session_key");

        //将openId和session_key加生成唯一skey
        String skey = UserEndecryptUtil.md5OpenIDSessionKey(openId, sessionKey, 2);

        //先去redis查询是否有openId对应skey的用户
        String skeyRedis = RedisPoolUtil.getRedis(openId);

        //如果redis缓存没有openid,可能是过期了,也可能是该用户是新注册的.
        //再去数据库查询openId是否存在,即是否存在该openId对应的这个用户
        User user = null;
        if (skeyRedis == null || skeyRedis.length() == 0) {
            user = userService.findUserByOpenId(openId);
        }

        if ((skeyRedis == null || skeyRedis.length() == 0) && user == null) {//用户未注册过
            //将openId和skey存入数据库并缓存一份skey进redis
            //openId和skey存入数据库
            User userNew = new User();
            System.out.println(user);
            userNew.setWxOpenid(openId);
            userNew.setSkey(skey);
            userNew.setTelephone("5555");
            userNew.setCreateTime(new Date());
            userService.insert(userNew);
        } else {
            //更新已存在用户的skey并缓存一份skey进redis
            //更新数据库skey
            userService.updateSkeyByOpenId(openId, skey);
        }

        //将openId和skey缓存入redis,以openId为键,skey为值;
        RedisPoolUtil.storeRedis(openId, skey);

        return CommonDataReturnUtil.requestSuccess(StatusInfo.SuccessInfo1, "loginUser", "skey", skey);
    }
}
