package com.jdc.payroll.master.output;

import java.util.List;

import com.jdc.payroll.domain.master.entity.Allowance;
import com.jdc.payroll.domain.master.entity.AllowanceForTier;
import com.jdc.payroll.master.input.TierItemForm;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AllowanceInfoForTeir extends AllowanceInfo{

	private List<TierItemForm> items;

	public static AllowanceInfo from(Allowance entity) {
		
		if(entity instanceof AllowanceForTier dto) {
			var info = new AllowanceInfoForTeir();
			info.setId(dto.getId());
			info.setName(dto.getName());
			info.setDescription(dto.getDescription());
			info.setType(dto.getType());
			info.setItems(dto.getItems().stream().map(TierItemForm::from).toList());
			return info;
		}
		
		return null;
	}}
