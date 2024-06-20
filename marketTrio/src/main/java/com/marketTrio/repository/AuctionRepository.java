package com.marketTrio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketTrio.domain.AuctionEntity;

public interface AuctionRepository extends JpaRepository<AuctionEntity, Integer> {
	List<AuctionEntity> findAll();
}
