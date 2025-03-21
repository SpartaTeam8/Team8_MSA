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


  @Transactional
  public CompanyResponseDto updateCompany(UUID id, CompanyRequestDto requestDto) {
    // ID를 이용해 회사 조회
    Company company = companyRepository.findById(id).orElseThrow(() ->
        new IllegalArgumentException("회사 정보가 존재하지 않습니다. ID: " + id));

    // Company 객체의 updateCompany 메서드 사용하여 회사 정보 수정
    company.updateCompany(
        requestDto.getName(),
        requestDto.getAddress(),
        requestDto.getContactName(),
        requestDto.getContactEmail(),
        requestDto.getContactPhone(),
        requestDto.getType()
    );


    // 회사 저장 (자동으로 업데이트됨)
    companyRepository.save(company);

    // 수정된 회사 정보 반환
    return new CompanyResponseDto(company);
  }

  @Transactional
  public void deleteCompany(UUID id, Long userId) {
    // 회사 정보 조회
    Company company = companyRepository.findById(id).orElseThrow(() ->
        new IllegalArgumentException("회사가 존재하지 않습니다. ID: " + id));

    // 회사 삭제 (deletedAt에 현재 시간 기록, deletedBy에 삭제한 사람 ID 기록)
    company.deactivate(userId);

    // 회사 삭제된 정보 저장
    companyRepository.save(company);

    // 연관된 다른 데이터들 비활성화 (필요한 경우에 따라 구현)
    // 예: 연관된 부서, 제품, 직원 등
    // 이 경우에는 다른 엔티티들이 Company 엔티티와 연관된 상태에서 삭제되거나 비활성화되도록 처리
    // 예시: employeeRepository.deactivateEmployeesByCompanyId(id);
  }
}
