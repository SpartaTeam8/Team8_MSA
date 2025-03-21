package com.teamsparta8.company.presentation.controller;

import com.teamsparta8.company.application.dto.CompanyRequestDto;
import com.teamsparta8.company.application.dto.CompanyResponseDto;
import com.teamsparta8.company.application.service.CompanyService;
import com.teamsparta8.company.domain.model.CompanyType;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Company API", description = "회사 관리 API")
@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
  private final CompanyService companyService;

  @PostMapping
  public ResponseEntity<CompanyResponseDto> createCompany(@Valid @RequestBody CompanyRequestDto requestDto) {
    CompanyResponseDto responseDto = companyService.createCompany(requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }

  @GetMapping
  public ResponseEntity<Page<CompanyResponseDto>> searchCompanies(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String type, // String으로 받아서 처리
      @RequestParam(required = false) String hubId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {

    // String으로 받은 type을 CompanyType으로 변환 (잘못된 값은 예외 처리)
    CompanyType companyType = null;
    if (type != null && !type.isBlank()) {
      try {
        companyType = CompanyType.valueOf(type.toUpperCase()); // Enum으로 변환
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Invalid company type: " + type);
      }
    }

    // hubId가 존재하면 UUID로 변환
    UUID convertedHubId = (hubId != null && !hubId.isBlank()) ? UUID.fromString(hubId) : null;

    // 서비스 호출 시 변환된 companyType을 전달
    Page<CompanyResponseDto> companies = companyService.searchCompanies(name, companyType,
        convertedHubId, page, size);
    return ResponseEntity.ok(companies);
  }
}


