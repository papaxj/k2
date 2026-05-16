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

import com.asd.k2.dto.AttachmentSaveRequest;
import com.asd.k2.service.AttachmentService;
import com.asd.k2.vo.AttachmentVo;
import com.asd.k2.vo.PageResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Tag(name = "附件管理", description = "attachment 表增删改查")
@Validated
@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {

	private final AttachmentService attachmentService;

	public AttachmentController(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	@Operation(summary = "分页查询附件列表", description = "可选 userId 或 bizType+bizId 筛选")
	@GetMapping
	public PageResult<AttachmentVo> list(
			@Parameter(description = "页码（从1开始）") @RequestParam(defaultValue = "1") @Min(1) int page,
			@Parameter(description = "每页条数（最大100）") @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size,
			@RequestParam(required = false) @Positive Long userId,
			@RequestParam(required = false) String bizType,
			@RequestParam(required = false) @Positive Long bizId) {
		return attachmentService.page(page, size, userId, bizType, bizId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AttachmentVo> get(@PathVariable @Positive Long id) {
		return attachmentService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<AttachmentVo> create(@Valid @RequestBody AttachmentSaveRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(attachmentService.create(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AttachmentVo> update(
			@PathVariable @Positive Long id,
			@Valid @RequestBody AttachmentSaveRequest request) {
		return attachmentService.update(id, request).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
		if (!attachmentService.deleteById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
