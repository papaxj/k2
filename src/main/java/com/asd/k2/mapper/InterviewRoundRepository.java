package com.asd.k2.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.asd.k2.entity.InterviewRound;

public interface InterviewRoundRepository extends JpaRepository<InterviewRound, Long> {

	Page<InterviewRound> findByApplicationId(Long applicationId, Pageable pageable);
}
