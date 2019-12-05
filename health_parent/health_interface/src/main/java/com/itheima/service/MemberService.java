package com.itheima.service;

import com.itheima.pojo.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {
    //根据手机号查询会员
    public Member findByTelephone(String telephone);
    public void add(Member member);
    public  Map<String,Integer> findMemberCountByMonths(String before12month);
}
