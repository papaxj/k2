package com.asd.k2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "新增/修改用户请求体")
public record MyUserSaveRequest(
		@NotBlank(message = "姓名不能为空")
		@Size(max = 255, message = "姓名长度不能超过255")
		@Schema(description = "姓名", example = "张三", requiredMode = Schema.RequiredMode.REQUIRED)
		String name,

		@Size(max = 255, message = "别名长度不能超过255")
		@Schema(description = "别名", example = "zhangsan")
		String asName) {
}
