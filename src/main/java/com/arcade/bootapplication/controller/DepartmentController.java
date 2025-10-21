package com.arcade.bootapplication.controller;

import com.arcade.bootapplication.Entity.Department;
import com.arcade.bootapplication.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    //connecting to the service layer
    DepartmentService departmentService;

    //using constructor injection for efficiency
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //a whole department using POST request "/departments call"
    //saving or adding a department
    @PostMapping("/departments")
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    //retrieve all the departments in a List<Department> from the services
    @GetMapping("departments/all")
    public List<Department> getAllDepartments() {
        return departmentService.getAll();
    }

}
