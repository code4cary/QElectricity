package com.charge.web.controller.wechat.user.firstPage;

import com.charge.ChargeApplication;
import com.charge.common.utils.QEFileUtils;
import com.charge.dao.mapper.wechat.user.UserMapper;
import com.charge.entity.model.fix.DeviceFixDO;
import com.charge.entity.po.device.Malfunction;
import com.charge.service.biz.wechat.user.firstPage.UserService;
import com.charge.service.biz.wechat.user.fix.MalfunctionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * Created by vincent on 29/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class DeviceFixControllerTest {
    //
    public static void main(String... args) {

    }

    @Value("${fixImg.path}")
    private String imgPath;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MalfunctionService malfunctionService;

    /**
     * 再再上传故障图片
     *
     * @throws Exception
     */
    @Test
    //@Transactional(rollbackFor = Exception.class)//事务注解
    public void testUploadDeviceFixImg() throws Exception {

        //根据skey查询用户，失败返回

        DeviceFixDO deviceFixDO = new DeviceFixDO();
        deviceFixDO.setSkey("skey9876543210");
        Integer id = userMapper.findUserIdBySkey("skey9876543210");

        Malfunction malfunction = new Malfunction();
        BeanUtils.copyProperties(deviceFixDO, malfunction);
        malfunction.setCreateTime(new Date());
        malfunction.setId(14);
        System.out.println();

        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
        String filePath = QEFileUtils.getImgTimePath(imgPath) + fileName;
        File file = new File("/Users/vincent/program/java/img/4.jpg");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile mulFile = new MockMultipartFile("test", inputStream);

        FileCopyUtils.copy(mulFile.getBytes(), new File(filePath));
        //保存故障图片路径
        malfunction.setPhoto(filePath);
        System.out.println(malfunction);
        malfunctionService.updateByPrimaryKeySelective(malfunction);

    }

    /**
     * 先上传故障信息
     *
     * @throws Exception
     */
    @Test
    public void testUploadDeviceFix() throws Exception {

        DeviceFixDO deviceFixDO = new DeviceFixDO();
        deviceFixDO.setSkey("skey9876543210");
        deviceFixDO.setChargingboxno("MC101710123458");
        deviceFixDO.setReasons("1,3");
        deviceFixDO.setRemark("问题太多了...");
        deviceFixDO.setWindowno("1,3,5,7,9");
        String openId = userService.findUserOpenIdBySkey(deviceFixDO.getSkey());
        Malfunction malfunction = new Malfunction();
        BeanUtils.copyProperties(deviceFixDO, malfunction);
        malfunction.setCreateTime(new Date());
        malfunction.setWxOpenid(openId);
        //保存故障原因
        malfunctionService.insertSelective(malfunction);
    }
}