package com.traycho.company;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.traycho.company.dto.CompanyDto;
import com.traycho.company.dto.CompanyOwnerDto;
import com.traycho.company.rest.CompanyRestService;
import com.traycho.company.rest.requests.CompanyOwnerRequest;
import com.traycho.company.rest.requests.CompanyRequest;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCompanyRestService {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestCompanyRestService.class);
	
	public static final String SERVER_URL = "http://localhost:8080";

	public static final String COMPANIES_URL = SERVER_URL + CompanyRestService.PATH;
	
	protected static CompanyDto company;
	
	protected static CompanyOwnerDto companyOwner;
	
	@Test
	public void test1CreateCompany(){
		LOG.info("Test create company");
		RestTemplate client = new RestTemplate();
		
		CompanyRequest request = new CompanyRequest();
		request.setName("Tesla Motors");
		request.setEmail("sales@tesla.com");
		request.setAddress("3540 Deer Creek Rd");
		request.setCity("San Francisco");
		request.setCountry("USA");
		request.setPhone("+1 (888) 518-3751");		

		
		company = client.exchange(COMPANIES_URL, HttpMethod.POST,new HttpEntity<Object>(request),CompanyDto.class).getBody();
		Assert.assertNotNull(company);
		Assert.assertNotNull(company.getUid());
		Assert.assertEquals(request.getName(), company.getName());
		Assert.assertEquals(request.getAddress(), company.getAddress());
		Assert.assertEquals(request.getCity(), company.getCity());
		Assert.assertEquals(request.getCountry(), company.getCountry());
		Assert.assertEquals(request.getEmail(), company.getEmail());
		Assert.assertEquals(request.getPhone(), company.getPhone());

	}
	
	@Test
	public void test2CompanyDetails(){
		RestTemplate client = new RestTemplate();
		
		CompanyDto companyDetails = client.exchange(COMPANIES_URL + "/" + company.getUid(), HttpMethod.GET, new HttpEntity<Object>(new HttpHeaders()),CompanyDto.class).getBody();
		Assert.assertNotNull(companyDetails);
		Assert.assertNotNull(companyDetails.getUid());
		Assert.assertEquals(companyDetails.getName(), company.getName());
		Assert.assertEquals(companyDetails.getAddress(), company.getAddress());
		Assert.assertEquals(companyDetails.getCity(), company.getCity());
		Assert.assertEquals(companyDetails.getCountry(), company.getCountry());
		Assert.assertEquals(companyDetails.getEmail(), company.getEmail());
		Assert.assertEquals(companyDetails.getPhone(), company.getPhone());
	}
	
	@Test
	public void test3UpdateCompanyDetails(){
		RestTemplate client = new RestTemplate();
		
		CompanyRequest request = new CompanyRequest();
		request.setName("Tesla Inc.");
		request.setEmail("support@tesla.com");
		request.setAddress("3500 Deer Creek Rd");
		request.setCity("Palo Alto");
		request.setCountry("USA");
		request.setPhone("+1 (888) 518-3752");
		
		company = client.exchange(COMPANIES_URL + "/" + company.getUid(), HttpMethod.POST, new HttpEntity<Object>(request),CompanyDto.class).getBody();
		Assert.assertNotNull(company);
		Assert.assertNotNull(company.getUid());
		Assert.assertEquals(company.getName(), request.getName());
		Assert.assertEquals(company.getAddress(), request.getAddress());
		Assert.assertEquals(company.getCity(), request.getCity());
		Assert.assertEquals(company.getCountry(), request.getCountry());
		Assert.assertEquals(company.getEmail(), request.getEmail());
		Assert.assertEquals(company.getPhone(), request.getPhone());
	}
	
	@Test
	public void test3AddCompanyOwner(){
		RestTemplate client = new RestTemplate();
		
		CompanyOwnerRequest request = new CompanyOwnerRequest();
		request.setName("Elon Musk");
		
		
		companyOwner = client.exchange(COMPANIES_URL + "/" + company.getUid() + "/owners", HttpMethod.POST, new HttpEntity<Object>(request),CompanyOwnerDto.class).getBody();
		Assert.assertNotNull(companyOwner);
		Assert.assertNotNull(companyOwner.getUid());
		Assert.assertEquals(companyOwner.getCompany(), company.getUid());
		
		company =  client.exchange(COMPANIES_URL + "/" + company.getUid(), HttpMethod.GET, new HttpEntity<Object>(new HttpHeaders()),CompanyDto.class).getBody();
		Assert.assertNotNull(company.getOwners());
		Assert.assertTrue(company.getOwners().size() == 1);
	}
	
	@Test
	public void test5CreateCompanyOnlyMandatory(){
		LOG.info("Test create company only mandatory fields");
		RestTemplate client = new RestTemplate();
		CompanyRequest request = new CompanyRequest();
		request.setName("Tesla Motors");
		request.setAddress("3540 Deer Creek Rd");
		request.setCity("San Francisco");
		request.setCountry("USA");
		
		company = client.exchange(COMPANIES_URL, HttpMethod.POST,new HttpEntity<Object>(request),CompanyDto.class).getBody();
		Assert.assertNotNull(company);
		Assert.assertNotNull(company.getUid());
		Assert.assertEquals(request.getAddress(), company.getAddress());
		Assert.assertEquals(request.getCity(), company.getCity());
		Assert.assertEquals(request.getCountry(), company.getCountry());
		Assert.assertNull(request.getEmail());
		Assert.assertNull(request.getPhone());

	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void test6ListAllCompanies(){
		RestTemplate client = new RestTemplate();
		
		List<CompanyDto> companies =  client.exchange(COMPANIES_URL, HttpMethod.GET, new HttpEntity<Object>(new HttpHeaders()),List.class).getBody();
		Assert.assertNotNull(companies);
		Assert.assertTrue(companies.size() == 2);
	}
	
}

