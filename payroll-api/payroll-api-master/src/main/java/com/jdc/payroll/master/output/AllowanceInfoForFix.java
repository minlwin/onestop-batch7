package com.jdc.payroll.master.output;

import java.math.BigDecimal;

import com.jdc.payroll.domain.master.CalculationTarget;
import com.jdc.payroll.domain.master.entity.Allowance;
import com.jdc.payroll.domain.master.entity.AllowanceForFix;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AllowanceInfoForFix extends AllowanceInfo{

	private BigDecimal amount;
	private CalculationTarget target;

	public static AllowanceInfo from(Allowance entity) {
		
		if(entity instanceof AllowanceForFix dto) {
			var info = new AllowanceInfoForFix();
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
