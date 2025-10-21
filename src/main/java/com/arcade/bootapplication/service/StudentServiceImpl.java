package com.arcade.bootapplication.service;

import com.arcade.bootapplication.Entity.Students;
import com.arcade.bootapplication.Exceptions.ResourceNotFoundException;
import com.arcade.bootapplication.Repo.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Service implementation class that handles all business logic
 * related to {@link Students} entities.
 *
 * <p>This class delegates data access operations to the
 * {@link StudentRepository} and performs all necessary
 * validation before persisting or updating student data.</p>
 *
 * <p>Logging, performance tracking, and exception handling
 * are managed externally by AOP aspects for clean separation
 * of concerns.</p>
 */
@Service
public class StudentServiceImpl implements StudentServices {

    private final StudentRepository studentRepository;

    /**
     * Constructs a new {@code StudentServiceImpl} with the required repository dependency.
     *
     * @param studentRepository the data repository for student entities
     */
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Retrieves all students from the database.
     *
     * @return list of all {@link Students} entities
     */
    @Override
    public List<Students> getAll() {
        return studentRepository.findAll();
    }

    /**
     * Persists a new student entity to the database.
     *
     * @param students the student object to save
     * @return the saved student entity
     */
    @Override
    public Students addNew(Students students) {
        return studentRepository.save(students);
    }

    /**
     * Retrieves a student by ID or throws an exception if not found.
     *
     * @param id the student's unique identifier
     * @return the found {@link Students} entity
     * @throws ResourceNotFoundException if no student exists with the given ID
     */
    @Override
    public Students getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    /**
     * Retrieves all students by name (case-insensitive).
     *
     * @param name the name of the student(s)
     * @return list of students with the given name
     * @throws ResourceNotFoundException if no students match the provided name
     */
    @Override
    public List<Students> getByName(String name) {
        List<Students> result = studentRepository.findByNameIgnoreCase(name);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("No students found with name: " + name);
        }
        return result;
    }

    /**
     * Retrieves all active students (isActive = true).
     * This method runs within a transactional context.
     *
     * @return list of active students
     */
    @Override
    @Transactional
    public List<Students> getByIsActiveTrue() {
        return studentRepository.findByIsactiveTrue();
    }

    /**
     * Retrieves students by country code.
     *
     * @param country the country code (e.g., "US", "IR")
     * @return list of students matching the provided country
     * @throws ResourceNotFoundException if no students are found for the given country
     */
    @Override
    public List<Students> getByCountry(String country) {
        List<Students> result = studentRepository.findByCountry(country);
        if (result == null || result.isEmpty()) {
            throw new ResourceNotFoundException("No students found with country: " + country);
        }
        return result;
    }

    /**
     * Deletes a student by ID.
     *
     * @param id the student's unique identifier
     * @throws ResourceNotFoundException if no student exists with the given ID
     */
    @Override
    public void remove(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    /**
     * Updates an existing student record by applying non-null fields
     * from the provided {@link Students} object.
     *
     * <p>Validation rules are enforced:
     * <ul>
     *   <li>Mark must be between 0 and 100</li>
     *   <li>Country code must not exceed 4 characters</li>
     * </ul>
     * </p>
     *
     * @param id       the ID of the student to update
     * @param students the new student data to apply
     * @return the updated {@link Students} entity
     * @throws ResourceNotFoundException if no student exists with the given ID
     * @throws IllegalArgumentException  if validation fails (e.g., invalid mark or country code)
     */
    @Override
    @Transactional
    public Students update(Long id, Students students) {
        Students dataBaseStudentObject = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        // --- Update only provided, valid fields ---
        if (Objects.nonNull(students.getName()) && !students.getName().trim().isEmpty()) {
            dataBaseStudentObject.setName(students.getName());
        }
        if (Objects.nonNull(students.getAge())) {
            dataBaseStudentObject.setAge(students.getAge());
        }
        if (Objects.nonNull(students.getBirthdate())) {
            dataBaseStudentObject.setBirthdate(students.getBirthdate());
        }
        if (Objects.nonNull(students.getJoiningtime())) {
            dataBaseStudentObject.setJoiningtime(students.getJoiningtime());
        }
        if (Objects.nonNull(students.getIsactive())) {
            dataBaseStudentObject.setIsactive(students.getIsactive());
        }
        if (Objects.nonNull(students.getMark())) {
            if (students.getMark() < 0 || students.getMark() > 100) {
                throw new IllegalArgumentException("Mark must be between 0 and 100.");
            }
            dataBaseStudentObject.setMark(students.getMark());
        }
        if (Objects.nonNull(students.getCountry()) && !students.getCountry().trim().isEmpty()) {
            if (students.getCountry().length() > 4) {
                throw new IllegalArgumentException("Country code must not exceed 4 characters.");
            }
            dataBaseStudentObject.setCountry(students.getCountry());
        }
        if (Objects.nonNull(students.getPrice())) {
            dataBaseStudentObject.setPrice(students.getPrice());
        }

        // --- Persist the updated entity ---
        return studentRepository.save(dataBaseStudentObject);
    }
}
