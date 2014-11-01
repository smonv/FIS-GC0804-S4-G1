package models;

import entities.Clients;
import entities.Feedbacks;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FeedbackModel {

    @PersistenceContext
    EntityManager em;

    public boolean create(Feedbacks feedback) {
        try {
            em.persist(feedback);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(Feedbacks feedback) {
        try {
            Feedbacks remove_feedback = em.getReference(Feedbacks.class, feedback.getFid());
            em.remove(remove_feedback);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Feedbacks feedback) {
        try {
            if (em.find(Feedbacks.class, feedback.getFid()) != null) {
                em.merge(feedback);
                return true;
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Feedbacks> getAll() {
        return em.createNamedQuery("Feedbacks.findAll").getResultList();
    }

    public Feedbacks getById(int fid) {
        List<Feedbacks> feedbacks = em.createNamedQuery("Feedbacks.findByFid").setParameter("fid", fid).getResultList();
        return feedbacks.size() > 0 ? feedbacks.get(0) : null;
    }

    public List<Feedbacks> getByClientId(Clients client) {
        return em.createNamedQuery("Feedbacks.findByClientId").setParameter("clientId", client).getResultList();
    }
}
