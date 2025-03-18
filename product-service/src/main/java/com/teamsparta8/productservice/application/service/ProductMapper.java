package com.teamsparta8.productservice.application.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import com.teamsparta8.productservice.application.dtos.ProductCreateInternalDto;
import com.teamsparta8.productservice.application.dtos.ProductResponseInternalDto;
import com.teamsparta8.productservice.application.dtos.ProductUpdateInternalDto;
import com.teamsparta8.productservice.domain.entity.Product;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

	//DTO -> Model
	Product createToProduct(ProductCreateInternalDto requestDto);

	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "modifiedAt", ignore = true)
	@Mapping(target = "modifiedBy", ignore = true)
	@Mapping(target = "deletedAt", ignore = true)
	@Mapping(target = "deletedBy", ignore = true)
	Product updateToProduct(ProductUpdateInternalDto requestDto, @MappingTarget Product product);

	//Model -> DTO
	ProductResponseInternalDto productToResponse(Product product);

	//개별 Product를 ProductResponseInternalDto로 변환
	ProductResponseInternalDto productToDto(Product product);

	//기본 메서드를 사용하여 Page 매핑 처리
	default Page<ProductResponseInternalDto> productListToResponse(Page<Product> productList) {

		if (productList == null) return null;

		return productList.map(this::productToDto);
	}

}
