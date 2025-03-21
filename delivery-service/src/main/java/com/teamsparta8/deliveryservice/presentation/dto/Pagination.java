package com.teamsparta8.deliveryservice.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {

	private Integer totalPages;

	private Long totalElements;

	private Integer currentPage;

	private Integer currentElements;
}

