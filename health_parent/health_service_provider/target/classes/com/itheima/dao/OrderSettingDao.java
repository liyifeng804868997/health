package com.itheima.dao;

import com.itheima.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    public void add(OrderSetting orderSetting);
    public void editNumberByOrderDate(OrderSetting orderSetting);
    public long findCountByOrderDate(Date order);

    List<OrderSetting> getOrderSettingByMonth(Map<String, String> map);
    //更新已预约人数
    public void editReservationsByOrderDate(OrderSetting orderSetting);
    OrderSetting findByOrderDate(Date parseString2Date);
}
