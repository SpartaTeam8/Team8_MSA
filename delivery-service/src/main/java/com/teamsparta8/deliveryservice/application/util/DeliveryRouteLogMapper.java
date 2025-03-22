package com.teamsparta8.deliveryservice.application.util;

import com.teamsparta8.deliveryservice.application.dto.DeliveryRouteLogResponseInternalDto;
import com.teamsparta8.deliveryservice.domain.model.DeliveryRouteLog;
import com.teamsparta8.deliveryservice.presentation.dto.DeliveryRouteLogResponseDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DeliveryRouteLogMapper {

    DeliveryRouteLogResponseInternalDto toResponse(DeliveryRouteLog deliveryRouteLog);

    default List<DeliveryRouteLogResponseInternalDto> toResponseList(List<DeliveryRouteLog> deliveryRouteLogList) {

        return deliveryRouteLogList.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
