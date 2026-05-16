package com.asd.k2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "新增/修改用户请求体")
public record MyUserSaveRequest(
		@NotBlank(message = "名称不能为空")
		@Size(max = 255, message = "名称长度不能超过255")
		@Schema(description = "名称", example = "张三", requiredMode = Schema.RequiredMode.REQUIRED)
		String name,

		@Size(max = 255, message = "别名长度不能超过255")
		@Schema(description = "别名", example = "zhangsan")
		String asName,

		@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "生日格式须为 yyyy-MM-dd，例如 2020-10-10")
		@Size(max = 10, message = "生日长度不能超过10")
		@Schema(description = "生日", example = "1990-05-20")
		String birthday,

		@Pattern(regexp = "^[MF]$", message = "性别须为 M（男）或 F（女）")
		@Size(max = 1, message = "性别长度不能超过1")
		@Schema(description = "性别：M-男，F-女", example = "M", allowableValues = { "M", "F" })
		String sex,

		@Email(message = "电子邮件格式不正确")
		@Size(max = 255, message = "电子邮件长度不能超过255")
		@Schema(description = "电子邮件", example = "zhangsan@example.com")
		String email,

		@Size(max = 255, message = "住址长度不能超过255")
		@Schema(description = "住址", example = "北京市朝阳区")
		String address,

		@Min(value = 0, message = "年龄不能小于0")
		@Max(value = 150, message = "年龄不能大于150")
		@Schema(description = "年龄", example = "30")
		Integer age) {
}
