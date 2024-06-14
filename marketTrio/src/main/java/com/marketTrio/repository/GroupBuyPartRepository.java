package com.marketTrio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketTrio.domain.GBParticipantEntity;
import com.marketTrio.domain.Member;

public interface GroupBuyPartRepository extends JpaRepository<GBParticipantEntity, String> {
	// CRUD는 여기서 정의 안 하고 Service에서 바로 쓰면 됨

//	ID로 특정 객체 찾기
	GBParticipantEntity findByMemberId(String memberId);
	////수연 추가////
	List<GBParticipantEntity> findAllByMemberId(String memberId);
}