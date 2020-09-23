package com.raframz.school.repository;

import com.raframz.school.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public void insert(Employee employee){
		em.persist(employee);
	}

    /**
     * Use this retrieve function for all annotations that came from
	 * @Inheritance because on all of them you will find a Employee table
	 *
     * @return a list of all employees added as subclasses of Employee entity
     */
	public List<Employee> getAll() {
		return em.createQuery("SELECT e FROM Employee e",
				Employee.class).getResultList();
	}

	/**
	 * Use this it the Superclass has the MappedSuperClass annotation
	 * so, this special it's not longer an entity even it's not a
	 * table anymore, the only thing that exist it's the subclasses tables
	 *
	 * @return a list of all PartTimeEmployees added as subclasses of Employee entity
	 */
	public List<PartTimeEmployee> getAllPartTimeEmployees(){
		return em.createQuery("SELECT p FROM PartTimeEmployee p",
				PartTimeEmployee.class).getResultList();
	}

	/**
	 * Use this it the Superclass has the MappedSuperClass annotation
	 * so, this special it's not longer an entity even it's not a
	 * table anymore, the only thing that exist it's the subclasses tables
	 *
	 * @return a list of all FullTimeEmployees added as subclasses of Employee entity
	 */
	public List<FullTimeEmployee> getAllFullTimeEmployees(){
		return em.createQuery("SELECT p FROM FullTimeEmployee p",
				FullTimeEmployee.class).getResultList();
	}

}




























