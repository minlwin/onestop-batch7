package com.jdc.payroll.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public abstract class AbstractEntity {

	@Column(columnDefinition = "boolean default false")
	private boolean deleted;
	
	@CreatedDate
	private LocalDateTime createAt;
	@CreatedBy
	private String createBy;
	@LastModifiedDate
	private LocalDateTime updateAt;
	@LastModifiedBy
	private String updateBy;

}
