package com.teamsparta8.productservice.infrastructure.persistence;

import static com.teamsparta8.productservice.domain.entity.QProduct.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamsparta8.productservice.domain.entity.Product;
import com.teamsparta8.productservice.domain.entity.SortBy;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductQueryDslRepositoryImpl implements ProductQueryDslRepository {

	private final JPAQueryFactory queryFactory;

	private static final int DEFAULT_SIZE = 10;
	private static final List<Integer> ALLOWED_PAGE_SIZES = Arrays.asList(10, 30, 50);
	
	//모든 상품 조회
	@Override
	public Page<Product> getProductSearch(String keyword, Pageable pageable, SortBy sortBy) {
		int pageSize = validatePageSize(pageable.getPageSize());

		//상품 리스트 조회
		List<Product> products = queryFactory
			.selectFrom(product)
			.where(
				getTitleLike(product.productName, keyword)
					.and(product.isDeleted.eq(false))
			)
			.orderBy(getOrderSpecifier(sortBy))
			.offset(pageable.getOffset())
			.limit(pageSize)
			.fetch();

		//총 상품 개수 조회 (전체 페이지 개수를 계산하기 위해)
		long totalCount = queryFactory
			.selectFrom(product)
			.where(
				getTitleLike(product.productName, keyword)
					.and(product.isDeleted.eq(false))
			)
			.fetchCount();

		//List<Product>와 totalCount를 기반으로 Page<Product> 생성
		return new PageImpl<>(products, pageable, totalCount);
	}
	
	//허브 담당자 상품 조회
	@Override
	public Page<Product> getProductSearchForHub(String keyword, Pageable pageable, SortBy sortBy, UUID hubId) {
		int pageSize = validatePageSize(pageable.getPageSize());

		//허브 ID에 맞는 상품만 조회
		List<Product> products = queryFactory
			.selectFrom(product)
			.where(
				getTitleLike(product.productName, keyword)
					.and(product.isDeleted.eq(false))
					.and(product.hubId.eq(hubId))
			)
			.orderBy(getOrderSpecifier(sortBy))
			.offset(pageable.getOffset())
			.limit(pageSize)
			.fetch();

		// 총 상품 개수 조회
		long totalCount = queryFactory
			.selectFrom(product)
			.where(
				getTitleLike(product.productName, keyword)
					.and(product.isDeleted.eq(false))
					.and(product.hubId.eq(hubId))
			)
			.fetchCount();

		return new PageImpl<>(products, pageable, totalCount);
	}

	@Override
	public Optional<Product> findByProductId(UUID productId) {
		return Optional.ofNullable(
			queryFactory
				.selectFrom(product)
				.where(
					product.productId.eq(productId),
					product.isDeleted.eq(false)
				)
				.fetchOne()
		);
	}

	//페이지 사이즈 검증
	private int validatePageSize(int requestedSize) {
		return ALLOWED_PAGE_SIZES.contains(requestedSize) ? requestedSize : DEFAULT_SIZE;
	}

	//정렬 조건
	private OrderSpecifier<?> getOrderSpecifier(SortBy sortBy) {
		switch (sortBy) {
			case CREATED:
				return product.createdAt.desc();  //생성일 기준 내림차순
			case MODIFIED:
				return product.modifiedAt.desc();  //수정일 기준 내림차순
			default:
				return product.createdAt.desc();  //기본값
		}
	}

	//한국어/영어 혼합 검색
	// BooleanBuilder
	private BooleanExpression getTitleLike(StringPath path, String keyword) {

		String trimmedKeyword = keyword.trim();

		//영어 문자가 포함되어 있는지 확인 (정규식 사용)
		boolean containsEnglish = trimmedKeyword.matches(".*[a-zA-Z]+.*");

		if (containsEnglish) {
			//영어가 포함된 경우 대소문자 구분 없이 검색
			return path.toLowerCase().contains(trimmedKeyword.toLowerCase());
		} else {
			//한국어만 있는 경우
			return path.like("%" + trimmedKeyword + "%");
		}
	}
}
