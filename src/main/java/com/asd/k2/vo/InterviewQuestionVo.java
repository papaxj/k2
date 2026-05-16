package com.asd.k2.vo;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "面试问题返回对象")
public record InterviewQuestionVo(
		Long id,
		Long roundId,
		String category,
		String question,
		String myAnswer,
		String correctAnswer,
		Integer difficultyLevel,
		Integer isAnsweredCorrectly,
		String remark,
		LocalDateTime createdAt) {
}
