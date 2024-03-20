package com.jdc.payroll.domain.transaction.entity;

import java.time.LocalTime;

import com.jdc.payroll.domain.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.JOINED)
public class Attendance extends AbstractEntity {

	@EmbeddedId
	private AttendancePk id;
	
	@Column(nullable = false)
	private Status status;
	
	private LocalTime checkIn;
	private LocalTime checkOut;
	private String remark;
	
	public enum Status {
		Attend, Absent, Leave, Late, EarlyOut
	}
}
