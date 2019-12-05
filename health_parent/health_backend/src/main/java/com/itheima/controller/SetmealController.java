package com.itheima.controller;

/*
    体验套餐管理
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.aliyunUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    //使用JedisPool操作Redis服务
    @Autowired
    private JedisPool jedisPool;

    //文件上传
    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        String originalFilename = imgFile.getOriginalFilename();   //原始文件名
        int i = originalFilename.lastIndexOf(".");
        String extention = originalFilename.substring(i - 1);    //.jpg
        String fileName = UUID.randomUUID().toString() + extention;   //随机生成一个36位的随机数
        try {
            //将文件上传到阿里云服务器
            aliyunUtils.upload2aliyun(imgFile.getBytes(), fileName);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);
    }

    @Reference
    private SetmealService setmealService;

    //新增套餐
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        try {
            setmealService.add(setmeal, checkgroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }
    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        String queryString = queryPageBean.getQueryString();
        if (queryString!=null||queryString==""){
            queryPageBean.setCurrentPage(1);
            return setmealService.pageQuery(queryPageBean);
        }else {
            return setmealService.pageQuery(queryPageBean);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Setmeal setmeal= setmealService.findById(id);
            return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }

    }

    @RequestMapping("/findsetmealIdsByCheckGroupId")
    public Result findsetmealIdsByCheckGroupId(Integer id) {
        try {
            List<Integer> list= setmealService.findsetmealIdsByCheckGroupId(id);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<Setmeal> list=  setmealService.findAll();
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    //编辑检查组
    @RequestMapping("/edit")
    public Result edit(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        try {
            setmealService.edit(setmeal, checkgroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            setmealService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_SETMEAL_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_SETMEAL_SUCCESS);
    }
}
