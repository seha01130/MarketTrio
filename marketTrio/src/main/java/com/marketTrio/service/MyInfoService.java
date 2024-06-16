package com.marketTrio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketTrio.dao.mybatis.MyBatisMemberDao;
import com.marketTrio.domain.Member;
import com.marketTrio.controller.MemberCommand;

@Service
public class MyInfoService {

    @Autowired
    private MyBatisMemberDao memberDao;

    public MemberCommand getMemberCommand(String memberId) throws Exception {
        Member member = memberDao.getMember(memberId);
        return new MemberCommand(member);
    }

    public String getPassword(String memberId) throws Exception {
        return memberDao.getPassword(memberId);
    }

    public void updateMember(Member member) throws Exception {
        memberDao.updateMember(member);
    }

    public void deleteMember(String memberId) throws Exception {
        memberDao.deleteMember(memberId);
    }
}
