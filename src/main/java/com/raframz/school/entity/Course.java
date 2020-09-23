package com.raframz.school.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table
@NamedQuery(name="getAllCoures", query="SELECT c FROM Course c")
@Cacheable
@SQLDelete(sql = "UPDATE Course SET is_deleted=true WHERE id=?")
@Where(clause = "is_deleted = false")
public class Course {
	/**
	 * static to prevent to ve Mapped
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(Course.class);

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	/**
	 * One course have many reviews
	 * @OneToMany its default LAZY  Fetching
	 */
	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList<>();

	@ManyToMany(mappedBy = "courses")
	@JsonIgnore
	private List<Student> students = new ArrayList<>();

	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	/**
	 * soft delete column
	 */
	private boolean isDeleted;
	
	public Course() {}

	public Course(String name) {
		super();
		this.name = name;
	}

	@PreRemove
	private void preRemove(){
		LOGGER.info("Setting isDeleted to True");
		this.isDeleted = true;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	@Override
	public String toString() {
		return String.format("Id: %s Course: %s ", id, name);
	}


}
