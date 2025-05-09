package com.example.AkademyTasks.service;

import com.example.AkademyTasks.projection.EmployeeProjection;
import com.example.AkademyTasks.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeProjection findById(Long id) {
        return employeeRepository.findEmployeeById(id);
    }

    public List<EmployeeProjection> findAllEmployees() {
        return employeeRepository.findAllEmployees();
    }
}
