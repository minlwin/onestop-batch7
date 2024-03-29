package com.jdc.payroll.master.service;

import static com.jdc.payroll.utils.helpers.EntityOperationHelper.getOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.entity.PermissionsPk;
import com.jdc.payroll.domain.master.entity.PositionPk;
import com.jdc.payroll.domain.master.repo.PermissionRepo;
import com.jdc.payroll.master.input.PermissionUpdateForm;
import com.jdc.payroll.utils.response.DataModificationResult;

@Service
public class PermissionService {
	
	private static final String DOMAIN = "Permission";
	
	@Autowired
	private PermissionRepo repo;

	@Transactional
	public DataModificationResult<Integer> update(PermissionUpdateForm form) {
		
		var possitionId = PositionPk.parse(form.posigionId());
		
		for(var item : form.items()) {
			var pk = new PermissionsPk();
			pk.setDepartmentCode(possitionId.getDepartmentCode());
			pk.setPositionCode(possitionId.getPositionCode());
			pk.setResourceId(item.resourceId());
			
			var permission = getOne(repo.findById(pk), DOMAIN, pk.getId());
			
			permission.setRead(item.read());
			permission.setWrite(item.write());
			permission.setModify(item.modify());
		}
		
		return new DataModificationResult<Integer>(form.items().size(), "%d permissions has been updated.".formatted(form.items().size()));
	}

}
