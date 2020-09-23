package com.raframz.school.repository;

import com.raframz.school.SchoolApplication;
import com.raframz.school.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
public class NativeQueriesTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void native_queries_basic() {
        Query query = em.createNativeQuery("SELECT * FROM Course", Course.class);
        List result =  query.getResultList();
        logger.info("SELECT * FROM Course -> {}", result);
    }

    @Test
    public void native_queries_named_basic() {
        Query query = em.createNativeQuery("SELECT * FROM Course WHERE id= :id", Course.class);
        query.setParameter("id", 10006L);
        List result =  query.getResultList();
        logger.info("SELECT * FROM Course -> {}", result);
    }

    /*@Test
    @Transactional
    public void native_queries_to_update() {
        Query query = em.createNativeQuery("UPDATE Course SET last_updated_date", Course.class);
        int noOfRowsUpdated = query.executeUpdate();
        logger.info("no Of Rows Updated -> {}", noOfRowsUpdated);
    }*/


}
