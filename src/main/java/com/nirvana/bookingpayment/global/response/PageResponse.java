package com.nirvana.bookingpayment.global.response;

import java.util.List;

import org.springframework.data.domain.Page;

// 모든 목록 API가 같은 모양으로 페이지를 응답하도록 한다.
public record PageResponse<T>(
	List<T> content,
	int page,
	int size,
	long totalElements,
	int totalPages
) {

	public static <T> PageResponse<T> from(Page<T> page) {
		return new PageResponse<>(
			page.getContent(),
			page.getNumber(),
			page.getSize(),
			page.getTotalElements(),
			page.getTotalPages()
		);
	}
}
