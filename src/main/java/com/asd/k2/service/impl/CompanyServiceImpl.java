package com.asd.k2.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asd.k2.common.Pageables;
import com.asd.k2.dto.CompanySaveRequest;
import com.asd.k2.entity.Company;
import com.asd.k2.mapper.CompanyRepository;
import com.asd.k2.service.CompanyService;
import com.asd.k2.vo.CompanyVo;
import com.asd.k2.vo.PageResult;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public PageResult<CompanyVo> page(int page, int size, Long userId) {
		Pageable pageable = Pageables.of(page, size);
		Page<Company> result = userId != null
				? companyRepository.findByUserId(userId, pageable)
				: companyRepository.findAll(pageable);
		return PageResult.of(result, page, this::toVo);
	}

	@Override
	public Optional<CompanyVo> getById(Long id) {
		return companyRepository.findById(id).map(this::toVo);
	}

	@Override
	@Transactional
	public CompanyVo create(CompanySaveRequest request) {
		Company entity = new Company();
		applyRequest(entity, request);
		return toVo(companyRepository.save(entity));
	}

	@Override
	@Transactional
	public Optional<CompanyVo> update(Long id, CompanySaveRequest request) {
		return companyRepository.findById(id).map(entity -> {
			applyRequest(entity, request);
			return toVo(companyRepository.save(entity));
		});
	}

	@Override
	@Transactional
	public boolean deleteById(Long id) {
		if (!companyRepository.existsById(id)) {
			return false;
		}
		companyRepository.deleteById(id);
		return true;
	}

	private void applyRequest(Company entity, CompanySaveRequest request) {
		entity.setUserId(request.userId());
		entity.setName(request.name());
		entity.setIndustry(request.industry());
		entity.setWebsite(request.website());
		entity.setCity(request.city());
		entity.setCompanySize(request.companySize());
		entity.setFinancingStage(request.financingStage());
		entity.setAddress(request.address());
		entity.setHrName(request.hrName());
		entity.setHrContact(request.hrContact());
		entity.setRemark(request.remark());
		entity.setStatus(request.status() != null ? request.status() : 1);
	}

	private CompanyVo toVo(Company entity) {
		return new CompanyVo(
				entity.getId(),
				entity.getUserId(),
				entity.getName(),
				entity.getIndustry(),
				entity.getWebsite(),
				entity.getCity(),
				entity.getCompanySize(),
				entity.getFinancingStage(),
				entity.getAddress(),
				entity.getHrName(),
				entity.getHrContact(),
				entity.getRemark(),
				entity.getStatus(),
				entity.getCreatedAt(),
				entity.getUpdatedAt());
	}
}
