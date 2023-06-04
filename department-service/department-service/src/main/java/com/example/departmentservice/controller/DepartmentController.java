package com.example.departmentservice.controller;


import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    @GetMapping("{departmentCode}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable String departmentCode){
        DepartmentDto savedDepartmentDto = departmentService.getDepartmentDepartmentCode(departmentCode);
        return new ResponseEntity<>(savedDepartmentDto,HttpStatus.OK);
    }
}
