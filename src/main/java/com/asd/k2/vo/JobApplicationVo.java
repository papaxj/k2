package com.asd.k2.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "岗位申请返回对象")
public record JobApplicationVo(
		Long id,
		Long userId,
		Long companyId,
		String positionName,
		String department,
		String employmentType,
		String workCity,
		Integer salaryMin,
		Integer salaryMax,
		Integer salaryMonths,
		String jobDesc,
		String source,
		String sourceLink,
		LocalDate applyDate,
		String currentStage,
		String status,
		Integer priorityLevel,
		Integer expectedSalary,
		String remark,
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {
}
