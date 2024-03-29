package com.jdc.payroll.master.service;

import static com.jdc.payroll.utils.helpers.EntityOperationHelper.created;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.getOne;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.updated;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.entity.Settings;
import com.jdc.payroll.domain.master.entity.Settings_;
import com.jdc.payroll.domain.master.repo.SettingsRepo;
import com.jdc.payroll.master.input.SettingForm;
import com.jdc.payroll.master.input.SettingSearch;
import com.jdc.payroll.master.output.SettingInfo;
import com.jdc.payroll.utils.response.DataModificationResult;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class SettingsService {
	
	private static final String DOMAIN = "Settings";
	
	@Autowired
	private SettingsRepo repo;
	
	@Transactional
	public DataModificationResult<Integer> create(SettingForm form) {
		var entity = repo.save(form.entity());
		return created(entity.getId(), DOMAIN);
	}

	@Transactional
	public DataModificationResult<Integer> update(int id, SettingForm form) {
		var entity = getOne(repo.findById(id), DOMAIN, id);
		entity.setEffectAt(form.effectAt());
		entity.setCutOffDay(form.cutOffDay());
		entity.setPayDay(form.payDay());
		return updated(id, DOMAIN);
	}

	public SettingInfo findById(int id) {
		return getOne(repo.findById(id).map(SettingInfo::from), DOMAIN, id);
	}

	public List<SettingInfo> search(SettingSearch search) {
		return repo.search(queryFunc(search));
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<SettingInfo>> queryFunc(SettingSearch search) {
		return cb -> {
			var cq = cb.createQuery(SettingInfo.class);
			var root = cq.from(Settings.class);
			SettingInfo.select(cq, root);
			cq.where(search.where(cb, root));
			cq.orderBy(cb.asc(root.get(Settings_.effectAt)));
			return cq;
		};
	}

}
