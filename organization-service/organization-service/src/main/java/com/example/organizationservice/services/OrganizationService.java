package com.example.organizationservice.services;

import com.example.organizationservice.dto.OrganizationDTO;

public interface OrganizationService {

    OrganizationDTO saveOrganization(OrganizationDTO organizationDTO);

    OrganizationDTO getOrganizationById(Long id);

    OrganizationDTO getByOrganizationCode(String organizationCode);
}
