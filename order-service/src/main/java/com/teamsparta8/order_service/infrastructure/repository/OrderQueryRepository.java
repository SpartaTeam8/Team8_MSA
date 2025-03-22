package com.teamsparta8.order_service.infrastructure.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamsparta8.order_service.domain.model.Order;
import com.teamsparta8.order_service.domain.model.QOrder;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
	private final JPAQueryFactory queryFactory;
	public QueryResults<Order> findAllOrders(int page, int size) {
		QOrder order = QOrder.order;

		return queryFactory.selectFrom(order)
			.offset((long) page * size)
			.limit(size)
			.fetchResults();
	}

	public QueryResults<Order> searchOrders(UUID userId, UUID hubId, UUID deliveryId, String status, int page, int size) {
		QOrder order = QOrder.order;

		BooleanBuilder builder = new BooleanBuilder();
		if (userId != null) {
			builder.and(order.receiverCompanyId.eq(userId));
		}
		if (hubId != null) {
			builder.and(order.hubId.eq(hubId));
		}
		if (deliveryId != null) {
			builder.and(order.deliveryId.eq(deliveryId));
		}

		return queryFactory.selectFrom(order)
			.where(builder)
			.offset(page * size)
			.limit(size)
			.fetchResults();
	}


}
