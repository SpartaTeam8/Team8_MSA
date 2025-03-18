package com.teamsparta8.order_service.presentatin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {

	private Integer totalPages;

	private Long totalElements;

	private Integer currentPage;

	private Integer currentElements;
}
