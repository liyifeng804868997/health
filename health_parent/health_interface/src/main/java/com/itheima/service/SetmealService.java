package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {
    public void add(Setmeal setmeal,Integer[] checkgroupIds);

    PageResult pageQuery(QueryPageBean queryPageBean);

    Setmeal findById(Integer id);

    List<Integer> findsetmealIdsByCheckGroupId(Integer id);

    List<Setmeal> findAll();

    void edit(Setmeal setmeal, Integer[] checkgroupIds);

    void delete(Integer id);

    Setmeal findById2(int id);

    List<Map<String, Object>> findSetmealCount();
}
