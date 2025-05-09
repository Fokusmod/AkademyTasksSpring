package com.example.AkademyTasks.controller;

import com.example.AkademyTasks.projection.EmployeeProjection;
import com.example.AkademyTasks.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/projection/{id}")
    public EmployeeProjection getEmployee(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping("/employee/projection")
    public List<EmployeeProjection> getEmployee() {
        return employeeService.findAllEmployees();
    }


}
