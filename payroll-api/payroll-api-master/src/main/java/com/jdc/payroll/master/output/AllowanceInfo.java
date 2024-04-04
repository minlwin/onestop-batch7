package com.jdc.payroll.master.output;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.jdc.payroll.domain.master.CalculationType;
import com.jdc.payroll.domain.master.entity.Allowance;

import lombok.Data;

@Data
@JsonTypeInfo(
	use = Id.NAME, include = As.EXISTING_PROPERTY, property = "type", visible = true
)
@JsonSubTypes({
	@JsonSubTypes.Type(value = AllowanceInfoForFix.class, name = "Fix"),
	@JsonSubTypes.Type(value = AllowanceInfoForPercent.class, name = "Percent"),
	@JsonSubTypes.Type(value = AllowanceInfoForTeir.class, name = "Tier"),	
})
public abstract class AllowanceInfo {

	private int id;
	private String name;
	private CalculationType type;
	private String description;
	
	
	public static AllowanceInfo from(Allowance entity) {
		return switch(entity.getType()) {
		case Fix -> AllowanceInfoForFix.from(entity);
		case Percent -> AllowanceInfoForPercent.from(entity);
		case Tier -> AllowanceInfoForTeir.from(entity);
		};
	}
}
