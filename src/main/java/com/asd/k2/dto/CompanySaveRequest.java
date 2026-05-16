package com.asd.k2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "新增/修改公司请求体")
public record CompanySaveRequest(
		@NotNull(message = "用户ID不能为空")
		@Positive(message = "用户ID必须为正整数")
		@Schema(description = "用户ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
		Long userId,

		@NotBlank(message = "公司名称不能为空")
		@Size(max = 100, message = "公司名称长度不能超过100")
		@Schema(description = "公司名称", example = "示例科技", requiredMode = Schema.RequiredMode.REQUIRED)
		String name,

		@Size(max = 100) @Schema(description = "行业") String industry,
		@Size(max = 255) @Schema(description = "官网") String website,
		@Size(max = 100) @Schema(description = "城市") String city,
		@Size(max = 50) @Schema(description = "公司规模") String companySize,
		@Size(max = 50) @Schema(description = "融资阶段") String financingStage,
		@Size(max = 255) @Schema(description = "公司地址") String address,
		@Size(max = 100) @Schema(description = "HR姓名") String hrName,
		@Size(max = 100) @Schema(description = "HR联系方式") String hrContact,
		@Schema(description = "备注") String remark,
		@Min(0) @Max(1) @Schema(description = "状态", example = "1") Integer status) {
}
