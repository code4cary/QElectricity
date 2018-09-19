package com.charge.web.controller.wechat.user.firstPage;

import com.charge.ChargeApplication;
import com.charge.entity.po.wechat.user.User;
import com.charge.service.biz.wechat.user.firstPage.UserService;
import com.charge.web.utils.RedisPoolUtil;
import com.charge.web.utils.UserEndecryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by vincent on 19/09/2018.
 */

/**
 * 接口测试方法
 * 测试LoginUserController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class LoginUserControllerTest {
    //
    public static void main(String... args) {

    }

    @Resource
    private UserService userService;

    @Test
    public void testDoLogin() throws Exception {

        String code = "123456qwerty";

        //获取用户的session_key和openID
        //JSONObject SessionKeyOpenId = getSessionKeyOrOpenId(code);
        //System.out.println("post请求获取的SessionAndopenId=" + SessionKeyOpenId);

        //用户的openID
        //String openId = SessionKeyOpenId.getString("openId");
        String openId = "VINCENT654321";

        //用户的session_key
        //String sessionKey = SessionKeyOpenId.getString("session_key");
        String sessionKey = "weijohn502991";
        System.out.println("openId=" + openId + ",session_key=" + sessionKey);
        System.out.println("---------------------------------------------");

        //将openId和session_key加生成唯一key
        String skey = UserEndecryptUtil.md5OpenIDSessionKey(openId, sessionKey, 2);
        System.out.println("skey= " + skey);
        System.out.println("---------------------------------------------");

        //先去redis查询是否有openId对应skey的用户
        String skeyRedis = RedisPoolUtil.getRedis(openId);
        System.out.println("skeyRedis= " + skeyRedis);
        System.out.println("-------------------------------------------------");

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
            System.out.println("user" + user);
            userNew.setWxOpenid(openId);
            userNew.setSkey(skey);
            userNew.setTelephone("5555");
            userNew.setCreateTime(new Date());
            userService.insert(userNew);
        } else {
            //更新已存在用户的skey并缓存一份skey进redis
            //更新数据库skey
            System.out.println("更新数据库skey...");
            userService.updateSkeyByOpenId(openId, skey);
        }

        //将openId和skey缓存入redis,以openId为键,skey为值;
        RedisPoolUtil.storeRedis(openId, skey);

    }
}
