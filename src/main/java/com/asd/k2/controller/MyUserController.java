package com.asd.k2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asd.k2.common.ResponseCode;
import com.asd.k2.common.ValidationErrorResponse;
import com.asd.k2.dto.MyUserSaveRequest;
import com.asd.k2.service.MyUserService;
import com.asd.k2.vo.MyUserVo;
import com.asd.k2.vo.PageResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Tag(name = "用户管理", description = "my_user 表增删改查")
@Validated
@RestController
@RequestMapping("/api/my-users")
public class MyUserController {

	private final MyUserService myUserService;

	public MyUserController(MyUserService myUserService) {
		this.myUserService = myUserService;
	}

	@Operation(summary = "分页查询用户列表")
	@ApiResponse(responseCode = ResponseCode.Http.OK, description = "成功",
			content = @Content(schema = @Schema(implementation = PageResult.class)))
	@GetMapping
	public PageResult<MyUserVo> list(
			@Parameter(description = "页码（从1开始）") @RequestParam(defaultValue = "1") @Min(1) int page,
			@Parameter(description = "每页条数（最大100）") @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size) {
		return myUserService.page(page, size);
	}

	@Operation(summary = "按 ID 查询用户")
	@ApiResponses({
			@ApiResponse(responseCode = ResponseCode.Http.OK, description = "成功",
					content = @Content(schema = @Schema(implementation = MyUserVo.class))),
			@ApiResponse(responseCode = ResponseCode.Http.BAD_REQUEST, description = "参数校验失败",
					content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
			@ApiResponse(responseCode = ResponseCode.Http.NOT_FOUND, description = "用户不存在")
	})
	@GetMapping("/{id}")
	public ResponseEntity<MyUserVo> get(
			@Parameter(description = "用户主键 ID", example = "1")
			@PathVariable @Positive(message = "用户 ID 必须为正整数") Integer id) {
		return myUserService.getById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "新增用户")
	@ApiResponses({
			@ApiResponse(responseCode = ResponseCode.Http.CREATED, description = "创建成功",
					content = @Content(schema = @Schema(implementation = MyUserVo.class))),
			@ApiResponse(responseCode = ResponseCode.Http.BAD_REQUEST, description = "参数校验失败",
					content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
	})
	@PostMapping
	public ResponseEntity<MyUserVo> create(@Valid @RequestBody MyUserSaveRequest request) {
		MyUserVo created = myUserService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@Operation(summary = "更新用户")
	@ApiResponses({
			@ApiResponse(responseCode = ResponseCode.Http.OK, description = "更新成功",
					content = @Content(schema = @Schema(implementation = MyUserVo.class))),
			@ApiResponse(responseCode = ResponseCode.Http.BAD_REQUEST, description = "参数校验失败",
					content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
			@ApiResponse(responseCode = ResponseCode.Http.NOT_FOUND, description = "用户不存在")
	})
	@PutMapping("/{id}")
	public ResponseEntity<MyUserVo> update(
			@Parameter(description = "用户主键 ID", example = "1")
			@PathVariable @Positive(message = "用户 ID 必须为正整数") Integer id,
			@Valid @RequestBody MyUserSaveRequest request) {
		return myUserService.update(id, request)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "删除用户")
	@ApiResponses({
			@ApiResponse(responseCode = ResponseCode.Http.NO_CONTENT, description = "删除成功"),
			@ApiResponse(responseCode = ResponseCode.Http.BAD_REQUEST, description = "参数校验失败",
					content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
			@ApiResponse(responseCode = ResponseCode.Http.NOT_FOUND, description = "用户不存在")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(
			@Parameter(description = "用户主键 ID", example = "1")
			@PathVariable @Positive(message = "用户 ID 必须为正整数") Integer id) {
		if (!myUserService.deleteById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
