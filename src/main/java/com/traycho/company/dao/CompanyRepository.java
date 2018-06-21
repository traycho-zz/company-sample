package com.traycho.company.dao;

import org.springframework.data.repository.CrudRepository;

import com.traycho.company.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

	Company findByUid(String uid);
}
