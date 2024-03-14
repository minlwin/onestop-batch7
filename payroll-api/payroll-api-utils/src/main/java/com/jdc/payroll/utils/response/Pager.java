package com.jdc.payroll.utils.response;

import java.util.List;

public record Pager<T>(
		long total,
		int page,
		int size,
		List<T> list) {

}
