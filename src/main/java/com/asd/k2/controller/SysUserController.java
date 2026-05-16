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

import com.asd.k2.common.ValidationErrorResponse;
import com.asd.k2.dto.SysUserSaveRequest;
import com.asd.k2.service.SysUserService;
import com.asd.k2.vo.PageResult;
import com.asd.k2.vo.SysUserVo;

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

@Tag(name = "系统用户", description = "sys_user 表增删改查")
@Validated
@RestController
@RequestMapping("/api/sys-users")
public class SysUserController {

	private final SysUserService sysUserService;

	public SysUserController(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	@Operation(summary = "分页查询用户列表")
	@GetMapping
	public PageResult<SysUserVo> list(
			@Parameter(description = "页码（从1开始）") @RequestParam(defaultValue = "1") @Min(1) int page,
			@Parameter(description = "每页条数（最大100）") @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size) {
		return sysUserService.page(page, size);
	}

	@Operation(summary = "按 ID 查询用户")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = SysUserVo.class))),
			@ApiResponse(responseCode = "404", description = "用户不存在")
	})
	@GetMapping("/{id}")
	public ResponseEntity<SysUserVo> get(
			@Parameter(description = "用户主键 ID") @PathVariable @Positive Long id) {
		return sysUserService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "新增用户")
	@ApiResponses({
			@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = SysUserVo.class))),
			@ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
	})
	@PostMapping
	public ResponseEntity<SysUserVo> create(@Valid @RequestBody SysUserSaveRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(sysUserService.create(request));
	}

	@Operation(summary = "更新用户")
	@PutMapping("/{id}")
	public ResponseEntity<SysUserVo> update(
			@PathVariable @Positive Long id,
			@Valid @RequestBody SysUserSaveRequest request) {
		return sysUserService.update(id, request).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "删除用户（逻辑删除）")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
		if (!sysUserService.deleteById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
