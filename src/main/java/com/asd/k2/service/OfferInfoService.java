package com.asd.k2.service;

import java.util.Optional;

import com.asd.k2.dto.OfferInfoSaveRequest;
import com.asd.k2.vo.OfferInfoVo;
import com.asd.k2.vo.PageResult;

public interface OfferInfoService {

	PageResult<OfferInfoVo> page(int page, int size, Long applicationId, String status);

	Optional<OfferInfoVo> getById(Long id);

	Optional<OfferInfoVo> getByApplicationId(Long applicationId);

	OfferInfoVo create(OfferInfoSaveRequest request);

	Optional<OfferInfoVo> update(Long id, OfferInfoSaveRequest request);

	boolean deleteById(Long id);
}
