package com.charge.web.controller.device;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vincent on 23/09/2018.
 */
@RestController
public class DeviceCommunicate {

    @RequestMapping("/device/communicateToSys")
    public String CommunicateToSys(HttpServletRequest request) {

        System.out.println(request);

        return "张海兵是大帅哥";


    }


}
