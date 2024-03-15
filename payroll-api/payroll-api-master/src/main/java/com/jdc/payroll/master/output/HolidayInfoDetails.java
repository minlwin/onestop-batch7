package com.jdc.payroll.master.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.payroll.domain.master.entity.Holiday;
import com.jdc.payroll.domain.master.entity.Holiday.Type;

public record HolidayInfoDetails(
		LocalDate date,
		Type type,
		String holiday,
		String remark,
		boolean deleted,
		String createdBy,
		LocalDateTime createdAt,
		String updateBy,
		LocalDateTime updatedAt) {

	public static HolidayInfoDetails from(Holiday entity) {
		return new HolidayInfoDetails(entity.getDate(), 
				entity.getType(), 
				entity.getHoliday(), 
				entity.getRemark(), 
				entity.isDeleted(), 
				entity.getCreateBy(), 
				entity.getCreateAt(), 
				entity.getUpdateBy(), 
				entity.getUpdateAt());
	}

}
