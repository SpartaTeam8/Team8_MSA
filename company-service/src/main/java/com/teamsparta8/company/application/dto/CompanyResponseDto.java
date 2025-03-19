package com.teamsparta8.company.application.dto;

import com.teamsparta8.company.domain.model.CompanyType;
import java.time.LocalDateTime;

public record CompanyResponseDto(
    Long id, // 업체 고유 ID
    String name, // 업체명
    CompanyType type, // 업체 타입 (PRODUCER 또는 CONSUMER)
    Long hubId, // 허브 ID
    String address, // 업체 주소
    String contactName, // 담당자 이름
    String contactEmail, // 담당자 이메일
    String contactPhone, // 담당자 연락처
    String businessRegistrationNumber, // 사업자 등록 번호
    LocalDateTime createdAt, // 생성일
    LocalDateTime updatedAt, // 수정일
    Boolean isActive // 활성 상태 (논리 삭제 여부)
) {}
