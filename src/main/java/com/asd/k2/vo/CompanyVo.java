package com.asd.k2.vo;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "公司返回对象")
public record CompanyVo(
		@Schema(description = "主键 ID") Long id,
		@Schema(description = "用户ID") Long userId,
		@Schema(description = "公司名称") String name,
		@Schema(description = "行业") String industry,
		@Schema(description = "官网") String website,
		@Schema(description = "城市") String city,
		@Schema(description = "公司规模") String companySize,
		@Schema(description = "融资阶段") String financingStage,
		@Schema(description = "公司地址") String address,
		@Schema(description = "HR姓名") String hrName,
		@Schema(description = "HR联系方式") String hrContact,
		@Schema(description = "备注") String remark,
		@Schema(description = "状态") Integer status,
		@Schema(description = "创建时间") LocalDateTime createdAt,
		@Schema(description = "更新时间") LocalDateTime updatedAt) {
}
