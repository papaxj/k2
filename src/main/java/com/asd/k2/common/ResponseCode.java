package com.asd.k2.common;

/**
 * 响应码常量：HTTP 状态码（OpenAPI {@code responseCode}）与业务错误码（响应体 {@code code}）。
 */
public final class ResponseCode {

	private ResponseCode() {
	}

	/** HTTP 状态码，用于 {@link io.swagger.v3.oas.annotations.responses.ApiResponse#responseCode()} */
	public static final class Http {

		private Http() {
		}

		public static final String OK = "200";

		public static final String CREATED = "201";

		public static final String NO_CONTENT = "204";

		public static final String BAD_REQUEST = "400";

		public static final String NOT_FOUND = "404";
	}

	/** 业务错误码，用于 {@link ValidationErrorResponse#code()} */
	public static final class Error {

		private Error() {
		}

		public static final String VALIDATION_ERROR = "VALIDATION_ERROR";

		public static final String BAD_REQUEST = "BAD_REQUEST";
	}
}
