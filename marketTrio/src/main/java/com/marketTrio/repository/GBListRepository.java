package com.marketTrio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marketTrio.domain.GBEntity;
import com.marketTrio.domain.SecondHandEntity;

@Repository
public interface GBListRepository extends JpaRepository<GBEntity, Integer> {

	List<GBEntity> findByBuyerId(String memberId);
	
    List<GBEntity> findBySellerId(String memberId);
}
