package com.jdc.payroll.master.output;

import com.jdc.payroll.domain.master.entity.Permissions;
import com.jdc.payroll.domain.master.entity.PositionPk.PositionCode;

public record PermissionInfo(
		String department,
		PositionCode position,
		int resourceId,
		String resource,
		String resourceUrls,
		String description,
		boolean read,
		boolean write,
		boolean modify) {
	
	public boolean isEnable() {
		return read || write || modify;
	}

	public static PermissionInfo from(Permissions entity) {
		return new PermissionInfo(
				entity.getId().getDepartmentCode(), 
				entity.getId().getPositionCode(), 
				entity.getId().getResourceId(), 
				entity.getResource().getName(), 
				entity.getResource().getBaseUrls(), 
				entity.getResource().getDescription(),
				entity.isRead(), 
				entity.isWrite(), 
				entity.isModify());
	}
}
