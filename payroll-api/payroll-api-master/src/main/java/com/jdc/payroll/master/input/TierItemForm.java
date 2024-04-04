package com.jdc.payroll.master.input;

import java.math.BigDecimal;

import com.jdc.payroll.domain.master.CalculationTarget;
import com.jdc.payroll.domain.master.entity.TierItem;

import jakarta.validation.constraints.NotNull;

public record TierItemForm(
		@NotNull(message = "Please enter range from value.")
		BigDecimal rangeFrom,
		BigDecimal rangeTo,
		@NotNull(message = "Please enter amount value.")
		BigDecimal amount,
		@NotNull(message = "Please select calculation target.")
		CalculationTarget target,
		@NotNull(message = "Please select fix or percent calculation.")
		Boolean percent) {

	public TierItem getEmbeddable() {
		var data = new TierItem();
		data.setAmount(amount);
		data.setTarget(target);
		data.setPercent(percent);
		data.setRangeFrom(rangeFrom);
		data.setRangeTo(rangeTo);
		return data;
	}
	
	public static TierItemForm from(TierItem item) {
		return new TierItemForm(
				item.getRangeFrom(), 
				item.getRangeTo(), 
				item.getAmount(), 
				item.getTarget(), 
				item.isPercent());
	}
}
