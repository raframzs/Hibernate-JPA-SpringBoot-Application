package com.raframz.school.repository;

import com.raframz.school.SchoolApplication;
import com.raframz.school.entity.Course;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= SchoolApplication .class)
class CourseSpringDataRepositoryTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repository;

    @Test
    public void getById_CoursePresent(){
        Optional<Course> course = repository.findById(10001L);
        assertTrue(course.isPresent());
    }

    @Test
    public void playing(){
        /**
         * CREATE, UPDATE
         */
        /*Course course = new Course("Microservices in 100 Steps");
        repository.save(course);

        course.setName("Microservices in 100 Steps Updated");
        repository.save(course);*/

        /**
         * READ ALL, COUNT THEM
         */
        log.info("Courses: {}, Quantity: {}",
                repository.findAll(), repository.count());

    }

    @Test
    public void pagination(){
        PageRequest pageRequest = PageRequest.of(0, 4);
        Page<Course> firstPage = repository.findAll(pageRequest);
        log.info("\nFirst Page -> {}", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        log.info("\nSecond Page -> {}", secondPage.getContent());
    }

    @Test
    public void findByName(){
        log.info("Find by name -> {}", repository.findByName("Java OCA"));
    }

}


