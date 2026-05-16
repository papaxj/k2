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

import com.asd.k2.dto.JobApplicationSaveRequest;
import com.asd.k2.service.JobApplicationService;
import com.asd.k2.vo.JobApplicationVo;
import com.asd.k2.vo.PageResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Tag(name = "岗位申请", description = "job_application 表增删改查")
@Validated
@RestController
@RequestMapping("/api/job-applications")
public class JobApplicationController {

	private final JobApplicationService jobApplicationService;

	public JobApplicationController(JobApplicationService jobApplicationService) {
		this.jobApplicationService = jobApplicationService;
	}

	@Operation(summary = "分页查询岗位申请列表", description = "可选 userId 或 companyId 筛选")
	@GetMapping
	public PageResult<JobApplicationVo> list(
			@Parameter(description = "页码（从1开始）") @RequestParam(defaultValue = "1") @Min(1) int page,
			@Parameter(description = "每页条数（最大100）") @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size,
			@RequestParam(required = false) @Positive Long userId,
			@RequestParam(required = false) @Positive Long companyId) {
		return jobApplicationService.page(page, size, userId, companyId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<JobApplicationVo> get(@PathVariable @Positive Long id) {
		return jobApplicationService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<JobApplicationVo> create(@Valid @RequestBody JobApplicationSaveRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(jobApplicationService.create(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<JobApplicationVo> update(
			@PathVariable @Positive Long id,
			@Valid @RequestBody JobApplicationSaveRequest request) {
		return jobApplicationService.update(id, request).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
		if (!jobApplicationService.deleteById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
