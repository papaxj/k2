package com.asd.k2.controller;

import java.time.Instant;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asd.k2.common.ResponseCode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "示例", description = "示例与健康检查类接口")
@RestController
@RequestMapping("/api")
public class SampleController {

	@Operation(summary = "Hello 示例", description = "返回欢迎语与当前时间戳")
	@ApiResponse(responseCode = ResponseCode.Http.OK, description = "成功",
			content = @Content(schema = @Schema(example = "{\"message\":\"Hello from k2\",\"timestamp\":\"2026-05-16T00:00:00Z\"}")))
	@GetMapping("/hello")
	public Map<String, Object> hello() {
		return Map.of(
				"message", "Hello from k2",
				"timestamp", Instant.now().toString());
	}

}
