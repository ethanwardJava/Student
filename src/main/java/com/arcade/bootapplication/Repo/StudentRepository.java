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

    void deleteStudentsById(Long id);


    List<Students> findByIsactiveTrue();


    @Query(value = "SELECT c FROM Students c WHERE LOWER(c.country) = LOWER(:country)")
    List<Students> findByCountry(@Param("country") String country);

    @Query("SELECT s FROM Students s WHERE LOWER(s.name) = LOWER(:name)")
    List<Students> findByNameIgnoreCase(@Param("name") String name);

    @Query("""
            SELECT s FROM Students s
            WHERE LOWER(s.country) = LOWER(:country)
              AND (:minMark IS NULL OR s.mark >= :minMark)
              AND (:activeOnly IS NULL OR s.isactive = :activeOnly)
            """)
    List<Students> findByCountryWithFilter(
            @Param("country") String country,
            @Param("minMark") Integer minMark,
            @Param("activeOnly") Boolean activeOnly
    );


}
