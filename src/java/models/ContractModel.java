package models;

import entities.Contracts;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ContractModel {

    @PersistenceContext
    EntityManager em;

    public boolean create(Contracts contract) {
        try {
            em.persist(contract);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
