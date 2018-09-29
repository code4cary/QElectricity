package com.charge.service.biz.wechat.agent.impl;

import com.charge.dao.mapper.device.ChargingBoxMapper;
import com.charge.dao.mapper.device.PowerBankMapper;
import com.charge.dao.mapper.wechat.agent.ShopMapper;
import com.charge.dao.mapper.wechat.user.AccountMapper;
import com.charge.dao.mapper.wechat.user.OrderMapper;
import com.charge.dao.mapper.wechat.user.UserMapper;
import com.charge.entity.po.back.wechat.agent.DeviceManage;
import com.charge.entity.po.back.wechat.agent.DevicePop;
import com.charge.entity.po.device.ChargingBox;
import com.charge.entity.po.device.PowerBank;
import com.charge.entity.po.wechat.agent.Shop;
import com.charge.entity.po.wechat.user.Order;
import com.charge.service.biz.base.impl.BaseServiceImpl;
import com.charge.service.biz.device.BorrowChargingBox;
import com.charge.service.biz.wechat.agent.ChargingBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by vincent on 26/09/2018.
 */
@Service
public class ChargingBoxServiceImpl extends BaseServiceImpl<ChargingBox, Integer> implements ChargingBoxService {

    @Autowired
    private ChargingBoxMapper chargingBoxMapper;

    @Autowired
    private BorrowChargingBox borrowChargingBox;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private PowerBankMapper powerBankMapper;

    @Autowired
    private AccountMapper accountMapper;


    @Override
    public void initBaseMapper() {
        setBaseMapper(chargingBoxMapper);
    }

    /**
     * 通过agentId,statusRange,type,searchData查询代理商指定搜索内容,指定日期的订单记录
     *
     * @param queryDataMap
     * @return
     */
    @Override
    public List<DeviceManage> findDeviceManageInfo(Map<String, String> queryDataMap) {
        List<DeviceManage> deviceManageList = chargingBoxMapper.findDeviceManageInfo(queryDataMap);

        return deviceManageList;
    }

    /**
     * 通过agentId,type,operateType,deviceNO进行设备管理页操作:解绑或重启
     *
     * @param queryDataMap
     * @return
     */
    @Override
    public Boolean operateChargingBoxDeviceManagePage(Map<String, String> queryDataMap) {
        //获取操作类型
        String operateType = queryDataMap.get("operateType");
        //获取设备编号
        String deviceNO = queryDataMap.get("deviceNO");

        boolean isOperateSuccess = false;
        if (operateType.equals("0")) {//解绑操作
            int row = chargingBoxMapper.updateAgentInfo(queryDataMap);
            System.out.println(row);
            isOperateSuccess = row > 0 ? true : false;
        } else if (operateType.equals("1")) {//重启操作
            //向曾斌那边发起请求 对设备进行重启
            //如果收到曾斌那边返回成功的消息
            //充电箱是否有相关信息进行更新??? 有变化曾斌那边会自动请求接口告诉后台

        }

        return isOperateSuccess;

    }

    /**
     * 根据agentId,deviceNO查询代理商设备弹出页信息
     *
     * @param queryDataMap
     * @return
     */
    @Override
    public DevicePop findDevicePopInfo(Map<String, String> queryDataMap) {

        DevicePop devicePopInfo = chargingBoxMapper.findDevicePopInfo(queryDataMap);

        //请求设备中介(曾斌)该充电箱可弹出的窗口号为多少

        List<String> windowList = new ArrayList<>();
        //测试数据
        windowList.add("1");
        windowList.add("3");
        windowList.add("5");
        windowList.add("7");
        windowList.add("9");
        devicePopInfo.setWindowNo(windowList);

        return devicePopInfo;
    }

    /**
     * 根据agentId,operateType,deviceNO,windowNO操作设备弹出页操作:弹出或重启
     *
     * @param queryDataMap
     * @return
     */
    @Override
    public Boolean operateChargingBoxDevicePopPage(Map<String, Object> queryDataMap) {
        //获取操作类型 0:pop,1:reboot
        String operateType = (String) queryDataMap.get("operateType");

        Boolean isOperateSuccess = false;
        if (operateType.equals("0")) {//弹出充电宝
            //请求设备中介弹出设备
            //获取需要弹出的充电宝的窗口号
            List<String> windowNo = (List<String>) queryDataMap.get("windowNo");
            //获取设备号
            String deviceNO = (String) queryDataMap.get("deviceNO");
            //将deviceNO,windowNo传入相应请求接口


        } else if (operateType.equals("1")) {//重启设备
            //向曾斌那边发起请求 对设备进行重启

            //测试数据
            isOperateSuccess = true;
        }


        return isOperateSuccess;
    }


    /**
     * 分配充电箱,建立充电箱和商户的关系
     *
     * @param queryData
     * @return
     */
    @Override
    public Boolean AddDevice(Map<String, String> queryData) {


        //建立设备与商户的关系
        int updateRow = chargingBoxMapper.updateDeviceSID(queryData);

        return updateRow>0?true:false;
    }

    /**
     * 通过skey,deviceNo,判断当前用户扫描的充电箱有可借的充电宝
     * @param queryData
     * @return
     */
    @Override
    public String findCanBorrow(Map<String, String> queryData) {
        //判断当前充电箱是否在线以及手否有可借的充电宝
        ChargingBox chargingBox = chargingBoxMapper.findDeviceInfo(queryData.get("deviceNO"));
        if(!chargingBox.getStatus().equals(1) || chargingBox.getCanBorrowNum().equals(0)) {
                return null;
        }

        //请求设备打开指定充电箱的一个充电宝,并获取设备返回的充电宝编号信息,然后返还给后台
        Map<Object, Object> backDataMap = borrowChargingBox.borrowChargingBox(chargingBox.getBoxCustomerId());
        String powerBankNO = (String) backDataMap.get("powerBankID");

        //判断当前充电宝是否在数据库有记录,如果有,更新其相应信息;如果没有,为其建立数据库记录
        PowerBank powerBank = powerBankMapper.findPowerBank(powerBankNO);
        //为当前充电宝建立/更新记录
        powerBank.setLockId((String) backDataMap.get("lockID"));
        powerBank.setLockStatus("0");//0关闭，1开启
        powerBank.setLockOperationStatus("1");//0操作失败，1操作成功
        powerBank.setPbCustomerId(powerBankNO);
        powerBank.setPbStatus("0");//电宝状态：0借出，1在位
        powerBank.setUpdateTime(new Date());
        if (powerBank.getId() == null) {//当前充电宝在数据库没记录
            //为当前充电宝建立记录
            powerBank.setBorrowTimes("1");//首次借出次数设为1
            powerBank.setCreateTime(new Date());
            //插入数据库
            powerBankMapper.insertSelective(powerBank);
        } else {//当前充电宝在数据库已有记录,更新相关信息
            powerBank.setBorrowTimes(String.valueOf(Integer.valueOf(powerBank.getBorrowTimes()) + 1));
            //更新数据库
            powerBankMapper.updateByPrimaryKeySelective(powerBank);
        }

        //为用户创建新的订单
        //获取用户的账户id
        Integer aid = accountMapper.findAIDBySkey(queryData.get("skey"));
        Order order = new Order();
        order.setAid(aid);
        order.setOrderNum(UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());
        order.setCreateTime(new Date());
        order.setPayStatus("0");//0:未支付
        order.setOrderType("0");//0：借充电宝订单
        //查询用户借充电宝所在商户的地址
        Shop shop = shopMapper.findShopAddress(chargingBox.getBoxCustomerId());
        order.setPowerBankStatus(shop.getAddress());
        order.setPowerBankStatus("0");//0：租借中
        order.setPowerBankId(powerBankNO);//这里应该是充电宝的编号
        order.setBoxChargingId(chargingBox.getBoxCustomerId());//这里应该是充电箱的编号
        //插入数据库
        orderMapper.insertSelective(order);

        return powerBankNO;
    }
    
    public static void main(String... args) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        System.out.println(uuid);
    }

}
