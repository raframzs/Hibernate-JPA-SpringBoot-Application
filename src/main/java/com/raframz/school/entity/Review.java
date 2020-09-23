package com.raframz.school.entity;

import javax.persistence.*;

@Entity
public class Review {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String rating;
	
	private String description;

	/**
	 * Many reviews are related to one course
	 * It's the owning side of the relationship
	 * @ManyToOne its default EAGER Fetching
	 */
	@ManyToOne
	private Course course;
	
	protected Review(){}
	
	public Review(String rating, String description) {
		super();
		this.rating = rating;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return String.format("Review[%s - %s]", rating, description);
	}
		
	
}
