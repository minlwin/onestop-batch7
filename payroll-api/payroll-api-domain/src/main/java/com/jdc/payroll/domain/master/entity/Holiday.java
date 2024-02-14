package com.jdc.payroll.domain.master.entity;

import java.time.LocalDate;

import com.jdc.payroll.domain.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Holiday extends AbstractEntity {
	
	@Id
	@Column(name = "holiday_date")
	private LocalDate date;
	
	@Column(nullable = false)
	private Type type;
	
	@Column(nullable = false)
	private String holiday;
	
	private String remark;
	
	public enum Type {
		Weekend, Gazetted, Special;
		
		public String getDesplayName() {
			return "%s Holiday".formatted(name());
		}
	}
}
