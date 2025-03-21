package com.teamsparta8.productservice.application.dtos;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyResponseDto {

	private UUID companyId;
	private UUID hubId;
	private String companyName;
	private UUID userId;
}
