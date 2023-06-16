package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.OrganizationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="ORGANIZATION-SERVICE")
public interface APIClientOrganization{

    @GetMapping("api/organization/{organizationCode}")
    OrganizationDTO getByOrganizationCode(@PathVariable("organizationCode")String organizationCode);

}
