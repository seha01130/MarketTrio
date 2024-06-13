package com.marketTrio.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marketTrio.domain.OptionEntity;

public interface OptionRepository extends JpaRepository<OptionEntity, Integer> {

}
