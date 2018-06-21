package com.traycho.company.rest.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompanyRequest {

	@NotNull
	@Size(min=3,max=100)
	private String name;

	@NotNull
	@Size(min=3,max=100)
	private String address;

	@NotNull
	@Size(min=3,max=50)
	private String city;

	@NotNull
	@Size(min=2,max=50)
	private String country;

	private String email;

	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
