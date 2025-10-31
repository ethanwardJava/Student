package com.arcade.bootapplication.controller;

import com.arcade.bootapplication.Entity.Students;
import com.arcade.bootapplication.service.StudentServices;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/students")
public class StudentController {

    private final StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    // ==================== GET ALL ====================
    @GetMapping("/all")
    public List<Students> getAllStudents() {
        log.info("Fetching all students | endpoint=/students/all");
        List<Students> students = studentServices.getAll();
        log.info("Retrieved {} students", students.size());
        return students;
    }

    // ==================== GET BY NAME ====================
    @GetMapping("/name/{name}")
    public List<Students> getByName(@PathVariable String name) {
        log.info("Fetching student by name | endpoint=/students/name/{}", name);
        List<Students> students = studentServices.getByName(name);
        log.info("Found {} student(s) with name={}", students.size(), name);
        return students;
    }

    // ==================== GET ACTIVE ====================
    @GetMapping("/active")
    public List<Students> getByIsActiveTrue() {
        log.info("Fetching active students | endpoint=/students/active");
        List<Students> students = studentServices.getByIsActiveTrue();
        log.info("Found {} active student(s)", students.size());
        return students;
    }

    // ==================== GET BY ID ====================
    @GetMapping("/{id}")
    public Students getStudentById(@PathVariable Long id) {
        log.info("Fetching student by id | endpoint=/students/{}", id);
        Students students = studentServices.getById(id);
        log.info("Found student with id={}", id);
        return students;
    }

    // ==================== GET BY COUNTRY ====================
    @GetMapping("/country/{country}")
    public List<Students> getByCountry(@PathVariable String country) {
        log.info("Fetching student by country | endpoint=/students/country/{}", country);
        List<Students> students = studentServices.getByCountry(country);
        log.info("Found {} student(s) in country={}", students.size(), country);
        return students;
    }

    // ==================== GET BY COUNTRY WITH FILTER ====================
    @GetMapping("/country/{country}/filter")
    public List<Students> getByCountryWithFilter(
            @PathVariable String country,
            @RequestParam(required = false) Integer minMark,
            @RequestParam(required = false) Boolean activeOnly) {

        log.info("Fetching students in country={} with minMark={} and activeOnly={}",
                country, minMark, activeOnly);

        return studentServices.findByCountryWithFilter(country, minMark, activeOnly);
    }


    // ==================== ADD NEW ====================
    @PostMapping("/add")
    public Students addNewStudent(@Valid @RequestBody Students students) {
        log.info("Adding new student | {}", students);
        return studentServices.addNew(students);
    }

    // ==================== DELETE ====================
    @DeleteMapping("/remove/{id}")
    public String removeStudentById(@PathVariable Long id) {
        log.info("Removing student with id={}", id);
        studentServices.remove(id);
        return "success";
    }

    // ==================== UPDATE BY ID ====================
    @PutMapping("/update/{id}")
    public Students updateStudentById(@PathVariable Long id, @RequestBody Students students) {
        log.info("Updating student with id={}", id);
        return studentServices.update(id, students);
    }
}
