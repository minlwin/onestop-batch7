package com.jdc.payroll.master.output;

import java.math.BigDecimal;

import com.jdc.payroll.domain.master.CalculationTarget;
import com.jdc.payroll.domain.master.entity.Deduction;
import com.jdc.payroll.domain.master.entity.DeductionForFix;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeductionInfoForFix extends DeductionInfo{

	private BigDecimal amount;
	private CalculationTarget target;

	public static DeductionInfo from(Deduction entity) {
		
		if(entity instanceof DeductionForFix dto) {
			var info = new DeductionInfoForFix();
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
