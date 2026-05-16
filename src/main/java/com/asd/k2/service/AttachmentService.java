package com.asd.k2.service;

import java.util.Optional;

import com.asd.k2.dto.AttachmentSaveRequest;
import com.asd.k2.vo.AttachmentVo;
import com.asd.k2.vo.PageResult;

public interface AttachmentService {

	PageResult<AttachmentVo> page(int page, int size, Long userId, String bizType, Long bizId);

	Optional<AttachmentVo> getById(Long id);

	AttachmentVo create(AttachmentSaveRequest request);

	Optional<AttachmentVo> update(Long id, AttachmentSaveRequest request);

	boolean deleteById(Long id);
}
