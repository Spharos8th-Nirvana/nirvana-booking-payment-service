package com.nirvana.bookingpayment.global.response;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import tools.jackson.databind.json.JsonMapper;

class PageResponseTest {

	private final JsonMapper jsonMapper = JsonMapper.builder().build();

	@Test
	void Page에서_만든_응답은_content_page_size_totalElements_totalPages로_직렬화된다() throws Exception {
		PageImpl<String> page = new PageImpl<>(List.of("booking-003", "booking-004"), PageRequest.of(1, 2), 5);

		String json = jsonMapper.writeValueAsString(ApiResponse.success(PageResponse.from(page)));

		JSONAssert.assertEquals("""
			{
			  "success": true,
			  "data": {
			    "content": ["booking-003", "booking-004"],
			    "page": 1,
			    "size": 2,
			    "totalElements": 5,
			    "totalPages": 3
			  },
			  "error": null
			}
			""", json, true);
	}

	@Test
	void 첫_페이지의_page는_0이다() throws Exception {
		PageImpl<String> page = new PageImpl<>(List.of("booking-001"), PageRequest.of(0, 20), 1);

		String json = jsonMapper.writeValueAsString(PageResponse.from(page));

		JSONAssert.assertEquals("""
			{ "content": ["booking-001"], "page": 0, "size": 20, "totalElements": 1, "totalPages": 1 }
			""", json, true);
	}
}
