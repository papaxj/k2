package com.asd.k2.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "面试轮次返回对象")
public record InterviewRoundVo(
		Long id,
		Long applicationId,
		Integer roundNo,
		String roundType,
		String interviewer,
		String interviewerTitle,
		String interviewMethod,
		String meetingLink,
		LocalDateTime interviewTime,
		Integer durationMinutes,
		String result,
		BigDecimal score,
		String summary,
		String feedback,
		LocalDateTime nextRoundTime,
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {
}
