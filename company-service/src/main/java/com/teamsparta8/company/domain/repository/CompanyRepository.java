package com.teamsparta8.company.domain.repository;

import com.teamsparta8.company.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
  boolean existsByName(String name);
  boolean existsByContactEmail(String contactEmail);
}
