package com.itheima.dao;

import com.itheima.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    public void add(Order order);
    public List<Order> findByCondition(Order order);
    public Map findById4Detail(Integer id);
    public Integer findOrderCountByDate(String date);
    public Integer findOrderCountAfterDate(@Param("date") String date,@Param("thisWeeklastDay") String thisWeeklastDay);
    public Integer findOrderCountAftermonthDate(@Param("firstDay4ThisMonth") String firstDay4ThisMonth,@Param("LasttDay4ThisMonth") String LasttDay4ThisMonth);
    public Integer findVisitsCountByDate(String date);
    public Integer findVisitsCountAfterDate(String date);
    public List<Map> findHotSetmeal();
}
