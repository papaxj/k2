package com.asd.k2.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "新增/修改面试轮次请求体")
public record InterviewRoundSaveRequest(
		@NotNull @Positive @Schema(description = "申请ID", requiredMode = Schema.RequiredMode.REQUIRED) Long applicationId,
		@NotNull @Positive @Schema(description = "轮次", requiredMode = Schema.RequiredMode.REQUIRED) Integer roundNo,
		@Size(max = 50) @Schema(description = "轮次类型") String roundType,
		@Size(max = 100) @Schema(description = "面试官") String interviewer,
		@Size(max = 100) @Schema(description = "面试官职位") String interviewerTitle,
		@Size(max = 50) @Schema(description = "面试方式") String interviewMethod,
		@Size(max = 500) @Schema(description = "会议链接") String meetingLink,
		@Schema(description = "面试时间") LocalDateTime interviewTime,
		@Positive @Schema(description = "时长（分钟）") Integer durationMinutes,
		@Size(max = 50) @Schema(description = "结果", example = "PENDING") String result,
		@DecimalMin("0") @DecimalMax("100") @Schema(description = "评分") BigDecimal score,
		@Schema(description = "面试总结") String summary,
		@Schema(description = "反馈") String feedback,
		@Schema(description = "下一轮时间") LocalDateTime nextRoundTime) {
}
