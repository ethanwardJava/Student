package com.arcade.bootapplication.Repo;

import com.arcade.bootapplication.Entity.Students;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Students savedStudent;

    @BeforeEach
    void setUp() {
        Students setupStudent = Students.builder()
                .name("Yara Karimi")
                .birthdate(Date.valueOf("2002-11-11"))
                .joiningtime(Timestamp.valueOf("2025-09-27 03:17:27.095"))
                .isactive(true)
                .mark(9)
                .age(21)
                .country("GER")
                .price(String.valueOf(new BigDecimal("124.50")))  // Fixed
                .build();

        savedStudent = testEntityManager.persistAndFlush(setupStudent);
        assertNotNull(savedStudent.getId()); // Optional: verify persistence
    }

    @Test
    void findById_returnStudent() {
        Students student = studentRepository.findById(savedStudent.getId()).orElse(null);
        assertNotNull(student);
        assertEquals("Yara Karimi", student.getName());
    }
}


