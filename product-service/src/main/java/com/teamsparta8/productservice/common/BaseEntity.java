package com.teamsparta8.productservice.common;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity{

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@CreatedBy
	@Column(updatable = false, length = 100)
	private String createdBy;

	@LastModifiedDate
	private LocalDateTime modifiedAt;

	@LastModifiedBy
	@Column(length = 100)
	private String modifiedBy;

	private LocalDateTime deletedAt;

	@Column(length = 100)
	private String deletedBy;

	public void setDeleted(LocalDateTime deletedAt, String deletedBy){
		this.deletedAt = deletedAt;
		this.deletedBy = deletedBy;
	}
}
