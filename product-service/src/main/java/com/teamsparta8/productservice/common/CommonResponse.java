package com.teamsparta8.productservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
	private String resultCode;
	private String message;
	private T data;
	private Pagination pagination;

	// 성공한 작업의 Message 포함하여 전달
	public static <T> CommonResponse<T> OK(String message) {
		CommonResponse<T> response = new CommonResponse<>();
		response.resultCode = "OK";
		response.message = message;
		return response;
	}

	public static <T> CommonResponse<T> OK(T data, String message) {
		CommonResponse<T> response = new CommonResponse<>();
		response.resultCode = "OK";
		response.message = message;
		response.data = data;
		return response;
	}

	public static <T> CommonResponse<T> OK(T data, String message, Pagination pagination) {
		CommonResponse<T> response = new CommonResponse<>();
		response.resultCode = "OK";
		response.message = message;
		response.data = data;
		response.pagination = pagination;
		return response;
	}

	public static <T> CommonResponse<T> ERROR(String message){
		CommonResponse<T> response = new CommonResponse<>();
		response.resultCode = "ERROR";
		response.message = message;
		return response;
	}
}

