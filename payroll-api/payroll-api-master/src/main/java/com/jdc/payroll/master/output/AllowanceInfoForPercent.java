package com.jdc.payroll.master.output;

import java.math.BigDecimal;

import com.jdc.payroll.domain.master.CalculationTarget;
import com.jdc.payroll.domain.master.entity.Allowance;
import com.jdc.payroll.domain.master.entity.AllowanceForPercent;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AllowanceInfoForPercent extends AllowanceInfo{

	private BigDecimal amount;
	private CalculationTarget target;

	public static AllowanceInfo from(Allowance entity) {
		
		if(entity instanceof AllowanceForPercent dto) {
			var info = new AllowanceInfoForPercent();
			info.setId(dto.getId());
			info.setName(dto.getName());
			info.setDescription(dto.getDescription());
			info.setType(dto.getType());
			info.setAmount(dto.getAmount());
			info.setTarget(dto.getTarget());
			return info;
		}
		
		return null;
	}

}
