package com.jdc.payroll.master.service;

import static com.jdc.payroll.utils.helpers.EntityOperationHelper.created;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.getOne;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.updated;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.entity.Position;
import com.jdc.payroll.domain.master.entity.PositionPk;
import com.jdc.payroll.domain.master.entity.PositionPk_;
import com.jdc.payroll.domain.master.entity.Position_;
import com.jdc.payroll.domain.master.repo.PositionRepo;
import com.jdc.payroll.master.input.PositionFormForCreate;
import com.jdc.payroll.master.input.PositionFormForUpdate;
import com.jdc.payroll.master.input.PositionSearch;
import com.jdc.payroll.master.output.PositionInfo;
import com.jdc.payroll.master.output.PositionInfoDetails;
import com.jdc.payroll.master.service.events.PositionCreationEvent;
import com.jdc.payroll.utils.exceptions.ApiBusinessException;
import com.jdc.payroll.utils.response.DataModificationResult;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional
public class PositionService {
	
	private static final String DOMAIN_NAME = "Position";
	
	@Autowired
	private PositionRepo repo;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	public DataModificationResult<String> create(PositionFormForCreate form) {
		
		var entity = form.entity();
		
		if(repo.findById(entity.getId()).isPresent()) {
			var message = "Position code %s has already been used in department with code %s.".formatted(form.position(), form.department());
			throw new ApiBusinessException(message);
		}
		
		entity = repo.save(entity);
		
		publisher.publishEvent(new PositionCreationEvent(entity.getId().getCode()));
		
		return created(entity.getId().getCode(), DOMAIN_NAME);
	}

	public DataModificationResult<String> update(String code, PositionFormForUpdate form) {
		var id = PositionPk.parse(code);
		var entity = getOne(repo.findById(id), DOMAIN_NAME, id.getCode());
		
		entity.setBasicSalary(form.basicSalary());
		entity.setOtFeesPerHour(form.otPerHour());
		entity.setAnualLeaves(form.anualLeaves());
		
		return updated(id.getCode(), DOMAIN_NAME);
	}

	@Transactional(readOnly = true)
	public PositionInfoDetails findById(String code) {
		var id = PositionPk.parse(code);
		var entity = getOne(repo.findById(id), DOMAIN_NAME, code);
		return PositionInfoDetails.from(entity);
	}

	@Transactional(readOnly = true)
	public List<PositionInfo> search(PositionSearch search) {
		return repo.search(queryFunc(search));
	}

	private Function<CriteriaBuilder, CriteriaQuery<PositionInfo>> queryFunc(PositionSearch search) {
		return cb -> {
			var cq = cb.createQuery(PositionInfo.class);
			
			var root = cq.from(Position.class);
			cq.multiselect(PositionInfo.select(root));
			cq.where(search.where(cb, root));
			cq.orderBy(
				cb.asc(root.get(Position_.id).get(PositionPk_.departmentCode)),
				cb.asc(root.get(Position_.id).get(PositionPk_.positionCode))
			);
			return cq;
		};
	}
}
