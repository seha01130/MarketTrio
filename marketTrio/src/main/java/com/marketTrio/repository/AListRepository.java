package com.marketTrio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marketTrio.domain.AuctionEntity;
import com.marketTrio.domain.SecondHandEntity;

@Repository
public interface AListRepository extends JpaRepository<AuctionEntity, Integer> {

	List<AuctionEntity> findByBuyerId(String memberId);

	List<AuctionEntity> findBySellerId(String memberId);
}
