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

import com.asd.k2.dto.CompanySaveRequest;
import com.asd.k2.service.CompanyService;
import com.asd.k2.vo.CompanyVo;
import com.asd.k2.vo.PageResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Tag(name = "公司管理", description = "company 表增删改查")
@Validated
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	private final CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@Operation(summary = "分页查询公司列表", description = "可选 userId 按用户筛选")
	@GetMapping
	public PageResult<CompanyVo> list(
			@Parameter(description = "页码（从1开始）") @RequestParam(defaultValue = "1") @Min(1) int page,
			@Parameter(description = "每页条数（最大100）") @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size,
			@Parameter(description = "用户ID") @RequestParam(required = false) @Positive Long userId) {
		return companyService.page(page, size, userId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyVo> get(@PathVariable @Positive Long id) {
		return companyService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<CompanyVo> create(@Valid @RequestBody CompanySaveRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(companyService.create(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyVo> update(
			@PathVariable @Positive Long id,
			@Valid @RequestBody CompanySaveRequest request) {
		return companyService.update(id, request).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
		if (!companyService.deleteById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
