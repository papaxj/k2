package com.asd.k2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.asd.k2.common.Pageables;
import com.asd.k2.dto.OfferInfoSaveRequest;
import com.asd.k2.entity.OfferInfo;
import com.asd.k2.mapper.OfferInfoRepository;
import com.asd.k2.service.OfferInfoService;
import com.asd.k2.vo.OfferInfoVo;
import com.asd.k2.vo.PageResult;

@Service
@Transactional(readOnly = true)
public class OfferInfoServiceImpl implements OfferInfoService {

	private final OfferInfoRepository offerInfoRepository;

	public OfferInfoServiceImpl(OfferInfoRepository offerInfoRepository) {
		this.offerInfoRepository = offerInfoRepository;
	}

	@Override
	public PageResult<OfferInfoVo> page(int page, int size, Long applicationId, String status) {
		Pageable pageable = Pageables.of(page, size);
		Page<OfferInfo> result;
		if (applicationId != null) {
			List<OfferInfo> list = offerInfoRepository.findByApplicationId(applicationId).map(List::of).orElse(List.of());
			result = new PageImpl<>(list, pageable, list.size());
		} else if (StringUtils.hasText(status)) {
			result = offerInfoRepository.findByStatus(status, pageable);
		} else {
			result = offerInfoRepository.findAll(pageable);
		}
		return PageResult.of(result, page, this::toVo);
	}

	@Override
	public Optional<OfferInfoVo> getById(Long id) {
		return offerInfoRepository.findById(id).map(this::toVo);
	}

	@Override
	public Optional<OfferInfoVo> getByApplicationId(Long applicationId) {
		return offerInfoRepository.findByApplicationId(applicationId).map(this::toVo);
	}

	@Override
	@Transactional
	public OfferInfoVo create(OfferInfoSaveRequest request) {
		OfferInfo entity = new OfferInfo();
		applyRequest(entity, request);
		return toVo(offerInfoRepository.save(entity));
	}

	@Override
	@Transactional
	public Optional<OfferInfoVo> update(Long id, OfferInfoSaveRequest request) {
		return offerInfoRepository.findById(id).map(entity -> {
			applyRequest(entity, request);
			return toVo(offerInfoRepository.save(entity));
		});
	}

	@Override
	@Transactional
	public boolean deleteById(Long id) {
		if (!offerInfoRepository.existsById(id)) {
			return false;
		}
		offerInfoRepository.deleteById(id);
		return true;
	}

	private void applyRequest(OfferInfo entity, OfferInfoSaveRequest request) {
		entity.setApplicationId(request.applicationId());
		entity.setBaseSalary(request.baseSalary());
		entity.setBonusSalary(request.bonusSalary());
		entity.setStockValue(request.stockValue());
		entity.setSignBonus(request.signBonus());
		entity.setOtherBenefits(request.otherBenefits());
		entity.setOfferDate(request.offerDate());
		entity.setDeadlineDate(request.deadlineDate());
		entity.setJoinDate(request.joinDate());
		entity.setStatus(request.status() != null ? request.status() : "PENDING");
		entity.setRemark(request.remark());
	}

	private OfferInfoVo toVo(OfferInfo entity) {
		return new OfferInfoVo(
				entity.getId(),
				entity.getApplicationId(),
				entity.getBaseSalary(),
				entity.getBonusSalary(),
				entity.getStockValue(),
				entity.getSignBonus(),
				entity.getOtherBenefits(),
				entity.getOfferDate(),
				entity.getDeadlineDate(),
				entity.getJoinDate(),
				entity.getStatus(),
				entity.getRemark(),
				entity.getCreatedAt(),
				entity.getUpdatedAt());
	}
}
