package com.traycho.company.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="company_owner")
public class CompanyOwner {

	@Id
	@SequenceGenerator(name = "SEQ_COMPANY_OWNER", sequenceName = "seq_company_owner", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPANY_OWNER")
	@Column(name = "company_owner_id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "uid")
	private String uid;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	public CompanyOwner(){
		setUid(UUID.randomUUID().toString());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
