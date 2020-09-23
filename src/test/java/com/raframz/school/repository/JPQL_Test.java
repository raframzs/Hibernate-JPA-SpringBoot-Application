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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
public class JPQL_Test {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void JPQL_basic() {
        Query query = em.createNamedQuery("getAll");
        List<?> resultList = query.getResultList();
        logger.info("Select c from Course c -> {}", resultList);
    }

    @Test
    public void JPQL_typed() {
        TypedQuery<Course> query =
                em.createQuery("SELECT c FROM Course c", Course.class);

        List<Course> list = query.getResultList();

        logger.info("Select c from Course c -> {}", list);
    }

    /**
     * Prints a list of Courses without students
     *
     * On the JPQL sentence c.students it's addressing to the 'students'
     * reference on the Course entity class
     */
    @Test
    public void JPQL_CoursesWithoutStudents() {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c WHERE c.students IS EMPTY",
                Course.class);
        List<Course> courseList = query.getResultList();
        logger.info("Courses without students ->{}", courseList);


    }

    /**
     * Retrieve the info of the Courses with at least two students
     */
    @Test
    public void JPQL_CoursesTwoStudents() {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c WHERE SIZE(c.students) >= 2",
                Course.class);
        List<Course> courseList = query.getResultList();
        logger.info("Courses without students ->{}", courseList);


    }

    @Test
    public void JPQL_CoursesOrderedByStudent() {
        TypedQuery<Course> query = em.createQuery(
                "SELECT c FROM Course c ORDER BY SIZE(c.students)",
                Course.class);
        List<Course> courseList = query.getResultList();
        logger.info("Courses without students ->{}", courseList);

    }

    /**
     * To study:
     *     // like
     *     // BETWEEN 100 AND 1000
     *     // IS NULL
     *     // UPPER, LOWER, TRIM, LENGTH
     */
    @Test
    public void JPQL_StudentsWithPassportLike(){
        TypedQuery<Student> query = em.createQuery(
                "SELECT s FROM Student s WHERE s.passport.number LIKE '%1412%'",
                Student.class
        );
        List<Student> studentList = query.getResultList();
        logger.info("Students with passport like 1412 -> {}", studentList);

    }

    /**
     * JOIN => SELECT c, s FROM Course c JOIN c.students s (only courses with students)
     * LEFT JOIN => SELECT c, s FROM Course c LEFT JOIN c.students s
     * CROSS JOIN => SELECT c, s FROM Course c, Student s
     */
    @Test
    public void join(){
        Query query = em.createQuery(
                "SELECT c, s FROM Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for (Object [] result: resultList) {
            logger.info("Course{} Student{} ", result[0], result[1]);
        }
    }

    @Test
    public void left_join(){
        Query query = em.createQuery(
                "SELECT c, s FROM Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for (Object [] result: resultList) {
            logger.info("Course{} Student{} ", result[0], result[1]);
        }
    }

    @Test
    public void cross_join(){
        Query query = em.createQuery(
                "SELECT c, s FROM Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size -> {}", resultList.size());
        for (Object [] result: resultList) {
            logger.info("Course{} Student{} ", result[0], result[1]);
        }
    }


}
