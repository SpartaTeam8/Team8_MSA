package com.teamsparta8.company.domain.entity;


import com.teamsparta8.company.domain.model.CompanyType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.hc.core5.reactor.IOSession.Status;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "companies")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SQLDelete(sql = "UPDATE company SET deleted_at = NOW() WHERE id = ?")  // 논리 삭제
@Where(clause = "deleted_at IS NULL")  // 활성 데이터만 조회
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100, unique = true)
  private String name; // 업체명

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CompanyType type; // 업체 유형 ('PRODUCER', 'CONSUMER')

  @Column(nullable = false)
  private Long hubId; // 허브 ID (허브 서비스와 연동)

  @Column(nullable = false)
  private String address; // 주소

  @Column(nullable = false, length = 100)
  private String contactName; // 담당자 이름

  @Column(nullable = false, length = 100, unique = true)
  private String contactEmail; // 담당자 이메일

  @Column(nullable = false, length = 20)
  private String contactPhone; // 담당자 전화번호

  @Column(nullable = false, length = 20, unique = true)
  private String businessRegistrationNumber; // 사업자 등록 번호

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdAt; // 생성일

  @Column(nullable = false)
  @LastModifiedDate
  private LocalDateTime updatedAt; // 수정일

  private LocalDateTime deletedAt; // 논리 삭제일

  private Long deletedBy; // 삭제한 사람의 ID

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status = Status.ACTIVE; // 활성화 상태 ('active', 'inactive')

  @Builder
  public Company(String name, CompanyType type, String address, String contactName,
      String contactEmail, String contactPhone, String businessRegistrationNumber) {
    this.name = name;
    this.type = type;
    this.address = address;
    this.contactName = contactName;
    this.contactEmail = contactEmail;
    this.contactPhone = contactPhone;
    this.businessRegistrationNumber = businessRegistrationNumber;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
    this.status = Status.ACTIVE;
  }

  public void updateCompany(String name, String address, String contactName,
      String contactEmail, String contactPhone, CompanyType type) {
    this.name = name;
    this.address = address;
    this.contactName = contactName;
    this.contactEmail = contactEmail;
    this.contactPhone = contactPhone;
    this.type = type;
    this.updatedAt = LocalDateTime.now();
  }

  public void deactivate(Long userId) {
    this.status = Status.INACTIVE;
    this.deletedAt = LocalDateTime.now();
    this.deletedBy = userId;
  }
}
