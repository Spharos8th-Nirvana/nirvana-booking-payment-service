package com.nirvana.bookingpayment.global.response;

// 입력 오류의 필드별 사유. 화면은 field로 잘못된 입력칸을 찾는다.
public record ErrorDetail(
	String field,
	String reason
) {
}
