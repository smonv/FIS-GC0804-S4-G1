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
    
    public FeedbackLevel getById(int flid){
        List<FeedbackLevel> feedbacklevels= em.createNamedQuery("FeedbackLevel.findByFlid").setParameter("flid", flid).getResultList();
       return feedbacklevels.size() > 0 ? feedbacklevels.get(0) : null;
    }

}
