package com.jdc.payroll.master.input;

import java.util.List;

import com.jdc.payroll.domain.master.entity.Deduction;
import com.jdc.payroll.domain.master.entity.DeductionForTier;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeductionFormForTier extends DeductionForm{

	@NotEmpty(message = "Please enter calculation items.")
	private List<@Valid TierItemForm> items;

	@Override
	public Deduction getEntity() {
		var entity = new DeductionForTier();
		entity.setName(getName());
		entity.setType(getType());
		entity.setDescription(getDescription());
		entity.setItems(items.stream().map(TierItemForm::getEmbeddable).toList());
		return entity;
	}

}
