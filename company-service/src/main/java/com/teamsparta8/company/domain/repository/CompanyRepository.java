package com.teamsparta8.company.domain.repository;

import com.teamsparta8.company.domain.entity.Company;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID>,
    JpaSpecificationExecutor<Company> {
  boolean existsByName(String name);
  boolean existsByContactEmail(String contactEmail);
}
