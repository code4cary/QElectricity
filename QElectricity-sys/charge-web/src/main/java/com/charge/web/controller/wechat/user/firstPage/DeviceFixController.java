package com.charge.web.controller.wechat.user.firstPage;

import com.charge.entity.model.fix.DeviceFixDO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by vincent on 17/09/2018.
 */

@RestController
@RequestMapping("/wechat/user/firstPage/")
public class DeviceFixController {

    @RequestMapping(value = "devicefix",method = RequestMethod.POST)
    @ResponseBody
    public void uploadDeviceFix(@RequestParam("file")MultipartFile file, DeviceFixDO deviceFixDO) {

    }

}
