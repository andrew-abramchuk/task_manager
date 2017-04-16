package com.mavenjavafxapp.crud.Impl;

import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonsWithLongTasksResult;
import com.mavenjavafxapp.crud.interfaces.PersonService;
import com.mavenjavafxapp.entities.PersonEntity;
import com.mavenjavafxapp.crud.ResultEntriesClasses.PersonNumbersResult;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    @PersistenceContext
    private EntityManager em = Persistence.createEntityManagerFactory("tm_persistenceUnit").createEntityManager();

    public List<PersonEntity> getAll() {
        TypedQuery<PersonEntity> query = em.createNamedQuery("PersonEntity.getAll", PersonEntity.class);
        return query.getResultList();
    }

    public List<PersonNumbersResult> getAllWithNumbers() {
        Query resultQuery = em.createNamedQuery("PersonEntity.getAllWithNumbers");
        List<Object[]> entries = (List<Object[]>) resultQuery.getResultList();
        List<PersonNumbersResult> result = new ArrayList<>();
        for (Object[] entry : entries) {
            result.add(new PersonNumbersResult((String) entry[0], (int) entry[1]));
        }
        return result;
    }

    public List<PersonsWithLongTasksResult> findAllBySpentTime(int hours) {
        Query query = em.createNamedQuery("PersonEntity.findAllBySpentTime");
        query.setParameter("hours", hours);
        return query.getResultList();
    }

    public List<PersonEntity> getByRegData(String personFio, String personLogin, String personPassword) {
        TypedQuery<PersonEntity> query = em.createNamedQuery("PersonEntity.getByRegData", PersonEntity.class);
        query.setParameter("personFio", personFio);
        query.setParameter("personLogin", personLogin);
        query.setParameter("personPassword", personPassword);
        return query.getResultList();
    }

    public PersonEntity add(PersonEntity person) {
        em.getTransaction().begin();
        PersonEntity taskFromDB = em.merge(person);
        em.getTransaction().commit();
        return taskFromDB;
    }

    public PersonEntity update(PersonEntity person) {
        em.getTransaction().begin();
        PersonEntity taskFromDB = em.merge(person);
        em.getTransaction().commit();
        return taskFromDB;
    }

}
