package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    //新增检查组，同时需要让检查组关联检查项
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //新增检查组，操作t_checkgroup表
        checkGroupDao.add(checkGroup);
        //设置检查组和检查项的多对多的关联关系，操作t_checkgroup_checkitem表
        Integer id = checkGroup.getId();
        this.setCheckGroupAndCheckItem(id,checkitemIds);
    }

    //分页查询
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    //根据检查组id查询关联的检查项id
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);

    }

    //编辑检查组信息，同时需要
    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //修改检查组基本信息，操作检查组t_checkgroup
        checkGroupDao.edit(checkGroup);
        //清理当前检查组关联的检查项，操作中间表t_checkgroup_checkitem表
        checkGroupDao.deleteAssoication(checkGroup.getId());
        //重新建立当前检查组和检查项的关联关系
        Integer checkGroupId = checkGroup.getId();
        this.setCheckGroupAndCheckItem(checkGroupId,checkitemIds);
    }


    @Override
    public void delete(Integer id) {
        //判断当前检查项是否已经关联到检查组
        long count= checkGroupDao.findCountByCheckGroupId(id);
        if (count>0){
            //当前检查项已经被关联到检查组，不允许删除
            new RuntimeException();
        }
        checkGroupDao.deleteAssoication(id);
        checkGroupDao.deleteById(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

    //建立检查组和检查项多对多关系
    public void setCheckGroupAndCheckItem(Integer checkGroupId,Integer[] checkitemIds){
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("checkgroupId", checkGroupId);
                map.put("checkitemId", checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
