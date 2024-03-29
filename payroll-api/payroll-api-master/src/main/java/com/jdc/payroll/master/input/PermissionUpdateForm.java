package com.jdc.payroll.master.input;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public record PermissionUpdateForm(
		@NotEmpty(message = "Please select position.")
		String posigionId,
		@NotEmpty
		List<@Valid PermissionUpdateItem> items
		) {

}
