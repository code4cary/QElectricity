package com.charge.service.biz.wechat.user.impl;

import com.charge.dao.mapper.wechat.user.AccountMapper;
import com.charge.entity.po.back.wechat.user.ChargingRecordBack;
import com.charge.dao.mapper.device.PowerBankMapper;
import com.charge.dao.mapper.device.PriceTypeCBMapper;
import com.charge.dao.mapper.wechat.user.OrderMapper;
import com.charge.dao.mapper.wechat.user.UserMapper;
import com.charge.entity.po.device.PriceTypeCB;
import com.charge.entity.po.wechat.user.Account;
import com.charge.entity.po.wechat.user.Order;
import com.charge.entity.po.wechat.user.User;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @since: JDK1.7
 * @description:用户信息操作
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PriceTypeCBMapper priceTypeCBMapper;

    @Autowired
    private PowerBankMapper powerBankMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AccountMapper accountMapper;


    /**
     * @des:注入之后执行初始化方法通用mapper注入
     * @param:
     * @return：
     * @exception:
     */
    @PostConstruct
    @Override
    public void initBaseMapper() {

        setBaseMapper(userMapper);

    }

    @Cacheable(value = "catCache")//???
    @Override
    public User findUserByUserName(String userName) {
        User user = userMapper.findUserByUserName(userName);
        return user;
    }

    /**
     * 根据用户openId查询用户
     *
     * @param openId
     * @return
     */
    @Override
    public User findUserByOpenId(String openId) {

        return userMapper.findUserByOpenId(openId);
    }

    /**
     * 根据id跟新用户skey
     *
     * @param Id
     * @param skey
     */
    @Override
    public void updateSkeyById(Integer Id, String skey) {
        userMapper.updateSkeyById(Id, skey);
    }

    /**
     * 根据openId更新skey
     *
     * @param openId
     * @param skey
     */
    @Override
    public void updateSkeyByOpenId(String openId, String skey) {
        userMapper.updateSkeyByOpenId(openId, skey);
    }

    /**
     * 根据skey查询用户个人信息返回给前端个人中心页展示
     * 只需要查询:积分,余额,押金状态
     * 设计到关联表的查询
     *
     * @param skey
     * @return
     */
    @Override
    public Map<String, String> findUserInfoBySkey(String skey) {
        Map<String, String> personInfo = userMapper.findUserInfoBySkey(skey);
        return personInfo;
    }


    /**
     * 根据skey查询用户钱包信息返回给前端我的钱包页展示
     *
     * @param skey
     * @return
     */
    @Override
    public Map<String, String> findUserWalletInfoBySkey(String skey) {

        Map<String, String> userWalletInfo = userMapper.findUserWalletInfoBySkey(skey);
        return userWalletInfo;
    }

    /**
     * 根据skey查询用户充电记录数据返回前端个人中心页展示
     *
     * @param skey
     * @return
     */
    @Override
    public List<ChargingRecordBack> findUserChargingRecordBySkey(String skey) {

        //-- 1:先把订单记录查询出
        //根据skey在t_order表下关联查询该用户的账户下的订单
        List<Order> orderList = orderMapper.findUserOrderRecordBySkey(skey);
        System.out.println(orderList);

        List<ChargingRecordBack> chargingRecordList = new ArrayList<>();
        orderList.forEach(order -> {
            ChargingRecordBack chargingRecord = new ChargingRecordBack();
            Double timeAmount = null;
            //-- 2:判断订单中是否有未完成充电的订单,如果有,就要根据时间变化来计算费用
            //①如果用户正在使用充电宝
            if (order.getPowerBankStatus().equals("0")) {
                //获取创建时间(即用户充电开始时间)的时间戳,单位毫秒
                Long createTime = order.getCreateTime().getTime();
                System.out.println("createTime= " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getCreateTime()));
                //获取当前时间的时间戳,单位毫秒
                Long nowTime = new Date().getTime();
                System.out.println("nowTime= " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                //计算用户当前已使用充电宝时间,单位分钟
                timeAmount = Math.ceil(Double.valueOf(nowTime - createTime) / 1000.0 / 60.0);
                chargingRecord.setChargingTimeAmount(String.valueOf(new Double(timeAmount).intValue()));

                //查询该充电箱的定价策略
                //获得该充电箱的id
                String chargingBoxId = order.getBoxChargingId();//此id为充电箱id,不是编号
                //根据充电箱id去定价策略表查询该充电箱的定价策略
                PriceTypeCB priceTypeCB = priceTypeCBMapper.findPriceTypeCBByCbId(Integer.valueOf(chargingBoxId));


                //获取详细价格策略,免费时长,每小时价格,每日封顶都为整型
                //免费时长
                Integer freeTime = Integer.valueOf(priceTypeCB.getFreeTime());
                //每小时价格(0-10元/小时),不足一小时按一小时计算
                Integer pricePerHour = Integer.valueOf(priceTypeCB.getPricePerHour());
                //每日封顶(0-50/天)  这是根据每天价格来计算的
                Integer topPricePerDay = Integer.valueOf(priceTypeCB.getTopPricePerDay());

                //判断当前使用时间是否大于免费时长,如果不大于,费用就为0
                if (timeAmount <= freeTime) {
                    order.setPayAmount("0");
                } else {
                    timeAmount = timeAmount - freeTime;//减去免费时长后的使用时长

//                    //按当前使用小时数计算的费用
//                    Integer tempPayAmount = new Double(Math.ceil(timeAmount / 60.0)).intValue() * pricePerHour;


                    //总的使用时间,单位分钟
                    int totalUseTime = new Double(Math.ceil(timeAmount)).intValue();
                    //总的使用天数
                    int useDay = totalUseTime / 24 / 60;
                    //不够一整天的使用时间,min
                    int dayUseMin = totalUseTime % (24 * 60);
                    //不够一整天的使用时间,hour
                    int dayUseHour = new Double(Math.ceil(dayUseMin / 60.0)).intValue();

                    Integer totalPayAmount = useDay*topPricePerDay + (dayUseHour*pricePerHour>topPricePerDay?topPricePerDay:dayUseHour*pricePerHour);

                    order.setPayAmount(String.valueOf(totalPayAmount));

//                    //判断费用是否大于每日封顶费用
//                        if (tempPayAmount >= topPricePerDay) {//如果费用大于每日封顶了,这里会有两种情况:使用时长小于一天;使用时长大于一天
//                            if (timeAmount < 24 * 60) {//使用时长小于一天
//                                order.setPayAmount(String.valueOf(topPricePerDay));
//                            } else {//使用时长大于一天
//                                //获取时长大于一天的个数部分,即使用时长有多少个一天,向下取整
//                                Integer moreThanOneDay = new Double(Math.floor(timeAmount / (24.0 * 60.0))).intValue();
//                                //获取时长小于一天的部分,单位分钟
//                                Double lessThanOneDay = timeAmount - moreThanOneDay * 24 * 60;
//                                //时长小于一天的部分又要分为:费用达到每日封顶和没达到
//                                Integer tempPayAmountOneDay = new Double(Math.ceil(lessThanOneDay / 60.0)).intValue() * pricePerHour;
//                                if (tempPayAmountOneDay >= topPricePerDay) {//费用大于等于每日封顶
//                                    order.setPayAmount(String.valueOf((++moreThanOneDay) * topPricePerDay));//相当于使用时长不足一天的部分给它算一天
//                                } else {//费用小于每日封顶
//                                    order.setPayAmount(String.valueOf(tempPayAmountOneDay + lessThanOneDay * topPricePerDay));
//                                }
//
//                            }
//                    } else { //费用没大于每日封顶
//                        order.setPayAmount(String.valueOf(tempPayAmount));
//                    }
                }
            } else {//3:如果用户当前没有使用充电宝 --重新计算使用时长
                timeAmount = Math.ceil(Double.valueOf(order.getEndTime().getTime()
                        - order.getCreateTime().getTime()) / 1000.0 / 60.0);

                chargingRecord.setChargingTimeAmount(String.valueOf(new Double(timeAmount).intValue()));

            }

            //用户正在使用充电宝或没使用充电宝
            chargingRecord.setChargingStatus(order.getPowerBankStatus());
            //时间格式化下
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTime = df.format(order.getCreateTime());
            chargingRecord.setChargingStartTime(startTime);
            //金额
            chargingRecord.setChargingCost(order.getPayAmount());
            //获取充电宝sn,即充电宝编号
            String powerBankNO = powerBankMapper.findSNById(order.getPowerBankId());
            chargingRecord.setPowerBankNO(powerBankNO);

            chargingRecordList.add(chargingRecord);

        });

        return chargingRecordList;
    }


    /**
     * 根据skey获取用户的openid
     * @param skey
     * @return
     */
    @Override
    public String findUserOpenIdBySkey(String skey) {
        String openid = userMapper.findUserOpenIdBySkey(skey);
        return openid;
    }

    /**
     * 获取用户账户信息
     * @param skey
     * @return
     */
    @Override
    public Account findAccountInfo(String skey) {
        Account account = accountMapper.findAccountInfo(skey);
        return account;
    }

    /**
     * 查询当前用户是否归还成功充电宝,且是第一次查询该订单
     * @param skey
     * @return
     */
    @Override
    public Order findBackPowerBankInfo(String skey) {
        Order order = orderMapper.findBackPowerBankInfo(skey);
        return order;
    }

    /**
     * 更新订单HAVE_SHOWED字段为1
     * @param orderUpdate
     */
    @Override
    public void updateOrderHaveShowed(Order orderUpdate) {
        orderMapper.updateByPrimaryKeySelective(orderUpdate);
    }

}
