package com.asd.k2.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "新增/修改岗位申请请求体")
public record JobApplicationSaveRequest(
		@NotNull @Positive @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED) Long userId,
		@NotNull @Positive @Schema(description = "公司ID", requiredMode = Schema.RequiredMode.REQUIRED) Long companyId,
		@NotBlank @Size(max = 100) @Schema(description = "岗位名称", requiredMode = Schema.RequiredMode.REQUIRED) String positionName,
		@Size(max = 100) @Schema(description = "部门") String department,
		@Size(max = 50) @Schema(description = "雇佣类型") String employmentType,
		@Size(max = 100) @Schema(description = "工作城市") String workCity,
		@Min(0) @Schema(description = "最低薪资") Integer salaryMin,
		@Min(0) @Schema(description = "最高薪资") Integer salaryMax,
		@Min(1) @Max(24) @Schema(description = "薪资月数", example = "12") Integer salaryMonths,
		@Schema(description = "职位描述") String jobDesc,
		@Size(max = 50) @Schema(description = "来源") String source,
		@Size(max = 500) @Schema(description = "职位链接") String sourceLink,
		@Schema(description = "投递日期") LocalDate applyDate,
		@Size(max = 50) @Schema(description = "当前阶段", example = "APPLIED") String currentStage,
		@Size(max = 50) @Schema(description = "状态", example = "PROCESSING") String status,
		@Min(1) @Max(3) @Schema(description = "优先级 1低 2中 3高", example = "2") Integer priorityLevel,
		@Min(0) @Schema(description = "期望薪资") Integer expectedSalary,
		@Schema(description = "备注") String remark) {
}
