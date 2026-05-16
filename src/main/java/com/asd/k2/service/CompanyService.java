package com.asd.k2.service;

import java.util.Optional;

import com.asd.k2.dto.CompanySaveRequest;
import com.asd.k2.vo.CompanyVo;
import com.asd.k2.vo.PageResult;

public interface CompanyService {

	PageResult<CompanyVo> page(int page, int size, Long userId);

	Optional<CompanyVo> getById(Long id);

	CompanyVo create(CompanySaveRequest request);

	Optional<CompanyVo> update(Long id, CompanySaveRequest request);

	boolean deleteById(Long id);
}
