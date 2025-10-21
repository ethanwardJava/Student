package com.arcade.bootapplication.service;

import com.arcade.bootapplication.Entity.Department;
import java.util.List;

//creating a family for the Department
public interface DepartmentService {
    Department saveDepartment(Department department);
    List<Department> getAll();
}
