package com.jdc.payroll.master.input;

import jakarta.validation.constraints.NotNull;

public record PermissionUpdateItem(
		@NotNull(message = "Please enter resource id")
		Integer resourceId,
		@NotNull(message = "Please enter read permission")
		Boolean read,
		@NotNull(message = "Please enter write permission")
		Boolean write,
		@NotNull(message = "Please enter modify permission")
		Boolean modify) {

}
