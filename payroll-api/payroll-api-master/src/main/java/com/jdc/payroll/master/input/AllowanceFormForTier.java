package com.jdc.payroll.master.input;

import java.util.List;

import com.jdc.payroll.domain.master.entity.Allowance;
import com.jdc.payroll.domain.master.entity.AllowanceForTier;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AllowanceFormForTier extends AllowanceForm {

	@NotEmpty(message = "Please enter calculation items.")
	private List<@Valid TierItemForm> items;

	@Override
	public Allowance getEntity() {
		var entity = new AllowanceForTier();
		entity.setName(getName());
		entity.setType(getType());
		entity.setDescription(getDescription());
		entity.setItems(items.stream().map(TierItemForm::getEmbeddable).toList());
		return entity;
	}
}
