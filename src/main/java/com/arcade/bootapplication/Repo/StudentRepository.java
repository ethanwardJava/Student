package com.arcade.bootapplication.Repo;

import com.arcade.bootapplication.Entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {

    @Query("SELECT s FROM Students s WHERE s.name = :name")
    List<Students> findByName(@Param("name") String name);

    List<Students> findByIsactiveTrue();

    List<Students> findByCountry(String country);

    void deleteStudentsById(Long id);

    @Query("SELECT s FROM Students s WHERE LOWER(s.name) = LOWER(:name)")
    List<Students> findByNameIgnoreCase(@Param("name") String name);

}
