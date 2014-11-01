package models;

import entities.FeedbackLevel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FeedbackLevelModel {

    @PersistenceContext
    EntityManager em;

    public List<FeedbackLevel> getAll() {
        return em.createNamedQuery("FeedbackLevel.findAll").getResultList();
    }

}
