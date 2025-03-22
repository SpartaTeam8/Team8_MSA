package com.teamsparta8.company.presentation.request;

import com.teamsparta8.company.domain.model.CompanyType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CompanyRequestDto {
  @NotBlank
  private String name;

  @NotNull
  private CompanyType type;

  @NotNull
  private UUID hubId;

  @NotBlank
  private String address;

  @NotBlank
  private String contactName;

  @Email
  @NotBlank
  private String contactEmail;

  @NotBlank
  private String contactPhone;

  @NotBlank
  private String businessRegistrationNumber;
}
