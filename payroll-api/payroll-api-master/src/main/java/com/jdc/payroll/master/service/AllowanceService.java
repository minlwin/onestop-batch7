package com.jdc.payroll.master.service;

import static com.jdc.payroll.utils.helpers.EntityOperationHelper.created;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.getOne;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.updated;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.entity.Allowance;
import com.jdc.payroll.domain.master.repo.AllowanceForFixRepo;
import com.jdc.payroll.domain.master.repo.AllowanceForPercentRepo;
import com.jdc.payroll.domain.master.repo.AllowanceForTierRepo;
import com.jdc.payroll.domain.master.repo.AllowanceRepo;
import com.jdc.payroll.master.input.AllowanceForm;
import com.jdc.payroll.master.input.AllowanceFormForFix;
import com.jdc.payroll.master.input.AllowanceFormForTier;
import com.jdc.payroll.master.input.AllowanceSearch;
import com.jdc.payroll.master.input.TierItemForm;
import com.jdc.payroll.master.output.AllowanceInfo;
import com.jdc.payroll.utils.response.DataModificationResult;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class AllowanceService {

	private static final String DOMAIN_NAME = "Allowance";
	
	@Autowired
	private AllowanceRepo repo;
	@Autowired
	private AllowanceForFixRepo fixRepo;
	@Autowired
	private AllowanceForPercentRepo percentRepo;
	@Autowired
	private AllowanceForTierRepo tierRepo;

	@Transactional
	public DataModificationResult<Integer> create(AllowanceForm form) {
		var entity = repo.save(form.getEntity());
		return created(entity.getId(), DOMAIN_NAME);
	}

	public AllowanceInfo findById(int id) {
		return getOne(repo.findById(id).map(AllowanceInfo::from), DOMAIN_NAME, id);
	}

	@Transactional
	public DataModificationResult<Integer> update(int id, AllowanceForm form) {
		
		if(form instanceof AllowanceFormForFix fix) {
			updateForFix(id, fix);
		} else if (form instanceof AllowanceFormForFix percent) {
			updateForPercent(id, percent);
		} else if (form instanceof AllowanceFormForTier tier) {
			updateForTier(id, tier);
		}
		
		return updated(id, DOMAIN_NAME);
	}

	private void updateForTier(int id, AllowanceFormForTier form) {
		var entity = getOne(tierRepo.findById(id), DOMAIN_NAME, id);
		entity.setName(form.getName());
		entity.setDescription(form.getDescription());
		entity.setItems(form.getItems().stream().map(TierItemForm::getEmbeddable).toList());
	}

	private void updateForPercent(int id, AllowanceFormForFix form) {
		var entity = getOne(percentRepo.findById(id), DOMAIN_NAME, id);
		entity.setName(form.getName());
		entity.setDescription(form.getDescription());
		entity.setAmount(form.getAmount());
		entity.setTarget(form.getTarget());
	}

	private void updateForFix(int id, AllowanceFormForFix form) {
		var entity = getOne(fixRepo.findById(id), DOMAIN_NAME, id);
		entity.setName(form.getName());
		entity.setDescription(form.getDescription());
		entity.setAmount(form.getAmount());
		entity.setTarget(form.getTarget());
	}

	public List<AllowanceInfo> search(AllowanceSearch search) {
		return repo.search(queryFunc(search)).stream().map(AllowanceInfo::from).toList();
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<Allowance>> queryFunc(AllowanceSearch search) {
		return cb -> {
			var cq = cb.createQuery(Allowance.class);
			var root = cq.from(Allowance.class);
			cq.select(root);
			cq.where(search.where(cb, root));
			return cq;
		};
	}
}
