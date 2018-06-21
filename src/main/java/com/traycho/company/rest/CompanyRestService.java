package com.traycho.company.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.traycho.company.dao.CompanyOwnerRepository;
import com.traycho.company.dao.CompanyRepository;
import com.traycho.company.dto.CompanyDto;
import com.traycho.company.dto.CompanyOwnerDto;
import com.traycho.company.mapper.CompanyMapper;
import com.traycho.company.mapper.CompanyOwnerMapper;
import com.traycho.company.model.Company;
import com.traycho.company.model.CompanyOwner;
import com.traycho.company.rest.requests.CompanyOwnerRequest;
import com.traycho.company.rest.requests.CompanyRequest;

@RestController
@RequestMapping(CompanyRestService.PATH)
public class CompanyRestService {
	
	public static final String PATH = "/companies";
	
	public static final String PATH_OWNERS = "{uid}/owners";
	
	public static final String PARAM_UID = "{uid}";
	
	
	
	@Autowired
	CompanyRepository companyDao;
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	CompanyOwnerMapper companyOwnerMapper;
	
	
	@Autowired
	CompanyOwnerRepository companyOwnerDao;
	
	@GetMapping
    public ResponseEntity<List<CompanyDto>> listAll() {
        List<Company> companies = (List<Company>) companyDao.findAll();
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
      
        List<CompanyDto> dtos =  companyMapper.fromList(companies);
        
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
	
	@GetMapping(PARAM_UID)
	public ResponseEntity<CompanyDto> details(@PathVariable("uid") String uid) {
		Company company = companyDao.findByUid(uid);
		
		if(company == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CompanyDto dto = companyMapper.from(company);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PutMapping(PARAM_UID)
	public ResponseEntity<CompanyDto> update(@PathVariable("uid") String uid, //
										  @Valid @RequestBody CompanyRequest request) {
		
		Company company = companyDao.findByUid(uid);
		if(company == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		company.setAddress(request.getAddress());
		company.setCity(request.getCity());
		company.setCountry(request.getCountry());
		company.setName(request.getName());
		company.setPhone(request.getPhone());
		company.setEmail(request.getEmail());
		
		company = companyDao.save(company);
		
		CompanyDto dto = companyMapper.from(company);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CompanyDto> create(@Valid @RequestBody CompanyRequest request) {
		
		Company company = new Company();
		company.setAddress(request.getAddress());
		company.setCity(request.getCity());
		company.setCountry(request.getCountry());
		company.setName(request.getName());
		company.setPhone(request.getPhone());
		company.setEmail(request.getEmail());
		
		company = companyDao.save(company);
		
		CompanyDto dto = companyMapper.from(company);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	@PostMapping(PATH_OWNERS)
	public ResponseEntity<CompanyOwnerDto> addOwner(@PathVariable("uid") String uid,//
												@Valid @RequestBody CompanyOwnerRequest request) {
		
		Company company = companyDao.findByUid(uid);
		if(company == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		CompanyOwner owner = new CompanyOwner();
		owner.setCompany(company);
		owner.setName(request.getName());
		
		owner = companyOwnerDao.save(owner);
		
		CompanyOwnerDto dto = companyOwnerMapper.from(owner);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
