package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exception.ResourceNotFoundException;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

   private EmployeeRepository employeeRepository;
    ModelMapper modelMapper = new ModelMapper();

   private APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee mappedEmployee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(mappedEmployee);
        EmployeeDto returnedEmployee = modelMapper.map(savedEmployee, EmployeeDto.class);
        return returnedEmployee;
    }

    @Override
    public APIResponseDto findEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        EmployeeDto employeeDto =  modelMapper.map(employee,EmployeeDto.class);

        DepartmentDto departmentById = apiClient.getDepartmentById(employee.getDepartmentCode());
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setDepartment(departmentById);
        apiResponseDto.setEmployee(employeeDto);

        return apiResponseDto;
    }
}
