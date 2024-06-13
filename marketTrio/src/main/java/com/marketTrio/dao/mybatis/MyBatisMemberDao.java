package com.marketTrio.dao.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.marketTrio.dao.mybatis.mapper.MemberMapper;
import com.marketTrio.domain.Member;


@Repository
public class MyBatisMemberDao {
	@Autowired
	private MemberMapper memberMapper;
	
	public Member getMember(String id) throws DataAccessException {
		return memberMapper.getMemberById(id);
	}

	public Member getMember(String id, String password) throws DataAccessException {
		return memberMapper.getMemberByIdAndPassword(id, password);
	}
	
	public String getNickname(String id) throws DataAccessException {
		return memberMapper.getNicknameById(id);
	}

	public void insertMember(Member member) throws DataAccessException {
		memberMapper.insertMember(member);
	}

	public void updateMember(Member member) throws DataAccessException {
		memberMapper.updateMember(member);
	}
	
	public void deleteMember(String id) throws DataAccessException {
		memberMapper.deleteMember(id);
	}
}

