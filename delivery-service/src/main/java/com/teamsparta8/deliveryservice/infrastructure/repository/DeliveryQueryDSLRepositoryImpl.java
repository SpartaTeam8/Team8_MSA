package com.teamsparta8.deliveryservice.infrastructure.repository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamsparta8.deliveryservice.domain.model.Delivery;
import com.teamsparta8.deliveryservice.domain.model.QDelivery;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeliveryQueryDSLRepositoryImpl {

	private final JPAQueryFactory queryFactory;

	public Page<Delivery> searchByDepartureHubId(UUID departureHubId, Pageable pageable){
		QDelivery delivery = QDelivery.delivery;

		List<Delivery> content = queryFactory
			.selectFrom(delivery)
			.where(delivery.departureHubId.eq(departureHubId))  // departureHubId 조건 추가
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.orderBy(pageable.getSort().stream()
				.map(sort -> {
					if(sort.getProperty().equals("createdAt")) {
						return sort.isAscending() ? delivery.createdAt.asc() : delivery.createdAt.desc();
					} else if(sort.getProperty().equals("lastModifiedAt")) {
						return sort.isAscending() ? delivery.lastModifiedAt.asc() : delivery.lastModifiedAt.desc();
					}
					return null;
				})
				.filter(Objects::nonNull)
				.toArray(OrderSpecifier[]::new)
			)
			.fetch();  // 결과 리스트를 가져옵니다.

		Long total = queryFactory
			.select(delivery.count())
			.where(delivery.departureHubId.eq(departureHubId))  // 동일한 조건으로 총 개수를 구함
			.fetchOne();

		total = (total == null) ? 0 : total;

		return new PageImpl<>(content, pageable, total);  // PageImpl로 감싸서 반환
	}
}
