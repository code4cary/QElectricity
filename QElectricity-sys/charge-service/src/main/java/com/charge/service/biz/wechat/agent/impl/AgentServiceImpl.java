package com.charge.service.biz.wechat.agent.impl;

import com.charge.dao.mapper.device.ChargingBoxMapper;
import com.charge.dao.mapper.device.PriceTypeCBMapper;
import com.charge.dao.mapper.wechat.agent.AgentMapper;
import com.charge.dao.mapper.wechat.agent.AgentWithdrawalMapper;
import com.charge.dao.mapper.wechat.agent.ShopMapper;
import com.charge.dao.mapper.wechat.user.OrderMapper;
import com.charge.entity.model.ModifyPriceTypeCBDO;
import com.charge.entity.po.back.wechat.agent.*;
import com.charge.entity.po.device.ChargingBox;
import com.charge.entity.po.wechat.agent.Agent;
import com.charge.entity.po.wechat.agent.MyAccount;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.entity.po.wechat.user.Order;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.wechat.agent.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by vincent on 23/09/2018.
 */
@Slf4j
@Service
@PropertySource(value = "classpath:earlyWarning.properties")
public class AgentServiceImpl extends BaseServiceImpl<Agent, Integer> implements AgentService {
    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ChargingBoxMapper chargingBoxMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private PriceTypeCBMapper priceTypeCBMapper;

    @Autowired
    private AgentWithdrawalMapper agentWithdrawalMapper;


    //充电箱预警数量阈值,即充电箱里充电宝可借数量少于多少时发生预警
    @Value("${earlyWarningThreshold}")
    private String earlyWarningThreshold;

    @Override
    public void initBaseMapper() {
        setBaseMapper(agentMapper);
    }


    /**
     * 根据代理商id(此id为代理商id_num)查询代理商首页相关信息
     *
     * @param agentId
     * @return
     */
    @Override
    public FirstPage findAgentInfoByIdNum(String agentId, Date dateStart, Date dateEnd) {

        //查询t_order表获取订单信息
        Date dateTodayStart = dateStart;
        Date dateTodayEnd = dateEnd;
        List<Order> todayOrdersCountAndIncome = orderMapper.findTodayOrdersByAgentId(agentId,
                dateTodayStart, dateTodayEnd);

        int todayIncome = 0;
        int todayOrdersNum = 0;
        for (Order order : todayOrdersCountAndIncome) {
            //计算今日订单收益,以及今日订单数
            //如果订单支付完成,才能算给代理商
            if (order.getPayStatus().equals("1")) {
                todayIncome += Integer.valueOf(order.getPayAmount());
                todayOrdersNum++;
            }
        }

        //查询t_agent表获取订单总数和总分成
        Agent totalOrderAndIncome = agentMapper.findTotalOrderAndShare(agentId);
        //订单总数
        String totalOrder = totalOrderAndIncome.getOrderNum();
        //总分成
        String totalSharing = totalOrderAndIncome.getTotalShare();

        //封装订单数据信息
        OrderData orderData = new OrderData();
        orderData.setTodayIncome(String.valueOf(todayIncome));
        orderData.setTodayOrder(String.valueOf(todayOrdersNum));
        orderData.setTotalOrder(String.valueOf(totalOrder));
        orderData.setTotalSharing(String.valueOf(totalSharing));

        //2:查询用代理商充电箱状态
        ChargingBoxStatus chargingBoxStatus = new ChargingBoxStatus();
        List<ChargingBox> chargingBoxList = chargingBoxMapper.findChargingBoxByAgentId(agentId);
        //充电箱总数量  根据agentId去t_charging_box表查询代理商拥有的充电箱数量
        int toTalNum = chargingBoxList.size();

        //充电箱离线数量
        int offlineNum = chargingBoxList.size();
        //充电箱预警数量
        int earlyWarningNum = 0;
        for (ChargingBox chargingBox : chargingBoxList) {
            if (chargingBox.getStatus().equals("1")) {
                offlineNum--;
                if (chargingBox.getBorrowNum().compareTo(earlyWarningThreshold) < 0) {
                    earlyWarningNum++;
                }
            }
        }

        toTalNum = toTalNum - offlineNum;
        //封装充电箱状态数据
        chargingBoxStatus.setToTalNum(String.valueOf(toTalNum));
        chargingBoxStatus.setOfflineNum(String.valueOf(offlineNum));


        //封装数据总的返回前端数据
        FirstPage firstPage = new FirstPage();
        //封装今日订单数据
        firstPage.setOrderData(orderData);
        //封装充电箱状态数据
        firstPage.setChargingBoxStatus(chargingBoxStatus);
        //封装预警数量数据
        Map<String, String> earlyWarningNumMap = new HashMap<>();
        earlyWarningNumMap.put("earlyWarningNum", String.valueOf(earlyWarningNum));
        firstPage.setEarlyWarningNum(earlyWarningNumMap);
        //封装代理商id数据
        Map<String, String> agentIdMap = new HashMap<>();
        agentIdMap.put("agentId", agentId);
        firstPage.setAgentId(agentIdMap);

        return firstPage;
    }

    /**
     * 根据用户名查询用户密码
     *
     * @param agentName
     * @return
     */
    @Override
    public String findPwdByName(String agentName) {
        String tmpPwd = agentMapper.findPwdByName(agentName);
        return tmpPwd;
    }


    /**
     * 根据用户名查询该用户的ID_NUMBER
     *
     * @param
     * @return
     */
    @Override
    public String findAgentIdNoByName(String username) {

        String agentId = agentMapper.findAgentIdNoByName(username);

        return agentId;
    }


    /**
     * 根据agentId查询代理商的今日收益详情
     *
     * @param agentId
     * @param
     * @return
     */
    @Override
    public List<TodayIncome> findTodayIncomeDetail(String agentId, Date dateStart, Date dateEnd) {

        //先查询代理商名下的商户
        List<Shop> shopList = shopMapper.findShopByAgentId(agentId);

        List<TodayIncome> todayIncomeList = new ArrayList<>();
        shopList.forEach(shop -> {
            TodayIncome todayIncome = new TodayIncome();
            //封装商户名
            todayIncome.setShopName(shop.getName());
            //通过商户id去查询指定日期的订单
            // System.out.println(shop.getId());
            Integer shopId = shop.getId();
            List<Order> orderList = orderMapper.findOrdersByIdAndDate(shopId, dateStart, dateEnd);
            //算出总收益
            int totalIncome = 0;
            for (Order order : orderList) {
                if (order.getPayStatus().equals("1")) {
                    totalIncome += Integer.valueOf(order.getPayAmount());
                }
            }
            //封装总收益
            todayIncome.setTotalIncome(String.valueOf(totalIncome));
            todayIncomeList.add(todayIncome);

        });

        //排序,将每个商户下的当日收益按最高到最低排序
        Collections.sort(todayIncomeList, (s1, s2) -> s2.getTotalIncome().compareTo(s1.getTotalIncome()));
        return todayIncomeList;
    }

    /**
     * 通过agentId,searchData查询代理商名下的商户拥有的充电箱的预警详情
     *
     * @param queryDataMap
     * @return
     */
    @Override
    public List<EarlyWarning> findEarlyWarning(Map<String, String> queryDataMap) {
        List<EarlyWarning> EarlyWarningList = chargingBoxMapper.findEarlyWarning(queryDataMap);
        return EarlyWarningList;
    }

    /**
     * 通过agentId,价格策略修改指定商户的充电箱价格策略
     *
     * @param modifyPriceDO
     * @return
     */
    @Override
    public Boolean modifyPrice(ModifyPriceTypeCBDO modifyPriceDO) {
        int row = priceTypeCBMapper.modifyPrice(modifyPriceDO);
        return row > 0 ? true : false;
    }

    /**
     * 查询代理商我的账户页数据
     *
     * @param queryData
     * @return
     */
    @Override
    public MyAccount getMyAccountInfo(Map<String, String> queryData) {

        //直营总收入,即代理商总分成
        String totalIncomeDirect = agentMapper.findAgentTotalSharing(queryData.get("agentID"));

        //已提现金额,查询代理商的提现表,状态为已完成的提现记录的金额之和则为已提现金额
        Double withdrawalAmountDone = agentWithdrawalMapper.findAgentWithdrawalAmountDone(queryData);
        withdrawalAmountDone = new BigDecimal(withdrawalAmountDone).setScale(1, RoundingMode.DOWN).doubleValue();

        //可提现余额, 直营总收入 - 已提现金额
        Double withdrawalAmountCan = Double.valueOf(totalIncomeDirect) - withdrawalAmountDone;
        withdrawalAmountCan = new BigDecimal(withdrawalAmountCan).setScale(1, RoundingMode.DOWN).doubleValue();

        //子代理分成收入
        //查询子代理商名下的商户总收入,然后乘以(1-子代理商的的分成比例),即可得到从子代理商那里得到的分成收入
        //先查询该代理商的id,通过该代理商的id去查询子代理商的id
        String id = agentMapper.findIDByIdNum(queryData.get("agentID"));
        String subID = agentMapper.findSubAgentIdByParentId(id);
        if (id.equals(subID)) {
            log.info("子代理商id和父代理商id一样了,请查看后台系统哪里出了问题...");
            subID = null;
        }
        //根据子代理商的id查询其需要给到父代理商的分成金额,需要判断其有无父代理商:子代理商不是一级代理商以及父代理商字段不为空
        Double subAgentSharing = 0.0;
        if (subID != null) {
            subAgentSharing = orderMapper.findSubAgentSharingIncome(subID);
        }
        subAgentSharing = new BigDecimal(subAgentSharing).setScale(1, RoundingMode.DOWN).doubleValue();

        //封装数据
        MyAccount myAccount = new MyAccount();
        myAccount.setWithdrawalAmountCan(String.valueOf(withdrawalAmountCan));
        myAccount.setWithdrawalAmountDone(String.valueOf(withdrawalAmountDone));
        myAccount.setTotalIncomeDirect(String.valueOf(totalIncomeDirect));
        myAccount.setSubAgentSharing(String.valueOf(subAgentSharing));

        return myAccount;
    }


    /**
     * 通过agentId,date查询代理商的提现记录
     *
     * @param queryDataMap
     * @return
     */
    @Override
    public List<WithdrawalRecord> findAgentWithdrawalRecord(Map<String, Object> queryDataMap) {

        List<WithdrawalRecord> withdrawalRecordList =
                agentWithdrawalMapper.findAgentWithdrawalRecord(queryDataMap);

        return withdrawalRecordList;
    }

    /**
     * 通过agent id_num查询agent
     *
     * @param id_num
     * @return
     */
    @Override
    public Agent findAgentByAgentIDNum(String id_num) {
        String id = agentMapper.findIDByIdNum(id_num);
        return agentMapper.selectByPrimaryKey(Integer.valueOf(id));
    }

}
