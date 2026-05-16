package com.asd.k2.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Offer返回对象")
public record OfferInfoVo(
		Long id,
		Long applicationId,
		Integer baseSalary,
		Integer bonusSalary,
		Integer stockValue,
		Integer signBonus,
		String otherBenefits,
		LocalDate offerDate,
		LocalDate deadlineDate,
		LocalDate joinDate,
		String status,
		String remark,
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {
}
