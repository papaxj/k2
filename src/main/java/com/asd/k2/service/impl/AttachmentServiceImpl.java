package com.asd.k2.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.asd.k2.common.Pageables;
import com.asd.k2.dto.AttachmentSaveRequest;
import com.asd.k2.entity.Attachment;
import com.asd.k2.mapper.AttachmentRepository;
import com.asd.k2.service.AttachmentService;
import com.asd.k2.vo.AttachmentVo;
import com.asd.k2.vo.PageResult;

@Service
@Transactional(readOnly = true)
public class AttachmentServiceImpl implements AttachmentService {

	private final AttachmentRepository attachmentRepository;

	public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
		this.attachmentRepository = attachmentRepository;
	}

	@Override
	public PageResult<AttachmentVo> page(int page, int size, Long userId, String bizType, Long bizId) {
		Pageable pageable = Pageables.of(page, size);
		Page<Attachment> result;
		if (userId != null) {
			result = attachmentRepository.findByUserId(userId, pageable);
		} else if (StringUtils.hasText(bizType) && bizId != null) {
			result = attachmentRepository.findByBizTypeAndBizId(bizType, bizId, pageable);
		} else {
			result = attachmentRepository.findAll(pageable);
		}
		return PageResult.of(result, page, this::toVo);
	}

	@Override
	public Optional<AttachmentVo> getById(Long id) {
		return attachmentRepository.findById(id).map(this::toVo);
	}

	@Override
	@Transactional
	public AttachmentVo create(AttachmentSaveRequest request) {
		Attachment entity = new Attachment();
		applyRequest(entity, request);
		return toVo(attachmentRepository.save(entity));
	}

	@Override
	@Transactional
	public Optional<AttachmentVo> update(Long id, AttachmentSaveRequest request) {
		return attachmentRepository.findById(id).map(entity -> {
			applyRequest(entity, request);
			return toVo(attachmentRepository.save(entity));
		});
	}

	@Override
	@Transactional
	public boolean deleteById(Long id) {
		if (!attachmentRepository.existsById(id)) {
			return false;
		}
		attachmentRepository.deleteById(id);
		return true;
	}

	private void applyRequest(Attachment entity, AttachmentSaveRequest request) {
		entity.setUserId(request.userId());
		entity.setBizType(request.bizType());
		entity.setBizId(request.bizId());
		entity.setFileName(request.fileName());
		entity.setFileUrl(request.fileUrl());
		entity.setFileSize(request.fileSize());
		entity.setFileType(request.fileType());
	}

	private AttachmentVo toVo(Attachment entity) {
		return new AttachmentVo(
				entity.getId(),
				entity.getUserId(),
				entity.getBizType(),
				entity.getBizId(),
				entity.getFileName(),
				entity.getFileUrl(),
				entity.getFileSize(),
				entity.getFileType(),
				entity.getCreatedAt());
	}
}
