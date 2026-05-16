package com.asd.k2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "新增/修改附件请求体")
public record AttachmentSaveRequest(
		@NotNull @Positive @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED) Long userId,
		@NotBlank @Size(max = 50) @Schema(description = "业务类型", requiredMode = Schema.RequiredMode.REQUIRED) String bizType,
		@NotNull @Positive @Schema(description = "业务ID", requiredMode = Schema.RequiredMode.REQUIRED) Long bizId,
		@NotBlank @Size(max = 255) @Schema(description = "文件名", requiredMode = Schema.RequiredMode.REQUIRED) String fileName,
		@NotBlank @Size(max = 500) @Schema(description = "文件URL", requiredMode = Schema.RequiredMode.REQUIRED) String fileUrl,
		@Positive @Schema(description = "文件大小（字节）") Long fileSize,
		@Size(max = 100) @Schema(description = "文件类型") String fileType) {
}
