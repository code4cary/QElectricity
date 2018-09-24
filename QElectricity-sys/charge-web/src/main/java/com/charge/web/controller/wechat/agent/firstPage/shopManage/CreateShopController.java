package com.charge.web.controller.wechat.agent.firstPage.shopManage;

import com.charge.common.utils.QEFileUtils;
import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.wechat.agent.CreateShopDO;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.service.biz.wechat.agent.AgentService;
import com.charge.service.biz.wechat.user.firstPage.ShopService;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by vincent on 17/09/2018.
 */

@Slf4j
@RestController
@RequestMapping("wechat/agent/firstPage/shopManage/")
public class CreateShopController extends BaseController {

    @Value("${fixImg.path}")
    private String imgPath;

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "createShop")
    @ResponseBody
    public CommonOutputDO<Object> uploadDeviceFixImg(@RequestParam("file") MultipartFile file, CreateShopDO createShopDO) throws Exception {

        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
        String filePath = QEFileUtils.getImgTimePath(imgPath) + fileName;
        FileCopyUtils.copy(file.getBytes(), new File(filePath));
        HashMap<String, String> map = new HashMap<>();
        //判断上传是否商户照片
        if(StringUtils.isNotEmpty(createShopDO.getShopPhoto()) && StringUtils.isEmpty(createShopDO.getId())){
            //插入操作
            Shop shop = new Shop();
            BeanUtils.copyProperties(createShopDO, shop);
            shop.setAgId(Integer.parseInt(createShopDO.getAgentID()));
            shop.setShopNo(UUID.randomUUID().toString().replace("-",""));
            shop.setShopPhoto(filePath);
            shop.setAddress(createShopDO.getShopAddress());
            shop.setName(createShopDO.getShopName());
            shop.setContractPersonName(createShopDO.getContractPerson());
            shop.setContractPersonPhone(createShopDO.getContractPhone());
            shop.setBusinessTime(createShopDO.getStartBusinessTime());
            shop.setCreateTime(new Date());
            int i = shopService.insertSelective(shop);
            if(i==0){
                return returnFailed(null,"插入商户图片失败");
            }
            map.put("id",shop.getId()+"");
        }else if(StringUtils.isNotEmpty(createShopDO.getContractPhoto())){
            Shop shop = shopService.selectByPrimaryKey(Integer.parseInt(createShopDO.getId()));
            if(null == shop){
                return returnFailed(null,"不存在该商户的信息");
            }
            //更新操作
            shop.setContractPhoto(filePath);
            int i = shopService.updateByPrimaryKeySelective(shop);
            if(i==0){
                return returnFailed(null,"更新合同图片失败");
            }
        }
            return returnSuccess(map);
    }

}
