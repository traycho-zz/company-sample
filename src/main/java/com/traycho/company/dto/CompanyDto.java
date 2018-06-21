package com.traycho.company.dto;

import java.util.List;

public class CompanyDto {

	private String uid;

	private String name;

	private String address;

	private String city;

	private String country;

	private String email;

	private String phone;
	
	private List<CompanyOwnerDto> owners;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

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

	public List<CompanyOwnerDto> getOwners() {
		return owners;
	}

	public void setOwners(List<CompanyOwnerDto> owners) {
		this.owners = owners;
	}
	
	
}
