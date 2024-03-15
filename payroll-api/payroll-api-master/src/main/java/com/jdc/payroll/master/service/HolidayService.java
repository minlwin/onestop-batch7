package com.jdc.payroll.master.service;

import static com.jdc.payroll.utils.helpers.EntityOperationHelper.created;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.getOne;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.updated;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.entity.Holiday;
import com.jdc.payroll.domain.master.entity.Holiday_;
import com.jdc.payroll.domain.master.repo.HolidayRepo;
import com.jdc.payroll.master.input.HolidayFormForCreate;
import com.jdc.payroll.master.input.HolidayFormForUpdate;
import com.jdc.payroll.master.input.HolidaySearch;
import com.jdc.payroll.master.output.HolidayInfo;
import com.jdc.payroll.master.output.HolidayInfoDetails;
import com.jdc.payroll.utils.response.DataModificationResult;
import com.jdc.payroll.utils.response.Pager;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class HolidayService {
	
	private static final String DOMAIN_NAME = "Holiday";

	@Autowired
	private HolidayRepo repo;
	
	@Autowired
	private DateTimeFormatter appDateFormatter;

	@Transactional
	public DataModificationResult<LocalDate> create(HolidayFormForCreate form) {
		var entity = repo.save(form.entity());
		return created(entity.getDate(), DOMAIN_NAME);
	}

	@Transactional
	public DataModificationResult<LocalDate> update(LocalDate date, HolidayFormForUpdate form) {
		
		var entity = getOne(repo.findById(date), DOMAIN_NAME, date.format(appDateFormatter));
		entity.setType(form.type());
		entity.setHoliday(form.holiday());
		entity.setRemark(form.remark());
		
		return updated(entity.getDate(), DOMAIN_NAME);
	}

	public HolidayInfoDetails findById(LocalDate date) {
		var entity = getOne(repo.findById(date), DOMAIN_NAME, date.format(appDateFormatter));
		return HolidayInfoDetails.from(entity);
	}

	public Pager<HolidayInfo> search(HolidaySearch search, int page, int size) {
		
		var queryFunc = queryFunction(search);
		var countFunc = countFunction(search);
		
		var pageResult = repo.search(queryFunc, countFunc, page, size);
		
		return Pager.from(pageResult);
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunction(HolidaySearch search) {
		// select count(h.date) from Holiday h where ...
		return cb -> {
			var cq = cb.createQuery(Long.class);
			// from Holiday h
			var root = cq.from(Holiday.class);
			// select count(h.date)
			cq.select(cb.count(root.get(Holiday_.date)));
			// where ...
			cq.where(search.where(cb, root));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<HolidayInfo>> queryFunction(HolidaySearch search) {
		
		return cb -> {
			var cq = cb.createQuery(HolidayInfo.class);
			// from Holiday h
			var root = cq.from(Holiday.class);
			HolidayInfo.select(cq, root);
			cq.where(search.where(cb, root));
			
			cq.orderBy(cb.asc(root.get(Holiday_.date)));
			
			return cq;
		};
	}

}
