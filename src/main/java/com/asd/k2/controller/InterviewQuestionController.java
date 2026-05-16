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

import com.asd.k2.dto.InterviewQuestionSaveRequest;
import com.asd.k2.service.InterviewQuestionService;
import com.asd.k2.vo.InterviewQuestionVo;
import com.asd.k2.vo.PageResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Tag(name = "面试问题", description = "interview_question 表增删改查")
@Validated
@RestController
@RequestMapping("/api/interview-questions")
public class InterviewQuestionController {

	private final InterviewQuestionService interviewQuestionService;

	public InterviewQuestionController(InterviewQuestionService interviewQuestionService) {
		this.interviewQuestionService = interviewQuestionService;
	}

	@Operation(summary = "分页查询面试问题列表", description = "可选 roundId 按轮次筛选")
	@GetMapping
	public PageResult<InterviewQuestionVo> list(
			@Parameter(description = "页码（从1开始）") @RequestParam(defaultValue = "1") @Min(1) int page,
			@Parameter(description = "每页条数（最大100）") @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size,
			@RequestParam(required = false) @Positive Long roundId) {
		return interviewQuestionService.page(page, size, roundId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<InterviewQuestionVo> get(@PathVariable @Positive Long id) {
		return interviewQuestionService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<InterviewQuestionVo> create(@Valid @RequestBody InterviewQuestionSaveRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(interviewQuestionService.create(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<InterviewQuestionVo> update(
			@PathVariable @Positive Long id,
			@Valid @RequestBody InterviewQuestionSaveRequest request) {
		return interviewQuestionService.update(id, request).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
		if (!interviewQuestionService.deleteById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
