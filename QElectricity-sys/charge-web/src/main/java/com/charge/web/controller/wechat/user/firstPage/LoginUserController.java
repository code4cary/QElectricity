package com.charge.web.controller.wechat.user.firstPage;

import com.alibaba.fastjson.JSONObject;
import com.charge.service.biz.wechat.login.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
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
    public Map<String, Object> doLogin(@RequestParam(value = "code", required = false) String code) {

        //判断code是否合法
//        if (code == null || code.length() == 0) {
//            return StatusLoginWX.status(StatusLoginWX.ERROR_WX_LOGIN, StatusLoginWX.ERROR_DATA);//微信端小程序传来的数据错误
//        }

        //获取用户的session_key和openID
        JSONObject SessionKeyOpenId = getSessionKeyOrOpenId(code);
        System.out.println("post请求获取的SessionAndopenId=" + SessionKeyOpenId);

        //用户的openID
        String openid = SessionKeyOpenId.getString("openid");

        //用户的session_key
        String sessionKey = SessionKeyOpenId.getString("session_key");
        System.out.println("openid=" + openid + ",session_key=" + sessionKey);

        //去数据库查询openid是否存在,即是否存在该openid对应的这个用户
        //User user = userService.findByOpenid(openid);


        //将openid和session_key加生成唯一key
        //UserEndecryptUtil.md5OpenIDSessionKey(openid,sessionKey,2);

        //用于保存用户信息的map集合
        Map<String, Object> map = new HashMap<>();


//        if (user == null) {//用户为注册过
//            //将用户信息存入数据库
//
//        } else {//用户已注册过
//            //LOGGER.info("用户openid已存在,不需要插入");
//            return StatusLoginWX.status(StatusLoginWX.SUCCESS_WX_LOGIN,StatusLoginWX.SUCCESS_DATA);
//        }


        //把加密后的sessionKey和oppenid即skey返回给小程序
        //map.put("skey", skey);


        map.put("result", "0");


        return map;
    }
}
