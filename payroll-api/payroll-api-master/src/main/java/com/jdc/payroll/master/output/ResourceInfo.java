package com.jdc.payroll.master.output;

import com.jdc.payroll.domain.master.entity.Resource;
import com.jdc.payroll.domain.master.entity.Resource.Type;

public record ResourceInfo(
		int id,
		String name,
		Type type,
		String urls,
		String description
		) {

	public static ResourceInfo from(Resource entity) {
		return new ResourceInfo(
				entity.getId(), 
				entity.getName(), 
				entity.getType(), 
				entity.getBaseUrls(), 
				entity.getDescription());
	}
}
