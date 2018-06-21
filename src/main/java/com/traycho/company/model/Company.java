package com.traycho.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@SequenceGenerator(name = "SEQ_COMPANY", sequenceName = "seq_company", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPANY")
	@Column(name = "company_id")
	private Integer id;

	@Column(name = "uid")
	private String uid;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "country")
	private String country;

	@OneToMany(fetch = FetchType.LAZY,targetEntity=CompanyOwner.class)
	@JoinColumn(name = "company_id")
	private List<CompanyOwner> owners;
	
	public Company() {
		setUid(UUID.randomUUID().toString());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public List<CompanyOwner> getOwners() {
		return owners;
	}

	public void setOwners(List<CompanyOwner> owners) {
		this.owners = owners;
	}
	
	public void addOwner(CompanyOwner owner){
		if(owners == null){
			owners = new ArrayList<>();
		}
		owners.add(owner);
	}
	
}
