package com.charge.web.controller.device;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vincent on 23/09/2018.
 */
@RestController
@RequestMapping("/device/")
public class DeviceCommunicate {

    /**
     * 设备主动与后台系统通信的接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "deviceToSys")
    public String CommunicateToSys(HttpServletRequest request) {

        System.out.println(request);

        return "张海兵是大帅哥";

    }
}
