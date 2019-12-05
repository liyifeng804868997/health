package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MenusDao;
import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private MenusDao menusDao;

    @Override
    //根据用户名查询数据库获取用户信息和关联的角色信息，同时需要查询角色关联的权限信息
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);  //查询用户基本信息，不包含用户角色
        if (user == null) {
            return null;
        }
        Integer id = user.getId();
        //根据用户id查询对应的角色
        Set<Role> roles = roleDao.findByUserId(id);
        for (Role role : roles) {
            Integer roleId = role.getId();
            //根据角色ID查询关联的权限
            Set<Permission> permissions = permissionDao.findByRoleId(roleId);
            role.setPermissions(permissions);//让角色关联权限
        }
        user.setRoles(roles);   //让用户关联角色
        return user;
    }

    @Override
    public List<Map> findMenuByUserName(String username) {
        List<Menu> lstMenu = menusDao.findMenusByUserName(username);
        List<Map> lstMenuMap = new ArrayList<>();
        for (Menu menu : lstMenu) {
            if (menu.getParentMenuId() == null) {
                Map map = new HashMap();
                map.put("path", menu.getPath());
                map.put("title", menu.getName());
                map.put("linkUrl", menu.getLinkUrl());
                map.put("children", findChildren(lstMenu, menu));
                lstMenuMap.add(map);
            }
        }
        return lstMenuMap;
    }

    public List<Map> findChildren(List<Menu> lstMenu, Menu menu) {
        List<Map> lstChildMenuMMap=new ArrayList<>();
        for (Menu childMenu : lstMenu) {
            if (menu.getId()==childMenu.getParentMenuId()){
                Map map = new HashMap();
                map.put("path", childMenu.getPath());
                map.put("title", childMenu.getName());
                map.put("linkUrl", childMenu.getLinkUrl());
                map.put("children", findChildren(lstMenu, childMenu));
                lstChildMenuMMap.add(map);
            }
        }
        return lstChildMenuMMap;
    }
}
