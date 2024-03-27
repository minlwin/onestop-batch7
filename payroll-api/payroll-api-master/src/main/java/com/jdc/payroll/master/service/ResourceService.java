package com.jdc.payroll.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.payroll.domain.master.repo.ResourceRepo;
import com.jdc.payroll.master.output.ResourceInfo;

@Service
public class ResourceService {
	
	@Autowired
	private ResourceRepo repo;

	@Transactional(readOnly = true)
	public List<ResourceInfo> findAll() {
		return repo.findAll().stream().map(ResourceInfo::from).toList();
	}

}
