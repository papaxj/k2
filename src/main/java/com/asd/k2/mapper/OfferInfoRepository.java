package com.asd.k2.mapper;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.asd.k2.entity.OfferInfo;

public interface OfferInfoRepository extends JpaRepository<OfferInfo, Long> {

	Optional<OfferInfo> findByApplicationId(Long applicationId);

	Page<OfferInfo> findByStatus(String status, Pageable pageable);
}
