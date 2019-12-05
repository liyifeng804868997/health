package com.itheima.dao;

import com.itheima.pojo.Menu;

import java.util.List;
import java.util.Map;

public interface MenusDao {
    List<Menu> findMenusByUserName(String username);
}
