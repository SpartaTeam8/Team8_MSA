package com.teamsparta8.order_service.infrastructure.util;

import java.util.List;
import java.util.function.Function;

import com.querydsl.core.QueryResults;
import com.teamsparta8.order_service.presentatin.dto.Pagination;

public class PaginationUtil {

	public static <T, R> Pagination createPagination(QueryResults<T> queryResults, int page, int size) {
		return new Pagination(
			(int) Math.ceil((double) queryResults.getTotal() / size),  // 총 페이지 수 계산
			queryResults.getTotal(),  // 전체 데이터 개수
			page,  // 현재 페이지 번호
			queryResults.getResults().size() // 현재 페이지의 데이터 개수
		);
	}

	public static <T, R> List<R> mapResults(QueryResults<T> queryResults, Function<T, R> mapper) {
		return queryResults.getResults().stream()
			.map(mapper)
			.toList();
	}
}
