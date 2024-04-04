package com.jdc.payroll.domain.master.entity;

import java.util.List;

import com.jdc.payroll.domain.master.CalculationType;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class DeductionForTier extends Deduction{

	public DeductionForTier() {
		setType(CalculationType.Tier);
	}
	
	@ElementCollection
	private List<TierItem> items;

}
