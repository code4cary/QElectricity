package com.charge.web.controller.device;

import com.charge.dao.mapper.device.PowerBankMapper;
import com.charge.dao.mapper.device.PriceTypeCBMapper;
import com.charge.dao.mapper.wechat.agent.ShopMapper;
import com.charge.dao.mapper.wechat.user.OrderMapper;
import com.charge.entity.model.CommonOutputDO;
import com.charge.entity.po.device.PowerBank;
import com.charge.entity.po.device.PriceTypeCB;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.entity.po.wechat.user.Order;
import com.charge.web.controller.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * Created by vincent on 01/10/2018.
 */
@Slf4j
@RestController
@RequestMapping("device/")
public class BackPowerBankController extends BaseController {

    @Autowired
    private PowerBankMapper powerBankMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PriceTypeCBMapper priceTypeCBMapper;

    @Autowired
    private ShopMapper shopMapper;


    /**
     * 还充电宝接口
     *
     * @param queryData powerBankID:充电宝ID(实际为充电宝编号)
     *                  powerBankEnergy:充电宝电量
     *                  chargingBoxNo:充电箱编号
     *                  lockID:锁ID
     * @return
     * @json {"powerBank":{"powerBankID":"","powerBankEnergy":""}}
     * {"chargingBox":{"chargingBoxNo":"","lockID":""}}
     */
    @Transactional
    @RequestMapping(value = "backPowerBank")
    public CommonOutputDO<Object> backPowerBank(Map<String, Map<String, String>> queryData) {
        if (!validateParam(queryData)) {
            return returnFailed(null, "参数为空异常");
        }

        log.info("用户归还充电宝...");
        //充电宝ID(实际为充电宝编号)
        String powerBankNO = queryData.get("powerBank").get("powerBankID");
        //充电宝电量
        String powerBankEnergy = queryData.get("powerBank").get("powerBankEnergy");
        //充电箱编号
        String chargingBoxNo = queryData.get("chargingBox").get("chargingBoxNo");
        //锁ID
        String lockID = queryData.get("chargingBox").get("lockID");

        //这里不用判断充电宝是否在数据库有记录,因为用户借充电宝的时候已经判断过了

        /**更新数据库订单表,充电宝表相关信息**/
        try {
            /**1:更新订单表**/
            //获取用户订单
            Order order = orderMapper.findOrderByPowerBankNO(powerBankNO);
            //计算费用,先获取价格策略表
            PriceTypeCB priceTypeCB = priceTypeCBMapper.findPriceTypeCBbyChargingBoxNo(order.getBoxChargingId());//此处的价格策略是用户借用充电宝处的充电箱的价格策略
            //获取详细价格策略,免费时长,每小时价格,每日封顶都为整型
            //免费时长,单位分钟
            Integer freeTime = Integer.valueOf(priceTypeCB.getFreeTime());
            //每小时价格(0-10元/小时),不足一小时按一小时计算
            Integer pricePerHour = Integer.valueOf(priceTypeCB.getPricePerHour());
            //每日封顶(0-50/天)  这是根据每天价格来计算的
            Integer topPricePerDay = Integer.valueOf(priceTypeCB.getTopPricePerDay());

            //计算充电宝使用时长,以秒为单位
            Long timeUseAmount = new Date().getTime() - order.getCreateTime().getTime();

            //判断当前使用时间是否大于免费时长,如果不大于,费用就为0
            if (timeUseAmount <= freeTime * 60) {
                order.setPayAmount("0");
            } else {
                Long timePriceAmount = timeUseAmount - freeTime;//减去免费时长后的使用时长,即会产生费用的时长

                //总的产生费用的时长,单位分钟
                int totalPriceTime = new Double(Math.ceil(timePriceAmount)).intValue();
                //不够一整天的产生费用的时长,day
                int priceDay = totalPriceTime / 24 / 60;
                //不够一整天的产生费用的时长,min
                int dayPriceMin = totalPriceTime % (24 * 60);
                //不够一整天的的产生费用的时长,hour
                int dayPriceHour = new Double(Math.ceil(dayPriceMin / 60.0)).intValue();

                Integer totalPayAmount = priceDay * topPricePerDay + (dayPriceHour * pricePerHour > topPricePerDay ? topPricePerDay : dayPriceHour * pricePerHour);
                //产生的金额
                order.setPayAmount(String.valueOf(totalPayAmount));
            }
            //归还充电宝的时间
            order.setEndTime(new Date());
            //归还充电宝的地点
            Shop shop = shopMapper.findShopAddress(chargingBoxNo);
            order.setPowerBankBackPlace(shop.getAddress());
            //订单中的充电宝的状态改为1
            order.setPowerBankStatus("1");
            //更新数据库订单信息
            orderMapper.updateByPrimaryKeySelective(order);

            /**2:更新充电宝表**/
            //获取充电宝编号
            PowerBank powerBank = powerBankMapper.findPowerBank(powerBankNO);
            powerBank.setLockId(lockID);
            //充电宝锁位状态：0关闭，1开启  这里江南充电宝归还后的锁位设置为1.另外一个锁位失败的借口
            //如果有对应着该用户借的充电宝锁位失败的数据的话,则将该充电宝的锁位状态更改为0
            powerBank.setLockStatus("1");
            powerBank.setPbCapacity(powerBankEnergy);
            //充电宝总的使用时间
            Long totalUseTime = Long.valueOf(powerBank.getTotalUseTime()) + timeUseAmount;
            powerBank.setTotalUseTime(String.valueOf(totalUseTime));

            //更新数据库充电宝表信息
            powerBankMapper.updateByPrimaryKeySelective(powerBank);
        } catch (Exception ex) {
            log.error("backPowerBank failed:" + ex.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return returnFailed(null, "后台处理用户归还充电宝失败");
        }

        log.info("over...");
        return returnSuccess(null);
    }

    private boolean validateParam(Map<String, Map<String, String>> queryData) {
        if (StringUtils.isEmpty(queryData) ||
                StringUtils.isEmpty(queryData.get("powerBank").get("powerBankID")) ||
                StringUtils.isEmpty(queryData.get("powerBank").get("powerBankEnergy")) ||
                StringUtils.isEmpty(queryData.get("chargingBox").get("chargingBoxNo")) ||
                StringUtils.isEmpty(queryData.get("chargingBox").get("lockID"))) {
            return false;
        }
        return true;
    }
}
