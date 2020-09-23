package com.raframz.school.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String number;
	
	/**
	 * Student in passport object
	 * @param mappedBy indicates that the reference 'passport' in Student class will make that Passport enity be the no-owneer-side of the relationship, this 
	 * avoid that the data is duplicated
	 */
	@OneToOne(fetch=FetchType.LAZY, mappedBy = "passport")
	private Student student;
	
	protected Passport() {}

	public Passport(String number) {
		super();
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return String.format("\nNUMBER [%s]", number);
	}			
	
}























