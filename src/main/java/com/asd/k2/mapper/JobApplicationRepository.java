package com.asd.k2.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.asd.k2.entity.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

	Page<JobApplication> findByUserId(Long userId, Pageable pageable);

	Page<JobApplication> findByCompanyId(Long companyId, Pageable pageable);

	Page<JobApplication> findByUserIdAndCompanyId(Long userId, Long companyId, Pageable pageable);
}
