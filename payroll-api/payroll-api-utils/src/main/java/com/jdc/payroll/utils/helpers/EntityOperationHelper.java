package com.jdc.payroll.utils.helpers;

import java.util.Optional;

import com.jdc.payroll.utils.exceptions.ApiBusinessException;
import com.jdc.payroll.utils.response.DataModificationResult;

public class EntityOperationHelper {

	public static<T, ID> T getOne(Optional<T> optional, String domain, ID id) {
		var message = "There is no %s with id %s!".formatted(domain, id);
		return optional.orElseThrow(() -> new ApiBusinessException(message));
	}
	
	public static<ID> DataModificationResult<ID> created(ID id, String domain) {
		var message = "%s has been created successfully!".formatted(domain);
		return new DataModificationResult<ID>(id, message);
	}

	public static<ID> DataModificationResult<ID> updated(ID id, String domain) {
		var message = "%s has been updated successfully!".formatted(domain);
		return new DataModificationResult<ID>(id, message);
	}
}
