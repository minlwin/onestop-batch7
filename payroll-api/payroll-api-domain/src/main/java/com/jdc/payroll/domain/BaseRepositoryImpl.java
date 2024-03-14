package com.jdc.payroll.domain;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID>{

	private EntityManager entityManager;
	
	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		var cq = queryFunc.apply(entityManager.getCriteriaBuilder());
		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public <R> Page<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc,
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, int page, int size) {
		
		var countQuery = countFunc.apply(entityManager.getCriteriaBuilder());
		var count = entityManager.createQuery(countQuery).getSingleResult();
		
		var cq = queryFunc.apply(entityManager.getCriteriaBuilder());
		var query = entityManager.createQuery(cq);
		query.setFirstResult(page * size);
		query.setMaxResults(size);
		var list = query.getResultList();
		
		var pageInfo = PageRequest.of(page, size);
		
		return new PageImpl<R>(list, pageInfo, count);
	}

}
