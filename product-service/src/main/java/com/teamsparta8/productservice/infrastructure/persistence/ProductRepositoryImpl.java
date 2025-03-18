package com.teamsparta8.productservice.infrastructure.persistence;

import static com.teamsparta8.productservice.domain.entity.QProduct.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamsparta8.productservice.domain.entity.Product;
import com.teamsparta8.productservice.domain.entity.SortBy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements CustomProductRepository {

	private final JPAQueryFactory queryFactory;

	private static final int DEFAULT_SIZE = 10;
	private static final List<Integer> ALLOWED_PAGE_SIZES = Arrays.asList(10, 30, 50);

	// @Override
	// public List<Product> findAllByStoreId(UUID hubId, Pageable pageable, SortBy sortBy) {
	// 	return getProductQuery(hubId, null, pageable, sortBy);
	// }


	//공통 쿼리 메서드
	private List<Product> getProductQuery(UUID hubId, String keyword, Pageable pageable, SortBy sortBy) {

		int pageSize = validatePageSize(pageable.getPageSize());

		return queryFactory
			.selectFrom(product)
			.where(
				product.hubId.eq(hubId)
					.and(getTitleLike(product.productName, keyword))
					.and(product.isDeleted.eq(false))
			)
			.orderBy(getOrderSpecifier(sortBy))
			.offset(pageable.getOffset())
			.limit(pageSize)
			.fetch();
	}

	//페이지 사이즈 검증
	private int validatePageSize(int requestedSize) {
		return ALLOWED_PAGE_SIZES.contains(requestedSize) ? requestedSize : DEFAULT_SIZE;
	}

	//정렬 조건
	private OrderSpecifier<?> getOrderSpecifier(SortBy sortBy) {
		return sortBy == SortBy.MODIFIED ?
			product.modifiedAt.desc() :
			product.createdAt.desc();
	}

	//한국어/영어 혼합 검색
	private BooleanExpression getTitleLike(StringPath path, String keyword) {
		if (keyword == null || keyword.isEmpty()) {
			return null;
		}

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
