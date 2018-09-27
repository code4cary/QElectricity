package com.charge.web.controller.wechat.agent.firstPage.shopManage;

import com.alibaba.fastjson.JSON;
import com.charge.ChargeApplication;
import com.charge.common.utils.QEFileUtils;
import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.service.biz.wechat.user.firstPage.ShopService;
import com.charge.web.controller.base.BaseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * Created by vincent on 27/09/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChargeApplication.class)
public class editShopControllerTest extends BaseController{

    @Value("${fixImg.path}")
    private String imgPath;

    @Autowired
    private ShopService shopService;
    //
    public static void main(String... args) {

    }

    @Test
    public void testEditeditShop() throws Exception {

        Shop shop = new Shop();
        shop.setAgId(1);
        shop.setId(1);
        shop.setName("过桥米线1");
        shop.setContractPersonName("宋大哥");
        shop.setContractPersonPhone("13618248426");
        shop.setBusinessTime("00:00-23:59");
        shop.setCreateTime(new Date());
        shop.setAddress("后瑞幼儿园旁边");


        //判断是否有图片更新
        String filePath = null;
        File file = new File("/Users/vincent/program/java/img/1.jpg");
        //File file = null;
        if (file != null) {//有图片更新
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
            filePath = QEFileUtils.getImgTimePath(imgPath) + fileName;
            FileCopyUtils.copy(file,new File(filePath));

            shop.setContractPhoto(filePath);
        }

        int updateRow = shopService.updateByPrimaryKeySelective(shop);

        CommonOutputDO commonOutputDO= updateRow>0?returnSuccess(null):returnFailed(null,"更新信息失败");
        Object json = JSON.toJSON(commonOutputDO);
        System.out.println(json);
    }
}