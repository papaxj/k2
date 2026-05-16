package com.asd.k2.vo;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "附件返回对象")
public record AttachmentVo(
		Long id,
		Long userId,
		String bizType,
		Long bizId,
		String fileName,
		String fileUrl,
		Long fileSize,
		String fileType,
		LocalDateTime createdAt) {
}
