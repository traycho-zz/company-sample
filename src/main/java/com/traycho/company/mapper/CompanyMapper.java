package com.traycho.company.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.traycho.company.dto.CompanyDto;
import com.traycho.company.model.Company;



@Mapper(uses={CompanyOwnerMapper.class},componentModel = "spring")
public abstract class CompanyMapper {

	@Mappings({
        @Mapping(source = "company.uid", target = "uid"),
        @Mapping(source = "company.name", target = "name"),
        @Mapping(source = "company.address", target = "address"),
        @Mapping(source = "company.city", target = "city"),
        @Mapping(source = "company.country", target = "country"),
        @Mapping(source = "company.email", target = "email"),
        @Mapping(source = "company.phone", target = "phone"),
        @Mapping(source = "company.owners", target = "owners")
       
    })
	public abstract CompanyDto from(Company company);
	
	
	@IterableMapping(elementTargetType=CompanyDto.class)
	public abstract List<CompanyDto> fromList(List<Company> companies);
 
}
