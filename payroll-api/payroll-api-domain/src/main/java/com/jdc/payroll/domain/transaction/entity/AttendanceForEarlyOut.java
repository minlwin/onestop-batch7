package com.jdc.payroll.domain.transaction.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class AttendanceForEarlyOut extends Attendance{

	public AttendanceForEarlyOut() {
		setStatus(Status.EarlyOut);
	}
}
