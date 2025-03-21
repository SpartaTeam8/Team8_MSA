package com.teamsparta8.hub.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pagination {

	private Integer totalPages;

	private Long totalElements;

	private Integer currentPage;

	private Integer currentElements;
}

