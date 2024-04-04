package com.jdc.payroll.master.service;

import static com.jdc.payroll.utils.helpers.EntityOperationHelper.created;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.getOne;
import static com.jdc.payroll.utils.helpers.EntityOperationHelper.updated;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.entity.Deduction;
import com.jdc.payroll.domain.master.repo.DeductionForFixRepo;
import com.jdc.payroll.domain.master.repo.DeductionForPercentRepo;
import com.jdc.payroll.domain.master.repo.DeductionForTierRepo;
import com.jdc.payroll.domain.master.repo.DeductionRepo;
import com.jdc.payroll.master.input.DeductionForm;
import com.jdc.payroll.master.input.DeductionFormForFix;
import com.jdc.payroll.master.input.DeductionFormForPercent;
import com.jdc.payroll.master.input.DeductionFormForTier;
import com.jdc.payroll.master.input.DeductionSearch;
import com.jdc.payroll.master.input.TierItemForm;
import com.jdc.payroll.master.output.DeductionInfo;
import com.jdc.payroll.utils.response.DataModificationResult;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class DeductionService {

	private static final String DOMAIN_NAME = "Deduction";
	
	@Autowired
	private DeductionRepo repo;
	@Autowired
	private DeductionForFixRepo fixRepo;
	@Autowired
	private DeductionForPercentRepo percentRepo;
	@Autowired
	private DeductionForTierRepo tierRepo;

	@Transactional
	public DataModificationResult<Integer> create(DeductionForm form) {
		var entity = repo.save(form.getEntity());
		return created(entity.getId(), DOMAIN_NAME);
	}

	public DeductionInfo findById(int id) {
		return getOne(repo.findById(id).map(DeductionInfo::from), DOMAIN_NAME, id);
	}

	@Transactional
	public DataModificationResult<Integer> update(int id, DeductionForm form) {
		
		if(form instanceof DeductionFormForFix fix) {
			updateForFix(id, fix);
		} else if (form instanceof DeductionFormForPercent percent) {
			updateForPercent(id, percent);
		} else if (form instanceof DeductionFormForTier tier) {
			updateForTier(id, tier);
		}
		
		return updated(id, DOMAIN_NAME);
	}

	private void updateForTier(int id, DeductionFormForTier form) {
		var entity = getOne(tierRepo.findById(id), DOMAIN_NAME, id);
		entity.setName(form.getName());
		entity.setDescription(form.getDescription());
		entity.setItems(form.getItems().stream().map(TierItemForm::getEmbeddable).toList());
	}

	private void updateForPercent(int id, DeductionFormForPercent form) {
		var entity = getOne(percentRepo.findById(id), DOMAIN_NAME, id);
		entity.setName(form.getName());
		entity.setDescription(form.getDescription());
		entity.setAmount(form.getAmount());
		entity.setTarget(form.getTarget());
	}

	private void updateForFix(int id, DeductionFormForFix form) {
		var entity = getOne(fixRepo.findById(id), DOMAIN_NAME, id);
		entity.setName(form.getName());
		entity.setDescription(form.getDescription());
		entity.setAmount(form.getAmount());
		entity.setTarget(form.getTarget());
	}

	public List<DeductionInfo> search(DeductionSearch search) {
		return repo.search(queryFunc(search)).stream().map(DeductionInfo::from).toList();
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<Deduction>> queryFunc(DeductionSearch search) {
		return cb -> {
			var cq = cb.createQuery(Deduction.class);
			var root = cq.from(Deduction.class);
			cq.select(root);
			cq.where(search.where(cb, root));
			return cq;
		};
	}
}
