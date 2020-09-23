package com.raframz.school.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * One to One relationship with Passport
 * @author rafra
 *
 */
@Entity
@NamedQuery(name="getAllStudents", query="SELECT s FROM Student s")
public class Student {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Passport passport;

	@ManyToMany()
	@JoinTable(name = "STUDENT_COURSE",
			joinColumns = @JoinColumn(name="STUDENT_ID"),
			inverseJoinColumns = @JoinColumn(name="COURSE_ID"))
	private List<Course> courses = new ArrayList<>();
	
	protected Student(){}
	
	public Student(String name) {
		super();
		this.name = name;
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

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	@Override
	public String toString() {
		return String.format("\nId: %s Student: %s \n", id, name);
	}
		
	
}
