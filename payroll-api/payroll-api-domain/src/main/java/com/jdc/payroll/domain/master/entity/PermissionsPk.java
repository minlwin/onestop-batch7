package com.jdc.payroll.domain.master.entity;

import java.io.Serializable;

import com.jdc.payroll.domain.master.entity.PositionPk.PositionCode;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PermissionsPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "position_code")
	private PositionCode positionCode;
	
	@Column(name = "department_code")
	private String departmentCode;

	@Column(name = "resource_id")
	private int resourceId;
	
	public String getId() {
		return "%s-%s-%03d".formatted(departmentCode, positionCode, resourceId);
	}
}
