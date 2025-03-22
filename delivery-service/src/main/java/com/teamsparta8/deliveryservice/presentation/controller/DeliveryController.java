package com.teamsparta8.deliveryservice.presentation.controller;

import java.util.List;
import java.util.UUID;

import com.teamsparta8.deliveryservice.application.dto.DeliveryRouteLogResponseInternalDto;
import com.teamsparta8.deliveryservice.application.service.DeliveryRouteLogService;
import com.teamsparta8.deliveryservice.presentation.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamsparta8.deliveryservice.application.dto.CreateDeliveryInternalDto;
import com.teamsparta8.deliveryservice.application.dto.DeliveryResponseInternalDto;
import com.teamsparta8.deliveryservice.application.dto.UpdateDeliveryInternalDto;
import com.teamsparta8.deliveryservice.application.service.DeliveryService;
import com.teamsparta8.deliveryservice.presentation.util.DtoMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;

    private final DeliveryRouteLogService deliveryRouteLogService;

    @PostMapping("")
    public CommonResponse<DeliveryResponseDto> createDelivery(@RequestBody CreateDeliveryDto request) {

        CreateDeliveryInternalDto internalDto = DtoMapper.convertToCreateInternalDto(request);

        DeliveryResponseInternalDto response = deliveryService.createDelivery(internalDto);

        return CommonResponse.OK(DtoMapper.convertToResponse(response), "배송 생성 성공");
    }

    @GetMapping("/{deliveryId}")
    public CommonResponse<DeliveryResponseDto> readDelivery(@PathVariable UUID deliveryId) {

        DeliveryResponseInternalDto response = deliveryService.readDelivery(deliveryId);

        return CommonResponse.OK(DtoMapper.convertToResponse(response), "배송 조회 성공 : " + deliveryId);
    }

    @GetMapping("")
    public CommonResponse<List<DeliveryResponseDto>> searchDeliveries(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam UUID departureHubId,
            Pageable pageable) {

        Page<DeliveryResponseInternalDto> responsePage = deliveryService
                .searchDeliveriesByDepartureHubId(departureHubId, pageable);

        Pagination pagination = Pagination.builder()
                .currentElements(responsePage.getNumberOfElements())
                .currentPage(responsePage.getNumber())
                .totalElements(responsePage.getTotalElements())
                .totalPages(responsePage.getTotalPages())
                .build();

        return CommonResponse.OK(
                DtoMapper.convertToReponseList(responsePage.getContent(), DtoMapper::convertToResponse),
                "담당 허브의 배송 조회 성공",
                pagination
        );
    }

    // 배송 담당자 지정과 배송 상태 변경만 수정 가능하도록
    @PatchMapping("/{deliveryId}")
    public CommonResponse<DeliveryResponseDto> updateDelivery(
            @RequestBody UpdateDeliveryDto request,
            @PathVariable UUID deliveryId) {

        UpdateDeliveryInternalDto internalDto = DtoMapper.convertToUpdateInternalDto(request);

        DeliveryResponseInternalDto response = deliveryService.updateDelivery(deliveryId, internalDto);

        return CommonResponse.OK(DtoMapper.convertToResponse(response), "배송 수정 성공 : " + deliveryId);
    }

    @DeleteMapping("/{deliveryId}")
    public CommonResponse<DeliveryResponseDto> deleteDelivery(@PathVariable UUID deliveryId, @AuthenticationPrincipal UserDetails userDetails) {

        DeliveryResponseInternalDto response = deliveryService.deleteDelivery(deliveryId, userDetails.getUsername());

        return CommonResponse.OK(DtoMapper.convertToResponse(response), "배송 삭제 성공 : " + deliveryId);
    }


    // ------------------ RouteLog 관련 Endpoint------------------------

    @GetMapping("/{deliveryId}/routes")
    public CommonResponse<List<DeliveryRouteLogResponseDto>> getDeliveryRoutes(@PathVariable(name = "deliveryId") UUID deliveryId) {

        List<DeliveryRouteLogResponseInternalDto> responseList = deliveryRouteLogService.getDeliveryRouteLog(deliveryId);

        return CommonResponse.OK(DtoMapper.convertToReponseList(responseList, DtoMapper::convertToResponse), "배송경로기록 조회 성공 : " + deliveryId);
    }
}
