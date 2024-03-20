package com.jdc.payroll.domain.master.entity;

import java.io.Serializable;

import jakarta.persistence.Column;

public class PermissionsPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "position_code")
	private String positionCode;
	
	@Column(name = "department_code")
	private String departmentCode;

	@Column(name = "resource_id")
	private int resourceId;
}
