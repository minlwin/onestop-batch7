package com.jdc.payroll.domain.master.repo;

import com.jdc.payroll.domain.BaseRepository;
import com.jdc.payroll.domain.master.entity.Department;

public interface DepartmentRepo extends BaseRepository<Department, String>{

	long countByCode(String code);
}
