package com.raframz.school.repository;

import com.raframz.school.SchoolApplication;
import com.raframz.school.entity.Course;
import com.raframz.school.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
public class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    /**
     * CREATING A CRITERIA QUERY
     *
     * 1. Use criteria builder to create a CriteriaQuery returning the
     * expected result object.
     *
     * 2. Define roots for tables which are involved in the query
     * 3. Define Predicates etc using Criteria Builder
     * 4. Add Predicates etc to the Criteria Query
     * 5. Build the TypedQuery using the entity manager and criteria query
     *
     */
    @Test
    public void allCourses() {
        // "SELECT c FROM Course c"
        // First
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        // Second
        Root<Course> courseRoot = cq.from(Course.class);
        // Five
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    /**
     * Retrieve all courses through a condition
     * WHERE - LIKE
     */
    @Test
    public void allCouresCondition() {
        // "SELECT s FROM Student s WHERE s.passport.number LIKE '%1412%'"
        // First
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        // Second
        Root<Course> courseRoot = cq.from(Course.class);

        // Third
        Predicate like100Steps = cb.like(courseRoot.get("name"),
                                "%100 Steps");
        // Fourth
        cq.where(like100Steps);

        // Five
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();
        logger.info("Typed Query Condition -> {}", resultList);

    }

    /**
     *  Retrieve all courses through a condition:
     *  NOT STUDENTS
     */
    @Test
    public void allCouresWithoutStudents() {
        // "SELECT c FROM Course c WHERE c.students IS EMPTY"
        // First
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        // Second
        Root<Course> courseRoot = cq.from(Course.class);

        // Third
        Predicate studentsEmpty = cb.isEmpty(courseRoot.get("students"));
        // Fourth
        cq.where(studentsEmpty);

        // Five
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();
        logger.info("Courses empty -> {}", resultList);

    }

    @Test
    public void join() {
        // "SELECT c FROM Course c JOIN c.students s"
        // First
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        // Second
        Root<Course> courseRoot = cq.from(Course.class);

        // Third
        Join<Object, Object> students = courseRoot.join("students");

         // Fourth

        // Five
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();
        logger.info("JOINED COURSES -> {}", resultList);

    }

    @Test
    public void leftJoin() {
        // "SELECT c FROM Course c JOIN c.students s"
        // First
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        // Second
        Root<Course> courseRoot = cq.from(Course.class);

        // Third
        Join<Object, Object> students = courseRoot.join(
                "students", JoinType.LEFT);

        // Fourth

        // Five
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();
        logger.info("JOINED COURSES -> {}", resultList);

    }

}
