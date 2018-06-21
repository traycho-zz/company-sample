package com.traycho.company.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.traycho.company.dto.CompanyOwnerDto;
import com.traycho.company.model.CompanyOwner;



@Mapper(componentModel = "spring")
public abstract class CompanyOwnerMapper {

	
	@Mappings({
        @Mapping(source = "owner.uid", target = "uid"),
        @Mapping(source = "owner.name", target = "name"),
        @Mapping(source = "owner.company.uid", target = "company")
       
    })
	public abstract CompanyOwnerDto from(CompanyOwner owner);
	
	
	@IterableMapping(elementTargetType=CompanyOwnerDto.class)
	public abstract List<CompanyOwnerDto> fromList(List<CompanyOwner> companies);
 
}