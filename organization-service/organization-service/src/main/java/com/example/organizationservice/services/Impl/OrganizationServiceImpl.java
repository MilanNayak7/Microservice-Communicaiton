package com.example.organizationservice.services.Impl;

import com.example.organizationservice.dto.OrganizationDTO;
import com.example.organizationservice.entity.Organization;
import com.example.organizationservice.exception.ResourceNotFoundException;
import com.example.organizationservice.repoditory.OrganizationRepository;
import com.example.organizationservice.services.OrganizationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;
    ModelMapper modelMapper = new ModelMapper();
    @Override
    public OrganizationDTO saveOrganization(OrganizationDTO organizationDTO) {
        Organization organization = modelMapper.map(organizationDTO,Organization.class);
        Organization savedOrganization = organizationRepository.save(organization);
        return modelMapper.map(savedOrganization,OrganizationDTO.class);
    }

    @Override
    public OrganizationDTO getOrganizationById(Long id) {
        Organization organization = organizationRepository.getReferenceById(id);
        return modelMapper.map(organization,OrganizationDTO.class);
    }

    @Override
    public OrganizationDTO getByOrganizationCode(String organizationCode) {
        Organization organization = organizationRepository.getByOrganizationCode(organizationCode).orElseThrow(()->new ResourceNotFoundException("organization","Code",organizationCode));
        return modelMapper.map(organization,OrganizationDTO.class);
    }
}
