package com.jdc.payroll.master.service;

import static com.jdc.payroll.utils.helpers.EntityOperationHelper.created;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.getOne;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.updated;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.entity.LeaveType;
import com.jdc.payroll.domain.master.repo.LeaveTypeRepo;
import com.jdc.payroll.master.input.LeaveTypeForm;
import com.jdc.payroll.master.input.LeaveTypeSearch;
import com.jdc.payroll.master.output.LeaveTypeInfo;
import com.jdc.payroll.utils.response.DataModificationResult;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class LeaveTypeService {
	
	private static final String DOMAIN = "Leave Type";
	
	@Autowired
	private LeaveTypeRepo repo;

	@Transactional
	public DataModificationResult<Integer> create(LeaveTypeForm form) {
		var entity = repo.save(form.entity());
		return created(entity.getId(), DOMAIN);
	}

	@Transactional
	public DataModificationResult<Integer> update(int id, LeaveTypeForm form) {
		var entity = getOne(repo.findById(id), DOMAIN, id);
		entity.setName(form.name());
		entity.setPaidDays(form.paidDays());
		entity.setRemark(form.remark());
		return updated(id, DOMAIN);
	}

	public LeaveTypeInfo findById(int id) {
		return getOne(repo.findById(id).map(LeaveTypeInfo::from), DOMAIN, id);
	}

	public List<LeaveTypeInfo> search(LeaveTypeSearch search) {
		return repo.search(queryFunc(search));
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<LeaveTypeInfo>> queryFunc(LeaveTypeSearch search) {
		return cb -> {
			var cq = cb.createQuery(LeaveTypeInfo.class);
			var root =  cq.from(LeaveType.class);
			LeaveTypeInfo.select(cq, root);
			cq.where(search.where(cb, root));
			return cq;
		};
	}

}
