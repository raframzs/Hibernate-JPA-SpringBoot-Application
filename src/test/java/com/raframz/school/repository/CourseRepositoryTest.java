package com.raframz.school.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.raframz.school.SchoolApplication;
import com.raframz.school.entity.Course;
import com.raframz.school.entity.Review;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= SchoolApplication .class)
class CourseRepositoryTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Autowired
    EntityManager em;

    @Test
    public void getById_basic() {
        Course course = repository.getById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());
    }

    /**
     * Demo for test caching without the @Transactional annotation both calls
     * may be fire a SELECT query though hibernate because the annotation in
     * the repository makes a each call as a transaction. Otherwise if
     * @Transactional is in the method signature, then a persistence context
     * is created, letting that equals method calls start fired through a
     * caching data.
     */
    @Test
    @Transactional
    public void getById_firstLevelDemo() {
        Course course = repository.getById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());

        Course course1 = repository.getById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());
    }

    @Test
    @DirtiesContext // ==> Spring will automatically reset the data
    public void deleteById_basic() {
        repository.deleteById(10002L);
        assertNull(repository.getById(10002L));
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        // get a course
        Course course = repository.getById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());

        // update record
        course.setName("JPA in 50 Steps - Updated");

        repository.save(course);

        //Check the value
        Course course1 = repository.getById(10001L);
        assertEquals("JPA in 50 Steps - Updated", course1.getName());

    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse(){
        Course course = repository.getById(10001L);
        log.info("{}", course.getReviews());

    }

    @Test
    @Transactional
    public void retrieveCourseForReview(){
        Review review = em.find(Review.class, 20300L);
        log.info("{}", review.getCourse());

    }

}


