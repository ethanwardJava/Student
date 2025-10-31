package com.arcade.bootapplication.service;

import com.arcade.bootapplication.Entity.Students;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentServices {
    List<Students> getAll();

    Students addNew(Students students);

    Students getById(Long id);

    List<Students> getByName(String name);

    List<Students> getByIsActiveTrue();

    List<Students> getByCountry(String country);

    void remove(Long id);

    Students update(Long id, Students students);



    List<Students> findByCountryWithFilter(String country, Integer minMark, Boolean activeOnly);
}
