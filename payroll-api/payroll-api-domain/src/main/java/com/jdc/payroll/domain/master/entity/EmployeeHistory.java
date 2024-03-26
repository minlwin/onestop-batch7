package com.jdc.payroll.domain.master.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.payroll.domain.AbstractEntity;
import com.jdc.payroll.domain.master.entity.Employee.Gender;
import com.jdc.payroll.domain.master.entity.Employee.Status;
import com.jdc.payroll.domain.master.entity.PositionPk.PositionCode;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeHistory extends AbstractEntity{

	@EmbeddedId
	private EmployeeHistoryPk id;
	
	@Column(nullable = false)
	private Type type;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_code", referencedColumnName = "code", insertable = false, updatable = false)
	private Employee employee;
	
	private String name;
	
	private String departmentCode;
	private String departmentName;
	private PositionCode position;
	
	private String phone;
	private String email;
	private Gender gender;
	private LocalDate dateOfBirth;

	private Status status;
	private LocalDate assignDate;
	private LocalDate provationPassDate;
	private LocalDate retireDate;
	
	private String remark;
	
	public enum Type {
		Entry("Create Employee"),
		ChangeStatus("Change Status"), 
		ChangePosition("Change Position"), 
		ChangeInfo("Change Information");
		
		private String value;

		private Type(String value) {
			this.value = value;
		}
		
		public String getDesplayName() {
			return value;
		}
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
		this.id = new EmployeeHistoryPk();
		id.setEmployeeCode(employee.getCode());
		id.setChangeAt(LocalDateTime.now());
		this.name = employee.getAccount().getName();
		this.departmentCode = employee.getDepartment().getCode();
		this.departmentName = employee.getDepartment().getName();
		this.position = employee.getPosition().getId().getPositionCode();
		this.phone = employee.getPhone();
		this.email = employee.getEmail();
		this.gender = employee.getGender();
		this.dateOfBirth = employee.getDateOfBirth();
		this.status = employee.getStatus();
		this.assignDate = employee.getAssignDate();
		this.provationPassDate = employee.getProvationPassDate();
		this.retireDate = employee.getRetireDate();
		this.remark = employee.getRemark();
	}
}
