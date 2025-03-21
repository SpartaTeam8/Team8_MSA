package com.teamsparta8.order_service.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.QueryResults;
import com.teamsparta8.order_service.infrastructure.client.DeliveryClient;
import com.teamsparta8.order_service.infrastructure.client.StockClient;
import com.teamsparta8.order_service.infrastructure.repository.OrderQueryRepository;
import com.teamsparta8.order_service.infrastructure.util.PaginationUtil;
import com.teamsparta8.order_service.presentatin.dto.CommonResponse;
import com.teamsparta8.order_service.presentatin.dto.CreateDeliveryRequest;
import com.teamsparta8.order_service.presentatin.dto.CreateDeliveryResponse;
import com.teamsparta8.order_service.presentatin.dto.CreateOrderRequest;
import com.teamsparta8.order_service.presentatin.dto.CreateOrderResponse;
import com.teamsparta8.order_service.application.dto.OrderMapper;
import com.teamsparta8.order_service.domain.model.Order;
import com.teamsparta8.order_service.domain.repository.OrderRepository;
import com.teamsparta8.order_service.domain.service.OrderDomainService;
import com.teamsparta8.order_service.presentatin.dto.DecreaseStockRequest;
import com.teamsparta8.order_service.presentatin.dto.OrderPageResponse;
import com.teamsparta8.order_service.presentatin.dto.Pagination;
import com.teamsparta8.order_service.presentatin.dto.RollbackStockRequest;
import com.teamsparta8.order_service.presentatin.dto.StockCheckResponse;
import com.teamsparta8.order_service.presentatin.dto.UpdateOrderRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
	private final OrderDomainService orderDomainService;
	private final OrderMapper orderMapper;
	private final OrderQueryRepository orderQueryRepository;
	private final StockClient stockClient;
	private final DeliveryClient deliveryClient;
	@Transactional
	public CreateOrderResponse createOrder(CreateOrderRequest request) {
		StockCheckResponse stockResponse = null;
		try {
			// 1. Stock Service를 통한 재고 확인 및 차감
			DecreaseStockRequest stockRequest = new DecreaseStockRequest(request.getProductId(), request.getQuantity());
			// StockCheckResponse stockResponse;
			try {
				CommonResponse<StockCheckResponse> response = stockClient.checkAndDecreaseStock(stockRequest);
				stockResponse = response.getData();
			} catch (Exception e) {
				throw new RuntimeException("재고 확인 및 차감 실패: " + e.getMessage());
			}

			int totalPrice = stockResponse.getPrice() * request.getQuantity();

			// 2. 도메인 서비스 호출하여 Order 생성
			Order order = orderDomainService.createOrder(
				request.getSupplierCompanyId(),
				request.getReceiverCompanyId(),
				request.getProductId(),
				stockResponse.getHubId(),
				null,
				request.getQuantity(),
				totalPrice,
				request.getRequestDescription()
			);

			//3. 주문 저장 (deliveryId 없이 우선 저장)
			orderDomainService.saveOrder(order);

			// // 4. Delivery 서비스 호출(테스트 중이라 주석처리)
			// CreateDeliveryRequest deliveryRequest = new CreateDeliveryRequest(order.getOrderId());
			// CreateDeliveryResponse deliveryResponse = deliveryClient.createDelivery(deliveryRequest);

			// // 5. 받은 deliveryId로 주문 정보 업데이트
			// orderDomainService.updateDeliveryId(order, deliveryResponse.getDeliveryId());

			UUID dummyDeliveryId = UUID.randomUUID();
			orderDomainService.updateDeliveryId(order, dummyDeliveryId);

			// 6. 응답 변환 및 반환
			return orderMapper.fromEntity(order);

		} catch (Exception e) {
			log.warn("주문 생성 도중 예외 발생 → 보상 트랜잭션 조건 확인 중: {}", e.getMessage());
			// 7. 예외 발생 시 → 보상 트랜잭션 수행, rollback은 재고 차감까지 된 경우에만!!
			// rollbackStock(request.getProductId(), request.getQuantity());
			if (stockResponse != null) {
				log.warn("배송 생성 실패로 보상 트랜잭션 수행 시작 (rollbackStock 호출)");

				try {
					rollbackStock(request.getProductId(), request.getQuantity());
					log.info("보상 트랜잭션 성공: 재고 복구 완료");
				} catch (Exception rollbackEx) {
					log.error("보상 트랜잭션 실패: 재고 복구 실패", rollbackEx);
				}
			}
			throw new RuntimeException("주문 생성 실패 -> 보상 트랜잭션 수행!: " + e.getMessage());
		}
	}

	private void rollbackStock(UUID productId, int quantity) {
		try {
			stockClient.rollbackStock(new RollbackStockRequest(productId, quantity));
		} catch (Exception ex) {
			log.error("보상 트랜잭션 실패: 재고 복구 실패", ex);
		}
	}

	@Transactional
	public CreateOrderResponse updateOrder(UUID orderId, UpdateOrderRequest request) {
		Order existingOrder = orderDomainService.getOrderById(orderId);
		int originalQuantity = existingOrder.getQuantity();
		int newQuantity = request.getQuantity();

		// 1. 기존 주문 수량 복구
		try {
			rollbackStock(existingOrder.getProductId(), originalQuantity);
		} catch (Exception ex) {
			log.error("기존 재고 복구 실패", ex);
			throw new RuntimeException("기존 재고 복구 실패: " + ex.getMessage());
		}

		// 2. 새 수량만큼 재고 차감
		StockCheckResponse stockResponse;
		try {
			DecreaseStockRequest stockRequest = new DecreaseStockRequest(existingOrder.getProductId(), newQuantity);
			stockResponse = stockClient.checkAndDecreaseStock(stockRequest).getData();
		} catch (Exception e) {
			throw new RuntimeException("새 주문 수량 차감 실패: " + e.getMessage());
		}

		// 3. 총 가격 계산
		int totalPrice = stockResponse.getPrice() * newQuantity;

		// 4. 도메인에서 주문 업데이트 처리 (totalPrice도 넘겨줌!)
		Order updatedOrder = orderDomainService.updateOrder(
			existingOrder,
			newQuantity,
			request.getRequestDescription(),
			totalPrice
		);

		orderDomainService.saveOrder(updatedOrder);
		return orderMapper.fromEntity(updatedOrder);
	}

	//단건 조회
	@Transactional(readOnly = true)
	public CreateOrderResponse getOrderById(UUID orderId) {
		Order order = orderDomainService.getOrderById(orderId);
		return orderMapper.fromEntity(order);
	}

	@Transactional
	public void deleteOrder(UUID orderId) {
		orderDomainService.deleteOrder(orderId);
	}
	//목록 조회
	@Transactional(readOnly = true)
	public OrderPageResponse getAllOrders(int page, int size) {
		QueryResults<Order> queryResults = orderDomainService.getAllOrders(page, size);
		List<CreateOrderResponse> response = PaginationUtil.mapResults(queryResults, orderMapper::fromEntity);
		Pagination pagination = PaginationUtil.createPagination(queryResults, page, size);
		return new OrderPageResponse(response, pagination);
	}
	@Transactional(readOnly = true)
	public OrderPageResponse searchOrders(UUID userId, UUID hubId, UUID deliveryId, String status, int page, int size) {
		QueryResults<Order> queryResults = orderQueryRepository.searchOrders(userId, hubId, deliveryId, status, page, size);
		List<CreateOrderResponse> response = PaginationUtil.mapResults(queryResults, orderMapper::fromEntity);
		Pagination pagination = PaginationUtil.createPagination(queryResults, page, size);
		return new OrderPageResponse(response, pagination);
	}



}
