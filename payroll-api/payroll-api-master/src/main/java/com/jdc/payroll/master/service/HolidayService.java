package com.jdc.payroll.master.service;

import static com.jdc.payroll.utils.helpers.EntityOperationHelper.created;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.notFound;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.updated;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.repo.HolidayRepo;
import com.jdc.payroll.master.input.HolidayFormForCreate;
import com.jdc.payroll.master.input.HolidayFormForUpdate;
import com.jdc.payroll.master.input.HolidaySearch;
import com.jdc.payroll.master.output.HolidayInfo;
import com.jdc.payroll.master.output.HolidayInfoDetails;
import com.jdc.payroll.utils.response.DataModificationResult;
import com.jdc.payroll.utils.response.Pager;

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
		
		var entity = notFound(repo.findById(date), DOMAIN_NAME, date.format(appDateFormatter));
		entity.setType(form.type());
		entity.setHoliday(form.holiday());
		entity.setRemark(form.remark());
		
		return updated(entity.getDate(), DOMAIN_NAME);
	}

	public HolidayInfoDetails findById(LocalDate date) {
		var entity = notFound(repo.findById(date), DOMAIN_NAME, date.format(appDateFormatter));
		return HolidayInfoDetails.from(entity);
	}

	public Pager<HolidayInfo> search(HolidaySearch search, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
