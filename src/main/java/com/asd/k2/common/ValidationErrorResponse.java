package com.asd.k2.common;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "参数校验失败响应")
public record ValidationErrorResponse(
		@Schema(description = "错误码", example = "VALIDATION_ERROR") String code,
		@Schema(description = "错误说明", example = "请求参数校验失败") String message,
		@Schema(description = "字段级错误列表") List<FieldError> errors) {

	@Schema(description = "单个字段校验错误")
	public record FieldError(
			@Schema(description = "字段名", example = "name") String field,
			@Schema(description = "错误信息", example = "姓名不能为空") String message) {
	}
}
