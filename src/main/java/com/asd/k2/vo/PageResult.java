package com.asd.k2.vo;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "分页结果")
public record PageResult<T>(
		@Schema(description = "数据列表") List<T> content,
		@Schema(description = "当前页码（从1开始）", example = "1") int page,
		@Schema(description = "每页条数", example = "20") int size,
		@Schema(description = "总记录数", example = "100") long totalElements,
		@Schema(description = "总页数", example = "5") int totalPages) {

	public static <E, T> PageResult<T> of(Page<E> springPage, int pageOneBased, Function<E, T> mapper) {
		return new PageResult<>(
				springPage.getContent().stream().map(mapper).toList(),
				pageOneBased,
				springPage.getSize(),
				springPage.getTotalElements(),
				springPage.getTotalPages());
	}
}
