package com.asd.k2.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.asd.k2.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

	Page<Attachment> findByUserId(Long userId, Pageable pageable);

	Page<Attachment> findByBizTypeAndBizId(String bizType, Long bizId, Pageable pageable);
}
