package com.jdc.payroll.security.api.model;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordForm(
		@NotBlank(message = "Please enter old password.")
		String oldPass,
		@NotBlank(message = "Please enter new password.")
		String newPass
		) {

}
