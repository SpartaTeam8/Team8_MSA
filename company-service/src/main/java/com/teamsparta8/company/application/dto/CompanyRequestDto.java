package com.teamsparta8.company.application.dto;

import com.teamsparta8.company.domain.model.CompanyType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CompanyRequestDto(
    @NotBlank @Size(max = 100)
    String name, // 업체명 (100자 제한, 필수 입력)

    @NotNull
    CompanyType type, // 업체 타입 (PRODUCER 또는 CONSUMER)

    @NotNull
    Long hubId, // 허브 ID (연관된 허브)

    @NotBlank
    String address, // 업체 주소

    @NotBlank @Size(max = 100)
    String contactName, // 담당자 이름

    @NotBlank @Email @Size(max = 100)
    String contactEmail, // 담당자 이메일 (이메일 형식 검증 포함)

    @NotBlank @Size(max = 20)
    String contactPhone, // 담당자 연락처

    @NotBlank @Size(max = 20)
    String businessRegistrationNumber // 사업자 등록 번호 (고유값)
) {}