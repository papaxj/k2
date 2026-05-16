package com.asd.k2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "新增/修改面试问题请求体")
public record InterviewQuestionSaveRequest(
		@NotNull @Positive @Schema(description = "轮次ID", requiredMode = Schema.RequiredMode.REQUIRED) Long roundId,
		@Size(max = 50) @Schema(description = "分类") String category,
		@NotBlank @Schema(description = "问题", requiredMode = Schema.RequiredMode.REQUIRED) String question,
		@Schema(description = "我的回答") String myAnswer,
		@Schema(description = "参考答案") String correctAnswer,
		@Min(1) @Max(5) @Schema(description = "难度等级", example = "2") Integer difficultyLevel,
		@Min(0) @Max(1) @Schema(description = "是否答对 0否 1是") Integer isAnsweredCorrectly,
		@Schema(description = "备注") String remark) {
}
