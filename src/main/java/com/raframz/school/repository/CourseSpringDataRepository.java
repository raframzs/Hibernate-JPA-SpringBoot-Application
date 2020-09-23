package com.raframz.school.repository;


import com.raframz.school.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "courses") // http://localhost:8080/courses/10001
public interface CourseSpringDataRepository extends JpaRepository<Course, Long>{

    @Override
    Optional<Course> findById(Long id);

    List<Course> findByName(String name);
    List<Course> countByName(String name);
    List<Course> findByNameOrderByIdDesc(String name);

    @Override
    void deleteById(Long id);

    @Query("SELECT c FROM Course c WHERE name LIKE '%100 Steps'")
    List<Course> coursesLike100Steps();

    @Query(value = "SELECT * FROM Course  WHERE name LIKE '%100 Steps'", nativeQuery = true)
    List<Course> coursesLike100StepsNative();

    @Query(name = "getAllCoures")
    List<Course> getAll();
}




























