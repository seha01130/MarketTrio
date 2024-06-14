package com.marketTrio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.marketTrio.domain.SecondHandEntity;

@Repository
public interface SHListRepository extends JpaRepository<SecondHandEntity, Integer> {

	List<SecondHandEntity> findByMember_Id(String memberId);
	   
    List<SecondHandEntity> findBySellerId(String memberId);
}
