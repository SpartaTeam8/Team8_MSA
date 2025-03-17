package com.teamsparta8.hub.presentation.dto;

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

