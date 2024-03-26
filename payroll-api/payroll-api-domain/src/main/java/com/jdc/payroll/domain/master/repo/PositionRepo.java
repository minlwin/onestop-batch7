package com.jdc.payroll.domain.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jdc.payroll.domain.BaseRepository;
import com.jdc.payroll.domain.master.entity.Position;
import com.jdc.payroll.domain.master.entity.PositionPk;
import com.jdc.payroll.domain.master.entity.PositionPk.PositionCode;

public interface PositionRepo extends BaseRepository<Position, PositionPk>{

	long countById(PositionPk id);
	
	@Query("select p.id.positionCode from Position p where p.id.departmentCode = ?1")
	List<PositionCode> findCodeByDepartment(String code);
}
