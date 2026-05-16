package com.asd.k2.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "新增/修改系统用户请求体")
public record SysUserSaveRequest(
		@NotBlank(message = "用户名不能为空")
		@Size(max = 50, message = "用户名长度不能超过50")
		@Schema(description = "用户名", example = "zhangsan", requiredMode = Schema.RequiredMode.REQUIRED)
		String username,

		@Size(max = 255, message = "密码长度不能超过255")
		@Schema(description = "密码（更新时可留空表示不修改）", example = "123456")
		String password,

		@Size(max = 50, message = "昵称长度不能超过50")
		@Schema(description = "昵称", example = "张三")
		String nickname,

		@Email(message = "邮箱格式不正确")
		@Size(max = 100, message = "邮箱长度不能超过100")
		@Schema(description = "邮箱", example = "zhangsan@example.com")
		String email,

		@Size(max = 255, message = "头像URL长度不能超过255")
		@Schema(description = "头像URL")
		String avatar,

		@Size(max = 30, message = "手机号长度不能超过30")
		@Schema(description = "手机号", example = "13800138000")
		String phone,

		@Min(value = 0, message = "状态值无效")
		@Max(value = 1, message = "状态值无效")
		@Schema(description = "状态：1正常 0禁用", example = "1")
		Integer status,

		@Schema(description = "最后登录时间")
		LocalDateTime lastLoginTime) {
}
