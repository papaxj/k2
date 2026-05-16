package com.asd.k2.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.asd.k2.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Page<Company> findByUserId(Long userId, Pageable pageable);
}
