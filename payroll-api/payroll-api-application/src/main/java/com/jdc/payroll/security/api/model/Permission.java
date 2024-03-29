package com.jdc.payroll.security.api.model;

import com.jdc.payroll.domain.master.entity.Permissions;
import com.jdc.payroll.domain.master.entity.Resource.Type;

public record Permission(
	String resource,
	Type resourceType,
	String resourceUrl,
	boolean read,
	boolean write,
	boolean modify) {

	public boolean isEnable() {
		return read || write || modify;
	}
	
	public static Permission from(Permissions entity) {
		return new Permission(
				entity.getResource().getName(), 
				entity.getResource().getType(), 
				entity.getResource().getBaseUrls(), 
				entity.isRead(), 
				entity.isWrite(), 
				entity.isModify());
	}
}
