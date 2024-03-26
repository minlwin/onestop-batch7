package com.jdc.payroll.domain.master.entity;

import java.io.Serializable;

import com.jdc.payroll.domain.IllegalDomainDataException;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PositionPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "position_code")
	private PositionCode positionCode;
	@Column(name = "department_code")
	private String departmentCode;
	
	public enum PositionCode {
		MGR("Manager"), 
		ASM("Assistance Manager"), 
		SPV("Supervisor"), 
		ASS("Assistance Supervisor"), 
		EMP("Employee");
		
		private String value;

		private PositionCode(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	public String getCode() {
		return "%s-%s".formatted(departmentCode, positionCode.name());
	}
	
	public static PositionPk parse(String code) {
		var array = code.split("-");
		
		if(array.length != 2) {
			throw new IllegalDomainDataException("Invalid position code.");
		}
		
		var id = new PositionPk();
		id.setDepartmentCode(array[0]);
		id.setPositionCode(PositionCode.valueOf(array[1]));
		return id;
	}
}
