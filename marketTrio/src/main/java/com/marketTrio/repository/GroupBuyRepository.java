package com.marketTrio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marketTrio.domain.GBEntity;

public interface GroupBuyRepository extends JpaRepository<GBEntity, Integer> {
	// CRUD는 여기서 정의 안 하고 Service에서 바로 쓰면 됨
//	
//	List<GBPostEntity> findAll();
//	
//	//ID로 특정 객체 찾기
//	GBPostEntity findByGBPostId(int GBPostId);
}
