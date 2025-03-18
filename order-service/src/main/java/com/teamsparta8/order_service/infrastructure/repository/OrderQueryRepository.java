package com.teamsparta8.order_service.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teamsparta8.order_service.domain.model.Order;
import com.teamsparta8.order_service.domain.model.QOrder;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {
	private final JPAQueryFactory queryFactory;

	// [일반 사용자] 본인이 주문한 목록 조회
	public QueryResults<Order> findOrdersByUserId(UUID userId, int page, int size) {
		QOrder order = QOrder.order;

		return queryFactory.selectFrom(order)
			.where(order.receiverCompanyId.eq(userId)) //주문 수취인 기준 필터링
			.offset(page * size)
			.limit(size)
			.fetchResults();
	}

	// [허브 관리자] 허브 ID 기반 주문 조회
	public QueryResults<Order> findOrdersByHubId(UUID hubId, int page, int size) {
		QOrder order = QOrder.order;

		return queryFactory.selectFrom(order)
			.where(order.hubId.eq(hubId)) //허브 기준 필터링
			.offset(page * size)
			.limit(size)
			.fetchResults();
	}

	// [배송 담당자] 본인이 담당하는 주문 목록 조회
	public QueryResults<Order> findOrdersByDeliveryId(UUID deliveryId, int page, int size) {
		QOrder order = QOrder.order;

		return queryFactory.selectFrom(order)
			.where(order.deliveryId.eq(deliveryId)) // 배송 담당자 기준 필터링
			.offset(page * size)
			.limit(size)
			.fetchResults();
	}



}
