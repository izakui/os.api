package com.izaquiel.sistemaIz.resources.exceptons;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandarError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessade> errors = new ArrayList<>();

	public ValidationError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessade> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessade(fieldName, message));
	}
	
	

}
