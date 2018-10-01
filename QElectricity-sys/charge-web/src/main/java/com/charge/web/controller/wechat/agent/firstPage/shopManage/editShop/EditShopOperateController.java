package com.charge.web.controller.wechat.agent.firstPage.shopManage.editShop;

import com.charge.common.utils.QEFileUtils;
import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.wechat.agent.CreateShopDO;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.service.biz.wechat.user.ShopService;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * Created by vincent on 17/09/2018.
 */
@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/shopManage/editShop/operate")
public class EditShopOperateController extends BaseController {

    @Value("${shopImg.path}")
    private String imgPath;

    @Autowired
    private ShopService shopService;

    @RequestMapping
    public CommonOutputDO<Object> editShop(@RequestParam(required = false, value = "file") MultipartFile file, CreateShopDO editShopDO) throws Exception {

        if (!validateParam(editShopDO)) {
            returnFailed(null, "没有商户id");
        }

        Shop shop = new Shop();
        BeanUtils.copyProperties(editShopDO, shop);
        shop.setAgId(Integer.parseInt(editShopDO.getAgentID()));
        shop.setName(editShopDO.getShopName());
        shop.setContractPersonName(editShopDO.getContractPerson());
        shop.setContractPersonPhone(editShopDO.getContractPhone());
        shop.setBusinessTime(editShopDO.getStartBusinessTime());
        shop.setCreateTime(new Date());
        shop.setAddress(editShopDO.getShopAddress());


        //判断是否有图片更新
        if (file != null) {//有图片更新
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
            String filePath = QEFileUtils.getImgTimePath(imgPath) + fileName;
            FileCopyUtils.copy(file.getBytes(), new File(filePath));

            //判断更新信息中是带有更新商户店面照还是合同照还是没有照片
            if (StringUtils.isNotEmpty(editShopDO.getShopPhoto())) {//编辑更新商户照
                //编辑更新操作
                shop.setShopPhoto(filePath);
            } else if (StringUtils.isNotEmpty(editShopDO.getContractPhoto())) {//编辑更新合同照
                //更新操作
                shop.setContractPhoto(filePath);
            }
        }

        int updateRow = shopService.updateByPrimaryKeySelective(shop);
        if (updateRow == 0) {
            return returnFailed(null, "编辑更新商户信息失败");
        }
        return returnSuccess(null);

    }

    private boolean validateParam(CreateShopDO editShopDO) {
        if (StringUtils.isEmpty(editShopDO.getId())) {
            return false;
        }
        return true;
    }
}
