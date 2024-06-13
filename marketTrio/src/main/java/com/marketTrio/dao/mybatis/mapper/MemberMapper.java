package com.marketTrio.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.marketTrio.domain.Member;


@Mapper
public interface MemberMapper {
	Member getMemberById(String id);

	Member getMemberByIdAndPassword(String id, String password);
	
	String getNicknameById(String id);

	List<String> getNicknameList();
	  
	void insertMember(Member member);

	void updateMember(Member member);
	
	void deleteMember(String id);
}
