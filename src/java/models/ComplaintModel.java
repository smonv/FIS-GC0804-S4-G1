package models;

import entities.Complaints;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ComplaintModel {

    @PersistenceContext
    EntityManager em;

    public boolean create(Complaints complaint) {
        try {
            em.persist(complaint);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
