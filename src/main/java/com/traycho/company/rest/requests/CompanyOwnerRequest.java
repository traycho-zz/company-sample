package com.traycho.company.rest.requests;

import javax.validation.constraints.NotNull;

public class CompanyOwnerRequest {

	
	@NotNull
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
