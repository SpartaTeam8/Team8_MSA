package com.teamsparta8.company.domain.repository;

import com.teamsparta8.company.domain.entity.Company;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID>,
    JpaSpecificationExecutor<Company> {
  boolean existsByName(String name);
  boolean existsByContactEmail(String contactEmail);

  // 중복된 회사명 확인 (deletedAt이 null인 데이터만)
  boolean existsByNameAndDeletedAtIsNull(String name);

  // 중복된 이메일 확인 (deletedAt이 null인 데이터만)
  boolean existsByContactEmailAndDeletedAtIsNull(String contactEmail);

  // deletedAt이 null인 회사들만 조회
  @Query("SELECT c FROM Company c WHERE c.deletedAt IS NULL")
  List<Company> findAllActiveCompanies();
}
