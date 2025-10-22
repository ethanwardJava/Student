package com.arcade.bootapplication.service;

import com.arcade.bootapplication.Entity.Students;
import com.arcade.bootapplication.Repo.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class StudentServicesTest {
    @Autowired
    private StudentServices studentServices;

    @MockitoBean
    private StudentRepository repository;
    /* ==============  SAMPLE USER  =================
     * "id": 25,
     * "name": "Yara",
     * "birthdate": "2002-11-11",
     * "joiningtime": "2025-09-27T03:17:27.095+00:00",
     * "isactive": true,
     * "mark": 9,
     * "age": 21,
     * "country": "GER",
     * "price": "$124.50"
     */

    @BeforeEach
    void setUp(){
        Students setupStudent = Students.builder()
                .id(25L)
                .name("Yara")
                .birthdate(Date.valueOf("2002-11-11"))
                .joiningtime(Timestamp.valueOf("2025-09-27 03:17:27.095"))
                .isactive(true)
                .mark(9)
                .age(21)
                .country("GER")
                .price("$124.50")
                .build();

        // Mock the repository to return the setup student when findByName is called with "Yara"
        Mockito.when(repository.findByNameIgnoreCase("Yara")).thenReturn(List.of(setupStudent));    }


    //Will find the student by a valid name
    @Test
    @DisplayName("Checking The name of expected student with the actual name ")
    @Disabled
    public void findTheStudentByValidName() {
        String studentName = "Yara";
        List<Students> foundList = studentServices.getByName(studentName);
        Students found = foundList.getFirst();
        assertEquals(studentName, found.getName());
        assertFalse(foundList.isEmpty()); // Ensure the list is not empty
        Mockito.verify(repository).findByNameIgnoreCase(studentName); // Verify the mock was called
    }


}