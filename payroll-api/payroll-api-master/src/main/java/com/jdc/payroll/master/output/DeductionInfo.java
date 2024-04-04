package com.jdc.payroll.master.output;

import com.jdc.payroll.domain.master.CalculationType;
import com.jdc.payroll.domain.master.entity.Deduction;

import lombok.Data;

@Data
public class DeductionInfo {

	private int id;
	private String name;
	private CalculationType type;
	private String description;
	
	
	public static DeductionInfo from(Deduction entity) {
		return switch(entity.getType()) {
		case Fix -> DeductionInfoForFix.from(entity);
		case Percent -> DeductionInfoForPercent.from(entity);
		case Tier -> DeductionInfoForTier.from(entity);
		};
	}

}
