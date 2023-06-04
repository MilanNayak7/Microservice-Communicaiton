package com.example.departmentservice.services.serviceImpl;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.exception.ResourceNotFoundException;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDto returnDepartmentDto = modelMapper.map(savedDepartment, DepartmentDto.class);
        return  returnDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentDepartmentCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode).orElseThrow(() -> new ResourceNotFoundException("Department","departmentCode",departmentCode));
        DepartmentDto returnedDepartmentDto = modelMapper.map(department, DepartmentDto.class);
        return returnedDepartmentDto;
    }

}
