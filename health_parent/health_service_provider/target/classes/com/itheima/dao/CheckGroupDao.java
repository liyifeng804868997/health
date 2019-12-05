package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {

    public void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map<String, Integer> map);


    Page<CheckGroup> findByCondition(@Param("value") String value);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(CheckGroup checkGroup);

    void deleteAssoication(Integer id);

    long findCountByCheckGroupId(Integer id);

    void deleteById(Integer id);

    List<CheckGroup> findAll();
}
