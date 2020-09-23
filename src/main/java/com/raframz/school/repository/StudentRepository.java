package com.raframz.school.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import com.raframz.school.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raframz.school.entity.Passport;
import com.raframz.school.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	@Autowired
	EntityManager em;
	
	public Student getById(Long id) {
		return em.find(Student.class, id);
	}
		
	public List<Student> getAll(){
		TypedQuery<Student> query = em.createNamedQuery("getAllStudents", Student.class);
		return query.getResultList();
	}	
	
	public void save(Student student) {
		if(student.getId() == null) {
			em.persist(student);
		}else {
			em.merge(student);
		}
	}
	
	public void deleteById(Long id) {
		Student student = getById(id);
		em.remove(student);
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("MIKEC<<<<<<<<<<<<<PXY1418860");
		em.persist(passport);
		
		Student student = new Student("Mike Cornellus");
		student.setPassport(passport);
		em.persist(student);
	}
	
	public void someToUnderstandPersistenceContext() {
		// Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 66201016L);
		// Persitence context (student)
		
		// Database Operation 2 - Retrive passport
		Passport passport = student.getPassport();
		// Persitence context (student, passport)
		
		// Database Operation 3 - U update passport
		passport.setNumber("MARG<<<<<<<<<<<<<PSW1418846");
		// Persitence context (student, passport++)
		
		// Database Operation 4 - Update student
		student.setName("MA GUADALUPE CORVO");
		// Persitence context (student++, passport++)
	}

	public void insertHardCodedStudentAndCourse(){
		Student student = new Student("JAIRO ROJAS");
		Course course = new Course("Design of Applications");
		em.persist(student);
		em.persist(course);

		student.addCourse(course);
		course.addStudent(student);
		em.persist(student);

	}

	public void insertStudentAndCourse(Student student, Course course){
		student.addCourse(course);
		course.addStudent(student);
		em.persist(student);

	}
	
}
























