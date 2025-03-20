package com.teamsparta8.company.application.dto;

import com.teamsparta8.company.domain.entity.Company;
import com.teamsparta8.company.domain.model.CompanyType;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

@Getter
public class CompanyResponseDto {
  private final Long id;
  private final String name;
  private final CompanyType type;
  private final UUID hubId;
  private final String address;
  private final String contactName;
  private final String contactEmail;
  private final String contactPhone;
  private final String businessRegistrationNumber;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;
  private final Boolean isActive; // 활성 상태

  // Company 엔티티를 받는 생성자
  public CompanyResponseDto(Company company) {
    this.id = company.getId();
    this.name = company.getName();
    this.type = company.getType();
    this.hubId = company.getHubId();
    this.address = company.getAddress();
    this.contactName = company.getContactName();
    this.contactEmail = company.getContactEmail();
    this.contactPhone = company.getContactPhone();
    this.businessRegistrationNumber = company.getBusinessRegistrationNumber();
    this.createdAt = company.getCreatedAt();
    this.updatedAt = company.getUpdatedAt();
    this.isActive = (company.getDeletedAt() == null); // 삭제되지 않았으면 활성 상태
  }
}