package com.example.AkademyTasks.repository;

import com.example.AkademyTasks.model.Employee;
import com.example.AkademyTasks.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e.firstName , ' ' , e.lastName AS fullName, e.position as position, d.name AS departmentName " +
           "FROM Employee e JOIN e.department d " +
           "WHERE e.id = :id")
    EmployeeProjection findEmployeeById(Long id);

    @Query("SELECT e.firstName , ' ' , e.lastName AS fullName, e.position as position, d.name AS departmentName " +
           "FROM Employee e JOIN e.department d ")
    List<EmployeeProjection> findAllEmployees();
}
