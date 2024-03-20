package com.jdc.payroll.domain.master.entity;

import com.jdc.payroll.domain.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Permissions extends AbstractEntity{

	@EmbeddedId
	private PermissionsPk id;
	
	@ManyToOne
	@JoinColumn(name = "position_code", referencedColumnName = "position_code", insertable = false, updatable = false)
	@JoinColumn(name = "department_code", referencedColumnName = "department_code", insertable = false, updatable = false)
	private Position position;
	
	@ManyToOne
	@JoinColumn(name = "resource_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Resource resource;
	
	@Column(name = "can_read")
	private boolean read;
	@Column(name = "can_write")
	private boolean write;
	@Column(name = "can_modify")
	private boolean modify;
}
