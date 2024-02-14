package com.jdc.payroll.domain.master.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PositionPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "position_code")
	private String positionCode;
	@Column(name = "department_code")
	private String departmentCode;
}
