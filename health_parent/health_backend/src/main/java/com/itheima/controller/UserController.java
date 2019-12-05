package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Reference
    private UserService userService;
    //获得当前登录用户的用户名
    @RequestMapping("/getUser")
    public Result getUsername(){
        //当Spring security完成认证后，会将当前用户信息保存到框架提供的上下文对象
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user!=null) {
            List<Map> lstMenu = userService.findMenuByUserName(user.getUsername());
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
            Map map=new HashMap();
            map.put("user",user);
            map.put("menus",lstMenu);
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,map);
        }

        return new Result(false, MessageConstant.GET_USERNAME_FAIL);
    }
}
