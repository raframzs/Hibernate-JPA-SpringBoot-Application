package com.raframz.school.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.raframz.school.SchoolApplication;
import com.raframz.school.entity.Passport;
import com.raframz.school.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
class StudentRepositoryTest {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository sr;
	
	@Autowired
	EntityManager em;
	
	// Session & Session Factory
	// EntityManager and Persistence context
	// Transaction
	
	@Test
	public void someTest() {
		sr.someToUnderstandPersistenceContext();
	}

	@Test
	@Transactional
	void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 66201082L);
		log.info("Student ->{}", student);
		log.info("Student ->{}", student.getPassport()); 
	}
	
	@Test
	@Transactional
	void retrievePassportAndAssociativeStudent() {
		Passport passport = em.find(Passport.class, 214188130L);
		log.info("\nStudent ->{}", passport);
		log.info("\nStudent ->{}", passport.getStudent()); 
	}

	@Test
	@Transactional
	void retrieveStudentAndCourse(){
		Student student = em.find(Student.class, 66201082L);
		log.info("Student ->{}", student);
		// Lazy fetch
		log.info("Courses ->{}", student.getCourses());
		}

}













 
