package com.teamsparta8.company.application.service;

//import com.teamsparta8.company.application.client.HubServiceClient;
import com.teamsparta8.company.application.dto.CompanyRequestDto;
import com.teamsparta8.company.application.dto.CompanyResponseDto;
import com.teamsparta8.company.domain.entity.Company;
import com.teamsparta8.company.domain.model.CompanyType;
import com.teamsparta8.company.domain.repository.CompanyRepository;
//import com.teamsparta8.hub.application.dto.HubResponseInternalDto;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

  private final CompanyRepository companyRepository;
  //private final HubResponseInternalDto hubServiceClient // 허브 검증용

  public CompanyResponseDto createCompany(CompanyRequestDto requestDto) {
    // ✅ HubServiceClient 호출 부분 주석 처리 (Hub API 요청 안 함)
  /*  try {
      hubServiceClient.getHubById(requestDto.getHubId());
    } catch (Exception e) {
      throw new IllegalArgumentException("해당 허브가 존재하지 않습니다: " + requestDto.getHubId());
    }*/

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

  @Transactional(readOnly = true)
  public Page<CompanyResponseDto> searchCompanies(String name, CompanyType type, UUID hubId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

    Specification<Company> spec = (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (name != null && !name.isBlank()) {
        predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
      }

      if (type != null) {
        predicates.add(criteriaBuilder.equal(root.get("type"), type));
      }

      // hubId 관련된 부분 주석 처리- 테스트를 위함

      /*if (hubId != null) {
        predicates.add(criteriaBuilder.equal(root.get("hubId"), hubId));
      }*/

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };

    Page<Company> companies = companyRepository.findAll(spec, pageable);
    return companies.map(CompanyResponseDto::new);
  }
}
