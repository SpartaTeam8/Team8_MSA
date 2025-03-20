package com.teamsparta8.company.application.dto;

import com.teamsparta8.company.domain.model.CompanyType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회사 등록 요청 DTO
 */
@Getter
@NoArgsConstructor
public class CompanyRequestDto {

    @NotBlank
    @Size(max = 100)
    private String name; // 업체명

    @NotNull
    private CompanyType type; // 업체 유형 ('PRODUCER', 'CONSUMER')

    @NotNull
    private UUID hubId; // 허브 ID (허브 서비스와 연동)

    @NotBlank
    private String address; // 주소

    @NotBlank
    @Size(max = 100)
    private String contactName; // 담당자 이름

    @NotBlank
    @Email
    @Size(max = 100)
    private String contactEmail; // 담당자 이메일

    @NotBlank
    @Size(max = 20)
    private String contactPhone; // 담당자 전화번호

    @NotBlank
    @Size(max = 20)
    private String businessRegistrationNumber; // 사업자 등록 번호
}