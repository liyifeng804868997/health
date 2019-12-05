package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SetmealDao {

    public void add(Setmeal setmeal);

    void setSermealAndCheckGroup(Map<String, Integer> map);

    Page<Setmeal> findByCondition( @Param("value") String value);

    Setmeal findById(Integer id);

    List<Integer> findsetmealIdsByCheckGroupId(Integer id);

    List<Setmeal> findAll();

    void edit(Setmeal setmeal);

    void deleteAssoication(Integer id);

    int findcountSetmealAndCheckgroup(Integer id);

    void deleteSetmealAndCheckgroup(Integer id);

    void deleteSetmeal(Integer id);

    Setmeal findById2(int id);

    List<Map<String, Object>> findSetmealCount();

}
