package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.dto.OrganizationDTO;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exception.ResourceNotFoundException;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

   private EmployeeRepository employeeRepository;
    ModelMapper modelMapper = new ModelMapper();

   private APIClient apiClient;
   private APIClientOrganization apiClientOrganization;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee mappedEmployee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(mappedEmployee);
        EmployeeDto returnedEmployee = modelMapper.map(savedEmployee, EmployeeDto.class);
        return returnedEmployee;
    }



    @CircuitBreaker(name="${spring.application.name}",fallbackMethod ="getDefaultDepartment")
    @Override
    public APIResponseDto findEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        EmployeeDto employeeDto =  modelMapper.map(employee,EmployeeDto.class);

        DepartmentDto departmentById = apiClient.getDepartmentById(employee.getDepartmentCode());
        OrganizationDTO byOrganizationCode = apiClientOrganization.getByOrganizationCode(employee.getOrganizationCode());

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setDepartment(departmentById);
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setOrganizationDTO(byOrganizationCode);

        return apiResponseDto;
    }



    public APIResponseDto getDefaultDepartment(Long id,Exception e) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("Test Dept");
        departmentDto.setDepartmentCode("Test");
        departmentDto.setDepartmentDescription("Research nd development");


        EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setEmployee(employeeDto);

        return apiResponseDto;
    }
}
