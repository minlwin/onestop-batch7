package com.jdc.payroll.master.output;

import java.util.List;

import com.jdc.payroll.domain.master.entity.Deduction;
import com.jdc.payroll.domain.master.entity.DeductionForTier;
import com.jdc.payroll.master.input.TierItemForm;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeductionInfoForTier extends DeductionInfo{

	private List<TierItemForm> items;

	public static DeductionInfo from(Deduction entity) {
		
		if(entity instanceof DeductionForTier dto) {
			var info = new DeductionInfoForTier();
			info.setId(dto.getId());
			info.setName(dto.getName());
			info.setDescription(dto.getDescription());
			info.setType(dto.getType());
			info.setItems(dto.getItems().stream().map(TierItemForm::from).toList());
			return info;
		}
		
		return null;
	}
}
