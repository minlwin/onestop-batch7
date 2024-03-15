package com.jdc.payroll.utils.response;

import java.util.List;

import org.springframework.data.domain.Page;

public record Pager<T>(
		long total,
		int page,
		int size,
		List<T> list) {

	public static<T> Pager<T> from(Page<T> result) {
		return new Pager<T>(
				result.getTotalElements(), 
				result.getNumber(), 
				result.getSize(), 
				result.getContent());
	}

}
