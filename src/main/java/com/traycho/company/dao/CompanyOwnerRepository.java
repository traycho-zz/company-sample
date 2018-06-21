package com.traycho.company.dao;

import org.springframework.data.repository.CrudRepository;

import com.traycho.company.model.CompanyOwner;

public interface CompanyOwnerRepository extends CrudRepository<CompanyOwner, Integer> {

	CompanyOwner findByUid(String uid);
}
