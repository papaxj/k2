package com.asd.k2.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "新增/修改Offer请求体")
public record OfferInfoSaveRequest(
		@NotNull @Positive @Schema(description = "申请ID", requiredMode = Schema.RequiredMode.REQUIRED) Long applicationId,
		@Min(0) @Schema(description = "基础薪资") Integer baseSalary,
		@Min(0) @Schema(description = "奖金") Integer bonusSalary,
		@Min(0) @Schema(description = "股票价值") Integer stockValue,
		@Min(0) @Schema(description = "签字费") Integer signBonus,
		@Schema(description = "其他福利") String otherBenefits,
		@Schema(description = "Offer日期") LocalDate offerDate,
		@Schema(description = "截止日期") LocalDate deadlineDate,
		@Schema(description = "入职日期") LocalDate joinDate,
		@Size(max = 50) @Schema(description = "状态", example = "PENDING") String status,
		@Schema(description = "备注") String remark) {
}
