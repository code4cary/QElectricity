package com.charge.web.controller.wechat.user.firstPage;

import com.charge.common.enums.StatusInfo;
import com.charge.common.utils.QEFileUtils;
import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.model.fix.DeviceFixDO;
import com.charge.entity.po.device.Malfunction;
import com.charge.service.biz.wechat.user.firstPage.UserService;
import com.charge.service.biz.wechat.user.fix.MalfunctionService;
import com.charge.web.controller.base.BaseController;
import com.charge.web.utils.CommonDataReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by vincent on 17/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("/wechat/user/firstPage/")
public class DeviceFixController extends BaseController {

    @Value("${fixImg.path}")
    private String imgPath;

    @Autowired
    UserService userService;

    @Autowired
    MalfunctionService malfunctionService;

    @RequestMapping(value = "devicefixImg")
    @ResponseBody
    public CommonOutputDO<Object> uploadDeviceFixImg(@RequestParam("file") MultipartFile file, DeviceFixDO deviceFixDO, HttpServletRequest request) throws Exception {
        if (!validateParam(deviceFixDO)) {
            return returnFailed(null,"参属为空异常");
        }
        log.info("上传设备故障+图片 信息");
        //根据skey查询用户，失败返回
        String openId = userService.findUserOpenIdBySkey(deviceFixDO.getSkey());
        if (StringUtils.isEmpty(openId)) return returnFailed(null,"openId不存在");
        Malfunction malfunction = new Malfunction();
        BeanUtils.copyProperties(deviceFixDO, malfunction);
        malfunction.setCreateTime(new Date());
        malfunction.setWxOpenid(openId);
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
        String filePath = QEFileUtils.getImgTimePath(imgPath) + fileName;
        FileCopyUtils.copy(file.getBytes(), new File(filePath));
        //保存故障原因
        malfunction.setPhoto(filePath);
        malfunction.setPowerbankno("123");
        malfunctionService.insertSelective(malfunction);
        log.info("over");
        return returnSuccess(null);
    }

    @RequestMapping(value = "devicefix")
    @ResponseBody
    public CommonOutputDO<Object> uploadDeviceFix(@RequestBody DeviceFixDO deviceFixDO, HttpServletRequest request) {
        if (!validateParam(deviceFixDO)) {
            return returnFailed(null,"参属为空异常");
        }
        log.info("上传设备故障信息");
        //根据skey查询用户，失败返回
        String openId = userService.findUserOpenIdBySkey(deviceFixDO.getSkey());
        if (StringUtils.isEmpty(openId)) return returnFailed(null,"openId不存在");
        Malfunction malfunction = new Malfunction();
        BeanUtils.copyProperties(deviceFixDO, malfunction);
        malfunction.setCreateTime(new Date());
        malfunction.setWxOpenid(openId);
        //设置默认电箱编号
        malfunction.setPowerbankno("123");
        //保存故障原因
        malfunctionService.insertSelective(malfunction);
        log.info("over");
        return returnSuccess(null);
    }

    @RequestMapping("test")
    @ResponseBody
    public Map test() {
        throw new RuntimeException("1223");
        //return CommonDataReturnUtil.requestSuccess(StatusInfo.SuccessInfo1, "", "", null);
    }

    private boolean validateParam(DeviceFixDO data) {
        if (StringUtils.isEmpty(data.getChargingboxno()) || StringUtils.isEmpty(data.getReasons()) || StringUtils.isEmpty(data.getSkey()) || StringUtils.isEmpty(data.getWindowno())) {
            return false;
        }
        return true;
    }

}
