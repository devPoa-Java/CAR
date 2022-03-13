package com.drivercar.democar.domain.interfaces;

import java.util.List;

public class ErrorResponse {
	List<ErrorData> errors;

	public ErrorResponse(List<ErrorData> errors) {
		this.errors = errors;
	}

	public List<ErrorData> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorData> errors) {
		this.errors = errors;
	}

	

}
