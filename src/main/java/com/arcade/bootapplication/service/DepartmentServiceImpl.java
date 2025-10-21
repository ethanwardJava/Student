package com.arcade.bootapplication.service;

import com.arcade.bootapplication.Entity.Department;
import com.arcade.bootapplication.Repo.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    //to be able to use methods like findAll() , save() and other useful methods from the Repository layer
    DepartmentRepository departmentRepository;


    //DI
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository detailsRepository) {
        this.departmentRepository = detailsRepository;
    }

    // saving a whole department no null allowed
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    //getting all the departments
    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }
}
