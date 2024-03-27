package com.jdc.payroll.master.service.events;

import static com.jdc.payroll.utils.helpers.EntityOperationHelper.getOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.entity.Permissions;
import com.jdc.payroll.domain.master.entity.PermissionsPk;
import com.jdc.payroll.domain.master.entity.PositionPk;
import com.jdc.payroll.domain.master.repo.PermissionRepo;
import com.jdc.payroll.domain.master.repo.PositionRepo;
import com.jdc.payroll.domain.master.repo.ResourceRepo;

@Service
public class PositionCreationEventListener {
	
	private static final String DOMAIN_NAME = "Position";
	
	@Autowired
	private PositionRepo positionRepo;
	@Autowired
	private ResourceRepo resourceRepo;
	@Autowired
	private PermissionRepo permissionRepo;

	@Transactional
	@EventListener
	public void createPermission(PositionCreationEvent event) {

		var position = getOne(positionRepo.findById(PositionPk.parse(event.code())), DOMAIN_NAME, event.code());
		var resources = resourceRepo.findAll();
		
		for(var resource : resources) {
			var permission = new Permissions();
			var permissionPk = new PermissionsPk();
			
			permissionPk.setDepartmentCode(position.getId().getDepartmentCode());
			permissionPk.setPositionCode(position.getId().getPositionCode());
			permissionPk.setResourceId(resource.getId());
			
			permission.setId(permissionPk);
			
			permission.setPosition(position);
			permission.setResource(resource);
			
			if(resource.getName().equals("Personal Data")) {
				permission.setRead(true);
				permission.setWrite(true);
			}
			
			permissionRepo.save(permission);
		}
	}
}
