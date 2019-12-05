package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import com.itheima.utils.DateUtils;
import com.itheima.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/*
    会员服务
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    //保存会员信息
    public void add(Member member) {
        String password = member.getPassword();
        if (password!=null){
            //使用md5将明文密码进行加密
            password = MD5Utils.md5(password);
            member.setPassword(password);
        }
        memberDao.add(member);
    }

    @Override
    public  Map<String,Integer> findMemberCountByMonths(String before12month) {
        Map<String,Integer> map=new HashMap<>();
        List<Map<String, Object>> memberCountBeforeDate = memberDao.findMemberCountBeforeDate(before12month);
        for (Map<String, Object> stringObjectMap : memberCountBeforeDate) {
            int count = Integer.parseInt(stringObjectMap.get("COUNT").toString());
            map.put(stringObjectMap.get("TIME").toString(),count);
        }
        return map;
    }


}
