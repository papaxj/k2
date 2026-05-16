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

import com.asd.k2.dto.OfferInfoSaveRequest;
import com.asd.k2.service.OfferInfoService;
import com.asd.k2.vo.OfferInfoVo;
import com.asd.k2.vo.PageResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Tag(name = "Offer管理", description = "offer_info 表增删改查")
@Validated
@RestController
@RequestMapping("/api/offers")
public class OfferInfoController {

	private final OfferInfoService offerInfoService;

	public OfferInfoController(OfferInfoService offerInfoService) {
		this.offerInfoService = offerInfoService;
	}

	@Operation(summary = "分页查询 Offer 列表", description = "可选 applicationId 或 status 筛选")
	@GetMapping
	public PageResult<OfferInfoVo> list(
			@Parameter(description = "页码（从1开始）") @RequestParam(defaultValue = "1") @Min(1) int page,
			@Parameter(description = "每页条数（最大100）") @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size,
			@RequestParam(required = false) @Positive Long applicationId,
			@RequestParam(required = false) String status) {
		return offerInfoService.page(page, size, applicationId, status);
	}

	@Operation(summary = "按申请 ID 查询 Offer")
	@GetMapping("/by-application/{applicationId}")
	public ResponseEntity<OfferInfoVo> getByApplicationId(@PathVariable @Positive Long applicationId) {
		return offerInfoService.getByApplicationId(applicationId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{id}")
	public ResponseEntity<OfferInfoVo> get(@PathVariable @Positive Long id) {
		return offerInfoService.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<OfferInfoVo> create(@Valid @RequestBody OfferInfoSaveRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(offerInfoService.create(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<OfferInfoVo> update(
			@PathVariable @Positive Long id,
			@Valid @RequestBody OfferInfoSaveRequest request) {
		return offerInfoService.update(id, request).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
		if (!offerInfoService.deleteById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
