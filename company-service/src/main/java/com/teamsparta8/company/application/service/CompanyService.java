package com.teamsparta8.company.application.service;

import com.teamsparta8.company.application.dto.CompanyRequestDto;
import com.teamsparta8.company.application.dto.CompanyResponseDto;
import com.teamsparta8.company.domain.entity.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

  private final CompanyRepository companyRepository;
  private final HubServiceClient hubServiceClient; // 허브 검증용

  @Transactional
  public CompanyResponseDto createCompany(CompanyRequestDto requestDto) {
    // 허브 존재 여부 검증
    boolean hubExists = hubServiceClient.checkHubExists(requestDto.getHubId());
    if (!hubExists) {
      throw new IllegalArgumentException("해당 허브가 존재하지 않습니다: " + requestDto.getHubId());
    }

    // 중복 검사 (이름과 이메일)
    if (companyRepository.existsByName(requestDto.getName())) {
      throw new IllegalArgumentException("이미 존재하는 회사명입니다.");
    }

    if (companyRepository.existsByContactEmail(requestDto.getContactEmail())) {
      throw new IllegalArgumentException("이미 등록된 이메일입니다.");
    }

    // Company 엔티티 생성 및 저장
    Company company = new Company(requestDto);
    companyRepository.save(company);

    return new CompanyResponseDto(company);
  }
}
