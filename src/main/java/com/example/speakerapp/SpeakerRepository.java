package com.example.speakerapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * This is DAO class
 * Created by glady on 27.06.2017.
 */

//public interface SpeakerRepository extends CrudRepository<Speaker, Long>{

    //HIBERNATE CONFIGURATION

    @Repository
    public class SpeakerRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Speaker> findByName(String name)
    {
        Query query = em.createQuery("select s from Speaker as s where s.name = :name");
        return query.setParameter("name", name).getResultList();
    }
    public List<Speaker> getAllSpeakers(){return em.createQuery("from Speaker").getResultList();}

    public void save(List<Speaker> speakers){
        for (Speaker speaker: speakers){
            em.persist(speaker);
        }
    }
    public long count(){return (long) em.createQuery("select count(s.name) from Speaker s").getSingleResult();}

    public void deleteAll() {em.createQuery("delete from Speaker").executeUpdate();}

    //SPRING DATA CONFIGURATION

}
