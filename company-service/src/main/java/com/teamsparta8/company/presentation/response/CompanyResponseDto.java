package com.teamsparta8.company.presentation.response;

import com.teamsparta8.company.domain.entity.Company;
import com.teamsparta8.company.domain.model.CompanyType;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponseDto {
  private Long id;
  private String name;
  private CompanyType type;
  private UUID hubId;
  private String address;
  private String contactName;
  private String contactEmail;
  private String contactPhone;
  private String businessRegistrationNumber;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

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
  }
}
