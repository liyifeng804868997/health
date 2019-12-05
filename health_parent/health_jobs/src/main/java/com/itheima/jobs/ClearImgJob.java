package com.itheima.jobs;

import com.itheima.constant.RedisConstant;
import com.itheima.utils.aliyunUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/*
    定时清理垃圾图片
 */
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        //根据Redis中保存的两个set集合进行插值进行运算
        //sdiff现实差值
        Set<String> set=jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if (set!=null){
            for (String s : set) {
                aliyunUtils.deleteFileFromAliyun(s);
                //从redis集合中删除
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,s);
                System.out.println(666);
                System.out.println("正在执行");
                System.out.println("自定义任务执行，清理垃圾图片:"+s);
            }
        }
    }
}
