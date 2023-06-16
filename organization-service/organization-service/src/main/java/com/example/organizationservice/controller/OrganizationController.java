package com.example.organizationservice.controller;


import com.example.organizationservice.dto.OrganizationDTO;
import com.example.organizationservice.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @PostMapping
    private ResponseEntity<OrganizationDTO> saveEmployee(@RequestBody OrganizationDTO organizationDTO){
        OrganizationDTO savedOrganizationDto =organizationService.saveOrganization(organizationDTO);
        return new ResponseEntity<>(savedOrganizationDto, HttpStatus.CREATED);
    }

//    @GetMapping ("{id}")
//    private ResponseEntity<OrganizationDTO> getOrganizationById(@PathVariable Long id){
//        OrganizationDTO returenedOrganizationDto = organizationService.getOrganizationById(id);
//        return  new ResponseEntity<>(returenedOrganizationDto,HttpStatus.OK);
//    }

    @GetMapping ("{organizationCode}")
    private ResponseEntity<OrganizationDTO> getOrganizationByDepartmentCode(@PathVariable String organizationCode){
        OrganizationDTO returenedOrganizationDto = organizationService.getByOrganizationCode(organizationCode);
        return  new ResponseEntity<>(returenedOrganizationDto,HttpStatus.OK);
    }
}
