package com.asd.k2.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class Pageables {

	private static final int MAX_SIZE = 100;

	private Pageables() {
	}

	public static Pageable of(int page, int size) {
		return of(page, size, Sort.by(Sort.Direction.DESC, "id"));
	}

	public static Pageable of(int page, int size, Sort sort) {
		int pageIndex = Math.max(page, 1) - 1;
		int pageSize = Math.min(Math.max(size, 1), MAX_SIZE);
		return PageRequest.of(pageIndex, pageSize, sort);
	}
}
