package com.example.departmentservice.services;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentDepartmentCode(String departmentCode);
}

