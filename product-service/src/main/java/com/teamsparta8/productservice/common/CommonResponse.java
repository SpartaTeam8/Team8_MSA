package com.teamsparta8.productservice.common;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class CommonResponse<T> {
	private String resultCode;
	private String message;
	private T data;
	private Pagination pagination;

	// 성공한 작업의 Message 포함하여 전달
	public static <T> CommonResponse<T> OK(String message) {
		return CommonResponse.<T>builder()
			.resultCode("OK")
			.message(message)
			.build();
	}

	public static <T> CommonResponse<T> OK(T data, String message) {
		return CommonResponse.<T>builder()
			.resultCode("OK")
			.message(message)
			.data(data)
			.build();
	}

	public static <T> CommonResponse<T> OK(T data, String message, Pagination pagination) {
		return CommonResponse.<T>builder()
			.resultCode("OK")
			.message(message)
			.data(data)
			.pagination(pagination)
			.build();
	}

	public static <T> CommonResponse<T> ERROR(String message){
		return CommonResponse.<T>builder()
			.resultCode("ERROR")
			.message(message)
			.build();
	}
}

