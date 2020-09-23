package com.raframz.school.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import com.raframz.school.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raframz.school.entity.Course;

@Repository
@Transactional
public class CourseRepository {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;
	
	public Course getById(long id) {
		return em.find(Course.class, id);
	}
	
	public List<Course> getAll(){
		TypedQuery<Course> namedQuery = em.createNamedQuery("getAllCourses", Course.class);
		return namedQuery.getResultList();
	}
	
	public void deleteById(long id){
		Course course =  getById(id);
		em.remove(course);
	}
	
	public Course save(Course course) {
		if(course.getId().equals(null)) {
			em.persist(course);
		}else {
			em.merge(course);
		}
		return course;
	}

	public void hardCodedReviewsForCourse(){
		// get the course 10003
		Course course =  getById(10003L);
		log.info("Retrieved course -> {}", course.getReviews());

		// add 2 reviews to it
		Review one = new Review("9", "Awesome course");
		Review two = new Review("7", "I learned a lot");

		course.addReview(one);
		one.setCourse(course);

		course.addReview(two);
		two.setCourse(course);

		// save it to the database
		em.persist(one); em.persist(two);
	}

	public void addReviewsForCourse(Long course_id, List<Review> reviews){
		// get the course 10003
		Course course =  getById(course_id);
		log.info("Retrieved course -> {}", course.getReviews());

		for (Review review: reviews) {
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}
	}
	
}




























